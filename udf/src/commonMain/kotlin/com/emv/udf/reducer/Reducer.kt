package com.emv.udf.reducer

import com.emv.udf.actions.Action
import com.emv.udf.results.DataResult
import com.emv.udf.viewstate.AppViewState
import com.emv.udf.viewstate.NavigationStates
import com.emv.datalayer.models.BaseModel

class Reducer {

    companion object {

        fun reduce(appViewState: AppViewState, action: Action): AppViewState {
            when (action) {
                is Action.Home -> {
                    return navigationResult(appViewState, NavigationStates.home)
                }
                is Action.Mesh -> {
                    return navigationResult(appViewState, NavigationStates.guide)
                }
                is Action.Map -> {
                    return navigationResult(appViewState, NavigationStates.library)
                }
                is Action.OnData<*> -> {
                    return dataResult(appViewState, action.dataResult)
                }
                is Action.Init -> {
                    return appViewState
                }
            }
        }


        private fun dataResult(appViewState: AppViewState, dataResult: DataResult<*>): AppViewState {
            var responseViewState = appViewState.responseViewState
            when (dataResult) {
                is DataResult.Loading -> responseViewState = responseViewState.copy(isLoading = true)
                is DataResult.Success -> responseViewState =
                    responseViewState.copy(isLoading = false, model = dataResult.data as BaseModel, error = null)
                is DataResult.Failure -> responseViewState = responseViewState.copy(isLoading = false, error = null)
                else -> throw IllegalStateException()
            }
            return AppViewState(appViewState.navigationViewState, responseViewState)
        }

        private fun navigationResult(appViewState: AppViewState, state: NavigationStates): AppViewState {
            var navigationState = appViewState.navigationViewState
            navigationState = navigationState.copy(selected = state)
            return AppViewState(navigationState, appViewState.responseViewState)
        }
    }
}