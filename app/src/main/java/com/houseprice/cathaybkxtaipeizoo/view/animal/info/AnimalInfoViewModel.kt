package com.houseprice.cathaybkxtaipeizoo.view.animal.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.houseprice.cathaybkxtaipeizoo.SingleLiveEvent
import com.houseprice.cathaybkxtaipeizoo.data.NetworkResult
import com.houseprice.cathaybkxtaipeizoo.data.enumclass.ExhibitInfoCardType
import com.houseprice.cathaybkxtaipeizoo.data.model.AnimalInfoViewItem
import com.houseprice.cathaybkxtaipeizoo.data.model.AnimalItem
import com.houseprice.cathaybkxtaipeizoo.data.model.ExhibitInfoCardView
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitViewItem
import com.houseprice.cathaybkxtaipeizoo.data.orEmpty
import com.houseprice.cathaybkxtaipeizoo.data.usecase.UseCaseRepository
import com.houseprice.cathaybkxtaipeizoo.view.BaseViewModel
import com.houseprice.cathaybkxtaipeizoo.view.animal.info.AnimalInfoActivity.Companion.BUNDLE_PARAM_ANIMAL_INFO
import com.houseprice.cathaybkxtaipeizoo.view.zoo.info.ExhibitInfoActivity.Companion.BUNDLE_PARAM_EXHIBIT_INFO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCaseRepository: UseCaseRepository
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