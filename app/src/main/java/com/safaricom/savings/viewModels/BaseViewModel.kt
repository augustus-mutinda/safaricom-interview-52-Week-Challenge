package com.safaricom.savings.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.safaricom.data.utils.Event
import com.safaricom.data.utils.ResourceState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

open class BaseViewModel : ViewModel() {
    private val mainJob = SupervisorJob()
    val uiScope = CoroutineScope(Dispatchers.Main + mainJob)
    val bgScope = CoroutineScope(Dispatchers.IO + mainJob)

    val _loadingState = MediatorLiveData<Event<@ResourceState Int?>>()
    val loadingState: LiveData<Event<@ResourceState Int?>> = _loadingState

    fun <R> bindObserver(observer: MediatorLiveData<R>, source: LiveData<R>) {
        observer.apply {
            addSource(source) {
                postValue(it)
            }
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        mainJob.cancelChildren()
    }
}