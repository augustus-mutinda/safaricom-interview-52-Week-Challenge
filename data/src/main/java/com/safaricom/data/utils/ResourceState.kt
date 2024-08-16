package com.safaricom.data.utils

import androidx.annotation.IntDef
import com.safaricom.data.utils.ResourceState.Companion.STATE_FAILED
import com.safaricom.data.utils.ResourceState.Companion.STATE_LOADING
import com.safaricom.data.utils.ResourceState.Companion.STATE_SUCCESS

@Target(AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    STATE_LOADING,
    STATE_SUCCESS,
    STATE_FAILED
)
annotation class ResourceState {
    companion object {
        const val STATE_LOADING = 1
        const val STATE_SUCCESS = 2
        const val STATE_FAILED = 3
    }
}
