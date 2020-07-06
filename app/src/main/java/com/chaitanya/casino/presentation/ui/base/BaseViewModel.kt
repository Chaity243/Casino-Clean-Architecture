package com.chaitanya.casino.presentation.ui.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.chaitanya.casino.common.SingleEvent
import com.chaitanya.casino.common.SingleLiveEvent
import com.chaitanya.domain.entity.HomeEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 * All ViewModel should extend the BaseViewModel
 */

open class BaseViewModel : ViewModel(), Observable {
    protected val mDisposables = CompositeDisposable()

    /**
     * Live data to handle error
     */
    private var errorLiveData = MediatorLiveData<SingleEvent<HomeEntity.ErrorEntity>>()

    val mErrorLiveData: LiveData<SingleEvent<HomeEntity.ErrorEntity>>
        get() = errorLiveData

    /**
     * Live data to handle loading
     */
    private var loadingLiveData = SingleLiveEvent<Boolean>()

    val mLoadingLiveData: SingleLiveEvent<Boolean>
        get() = loadingLiveData

    private val callbacks = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    internal fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    internal fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }



    /**
     * Method call to handle loading
     */
    fun showLoading(show: Boolean) {
        loadingLiveData.postValue(show)
    }


    protected fun Disposable.track() {
        mDisposables.add(this)
    }

    protected fun getDisposable(): CompositeDisposable {
        return mDisposables
    }

    override fun onCleared() {
        mDisposables.clear()
        super.onCleared()
    }

    protected fun handleRemoteError(error: HomeEntity.ErrorEntity) {
        errorLiveData.postValue(SingleEvent(error))
    }
}