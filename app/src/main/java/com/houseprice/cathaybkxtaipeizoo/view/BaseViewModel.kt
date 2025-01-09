package com.houseprice.cathaybkxtaipeizoo.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.houseprice.cathaybkxtaipeizoo.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    var progressBar: MutableLiveData<Boolean> = MutableLiveData()

    var toast: SingleLiveEvent<String> = SingleLiveEvent()

}
