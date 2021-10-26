package com.optus.codetest.utils

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.testingObserver(onChangeHandler: (T) -> Unit) {
    val observer =
        OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}