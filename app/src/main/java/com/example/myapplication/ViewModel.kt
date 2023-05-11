package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {

    val url: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val ifFinished: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val chooseHide: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val delete: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}