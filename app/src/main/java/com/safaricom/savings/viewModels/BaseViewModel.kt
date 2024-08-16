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

    /**
     * Binds a LiveData source to a MediatorLiveData observer.
     *
     * This method sets up an observer on the provided LiveData source and posts any changes
     * to the MediatorLiveData observer. Useful for combining or managing LiveData sources.
     *
     * @param observer The MediatorLiveData that will observe changes from the source LiveData.
     * @param source The LiveData source that will be observed.
     */
    fun <R> bindObserver(observer: MediatorLiveData<R>, source: LiveData<R>) {
        observer.apply {
            addSource(source) {
                postValue(it)
            }
        }
    }

    /**
     * Called when the ViewModel is no longer used and will be destroyed.
     * Cancels all coroutines to prevent memory leaks and ensure proper cleanup.
     */
    override fun onCleared() {
        super.onCleared()
        mainJob.cancelChildren()
    }
}