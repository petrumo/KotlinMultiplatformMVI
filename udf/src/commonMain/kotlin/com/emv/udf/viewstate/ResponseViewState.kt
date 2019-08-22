package com.emv.udf.viewstate

import com.emv.datalayer.models.BaseModel

data class ResponseViewState(val isLoading: Boolean = false,
                             val model: BaseModel? = null,
                             val error: Throwable? = null
) : ViewState