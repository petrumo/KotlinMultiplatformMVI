package com.emv.kotlinmultiplatformmvi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.map
//import androidx.lifecycle.observe weird issue without this import
//import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.observe
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.emv.kotlinmultiplatformmvi.databinding.ActivityMainBinding
import com.emv.udf.actions.Action
import com.emv.udf.viewstate.NavigationViewState
import com.emv.udf.viewstate.ResponseViewState
import com.emv.datalayer.models.BaseModel
import com.emv.datalayer.models.Feed
import com.emv.kotlinmultiplatformmvi.R
import com.emv.kotlinmultiplatformmvi.viewmodel.ResponseViewModel
import com.emv.kotlinmultiplatformmvi.viewmodel.ViewModelFactory
import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.simpleName
    val PROGRESS = "PROGRESS"
    private lateinit var viewModel: ResponseViewModel

    private lateinit var binding: ActivityMainBinding

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewModel.dispatch(Action.Home)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_mesh -> {
                viewModel.dispatch(Action.Mesh)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_map -> {
                viewModel.dispatch(Action.Map)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory()
        ).get(ResponseViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = ItemsListAdapter(Glide.with(this@MainActivity))
            setHasFixedSize(true)
        }

        viewModel.dispatch(Action.Init)
        viewModel.appState.map { appViewState -> appViewState.navigationViewState }.distinctUntilChanged().observe(this){render(it)}
        viewModel.appState.map { appViewState -> appViewState.responseViewState }.distinctUntilChanged().observe(this){render(it)}

    }

    private fun render(state: NavigationViewState) {
        Log.d(TAG, "::: Render navigation:$state")
    }


    private fun render(state: ResponseViewState) {
        Log.d(TAG, "::: Render:$state")
        if (state.isLoading) {
            binding.spinner.visibility = View.VISIBLE
        } else {
            binding.spinner.visibility = View.GONE
            val model: BaseModel? = state.model
            if (model is Feed)
                (binding.recyclerView.adapter as ItemsListAdapter).updateItems(model?.items)
        }
    }
}
