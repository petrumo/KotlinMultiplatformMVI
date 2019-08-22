package com.emv.udf.viewstate

enum class NavigationStates {home, live, guide, catchup, library, user, search}
data class NavigationViewState(val selected: NavigationStates = NavigationStates.home
) : ViewState