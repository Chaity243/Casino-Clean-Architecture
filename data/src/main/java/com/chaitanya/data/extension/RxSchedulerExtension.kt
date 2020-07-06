package com.chaitanya.data.extension

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

//Observable
fun <T> Observable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Flowable
fun <T> Flowable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

private fun <T> Flowable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Single
fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Maybe
fun <T> Maybe<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

private fun <T> Maybe<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())