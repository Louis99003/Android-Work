package com.houseprice.cathaybkxtaipeizoo.view.animal.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import com.houseprice.cathaybkxtaipeizoo.SingleLiveEvent
import com.houseprice.cathaybkxtaipeizoo.data.model.AnimalInfoViewItem
import com.houseprice.cathaybkxtaipeizoo.view.BaseViewModel
import com.houseprice.cathaybkxtaipeizoo.view.animal.info.AnimalInfoActivity.Companion.BUNDLE_PARAM_ANIMAL_INFO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimalInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {


    private val _infoData = SingleLiveEvent<AnimalInfoViewItem>()
    val infoData: SingleLiveEvent<AnimalInfoViewItem> = _infoData

    private val _activityTitle = MutableLiveData<String>()
    val activityTitle: MutableLiveData<String> = _activityTitle


    private val animalInfo = Gson().fromJson(
        savedStateHandle.get<String>(BUNDLE_PARAM_ANIMAL_INFO),
        AnimalInfoViewItem::class.java
    ) ?: AnimalInfoViewItem()

    init {
        _activityTitle.value = animalInfo.aNameCh
        _infoData.value = animalInfo
    }

}