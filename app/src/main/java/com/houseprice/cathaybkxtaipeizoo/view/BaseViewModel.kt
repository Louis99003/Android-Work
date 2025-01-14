package com.houseprice.cathaybkxtaipeizoo.view

import androidx.lifecycle.ViewModel
import com.houseprice.cathaybkxtaipeizoo.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    var toast: SingleLiveEvent<String> = SingleLiveEvent()

}
