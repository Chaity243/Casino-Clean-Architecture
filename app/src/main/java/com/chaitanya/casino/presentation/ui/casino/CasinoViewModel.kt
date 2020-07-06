package com.chaitanya.casino.presentation.ui.casino

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.chaitanya.casino.R
import com.chaitanya.casino.presentation.listeners.OnAddressSelectedListener
import com.chaitanya.casino.presentation.ui.base.BaseViewModel
import com.chaitanya.domain.common.ResultState
import com.chaitanya.domain.entity.HomeEntity
import com.chaitanya.domain.usecases.home.HomeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

class CasinoViewModel @Inject constructor(val homeUseCase: HomeUseCase) :
    BaseViewModel() {


    private val publishSubject = PublishSubject.create<String>()

    var list = arrayListOf<HomeEntity.Address>()

    val userEntry: MutableLiveData<String> = MutableLiveData()

    val items: ObservableList<String> =
        ObservableArrayList<String>()

    val itemBinding = ItemBinding.of<String> { sortOptionBinding, _, address ->
        sortOptionBinding.set(BR.item, R.layout.item_search_address)
            .bindExtra(BR.listener,
                object : OnAddressSelectedListener<String> {

                    override fun onAddressSelect(item: String) {
                        userEntry.value = item
                    }
                })
    }

    /* Debounce operator is used to collect the search query in timely manner instead of rapidly performing search on every character typed.
      *
      * switchMapSingle() plays very important role here. When there are multiple search requests in the queue, SwitchMap() ignores the previous emission and considers only the current search query. So the list will always displays the latest search results.
      *
      * PublishSubject emits the events at the time of subscription. In our case, calling publishSubject.onNext() invokes the emission of Observable again thus making newer network call.
      *
      * */
    fun findAddress() {
        publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMapSingle {
                homeUseCase.fetchAddresses(it, "Gurgaon")
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is ResultState.Success -> {
                        if (list.isNotEmpty()) list.clear()
                        list.addAll(it.data.data.addressList)

                        items.clear()
                        items.addAll(list.map { it.addressString })
                    }
                    is ResultState.Error -> {
                        it.error?.let { it1 -> handleRemoteError(it1) }

                    }
                }
            }.track()
    }


    fun clearDisposables() {
        mDisposables.clear()
    }

    fun setData(it: ResultState<HomeEntity.SearchResponse>) {
        when (it) {
            is ResultState.Success -> {
                list.addAll(it.data.data.addressList)
                items.clear()
                items.addAll(list.map { it.addressString })
            }
            is ResultState.Error -> {
                it.error?.let { it1 -> handleRemoteError(it1) }

            }
        }

    }

    fun publish(it: String?) {
        it?.let { it1 -> publishSubject.onNext(it1) }
    }
}