package com.houseprice.cathaybkxtaipeizoo.view.zoo.info

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
import com.houseprice.cathaybkxtaipeizoo.view.zoo.info.ExhibitInfoActivity.Companion.BUNDLE_PARAM_EXHIBIT_INFO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExhibitInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCaseRepository: UseCaseRepository
) : BaseViewModel() {

    var shouldListLoadingData = true

    private val _listData = SingleLiveEvent<MutableList<ExhibitInfoCardView>>()
    val listData: SingleLiveEvent<MutableList<ExhibitInfoCardView>> = _listData

    private val _listIsLoading = MutableLiveData(false)
    val listIsLoading: LiveData<Boolean> = _listIsLoading

    private val _activityTitle = MutableLiveData<String>()
    val activityTitle: MutableLiveData<String> = _activityTitle


    private var currentPage = 1 // 初始化頁數
    private var listPageSizeRule = 10 // 列表 api 拉取筆數

    private val exhibitInfo = Gson().fromJson(
        savedStateHandle.get<String>(BUNDLE_PARAM_EXHIBIT_INFO),
        ZooExhibitViewItem::class.java
    )
        ?: ZooExhibitViewItem()

    init {
        _activityTitle.value = exhibitInfo.eName
    }

    fun getListData() {
        viewModelScope.launch {
            isListViewLoading(true)
            shouldListLoadingData = false
            var items = listData.value
            if (items == null) {
                items = ArrayList()
            }

            if (items.size == 0) {
                items.add(
                    ExhibitInfoCardView(
                        exhibitInfoView = exhibitInfo,
                        animalInfoView = null,
                        cardType = ExhibitInfoCardType.TOP_INFO
                    )
                )

            }

            useCaseRepository.getZooAnimal(
                exhibitInfo.eName,
                listPageSizeRule,
                currentPage * listPageSizeRule
            ).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        if (items.size == 1) {
                            items.add(
                                ExhibitInfoCardView(cardType = ExhibitInfoCardType.LIST_TITLE_CARD)
                            )
                        }

                        items.addAll(
                            getListViewData(it.data?.result?.results)
                        )

                        listData.value = items
                        currentPage++
                        isListViewLoading(false)

                    }

                    is NetworkResult.Error -> {
                        toast.value = it.error.description
                        isListViewLoading(false)
                    }
                }
            }
        }

    }

    private fun getListViewData(
        results: MutableList<AnimalItem>?
    ): MutableList<ExhibitInfoCardView> {
        val list: MutableList<ExhibitInfoCardView> = ArrayList()

        if (!results.isNullOrEmpty()) {
            results.forEach { result ->
                val item = ExhibitInfoCardView(
                    animalInfoView = AnimalInfoViewItem(
                        id = result.id,
                        aNameCh = result.aNameCh.orEmpty(),
                        aSummary = result.aSummary.orEmpty(),
                        aKeywords = result.aKeywords.orEmpty(),
                        aAlsoknown = result.aAlsoknown.orEmpty(),
                        aPoigroup = result.aPoigroup.orEmpty(),
                        aNameEn = result.aNameEn.orEmpty(),
                        aConservation = result.aConservation.orEmpty(),
                        aDistribution = result.aDistribution.orEmpty(),
                        aHabitat = result.aHabitat.orEmpty(),
                        aFeature = result.aFeature.orEmpty(),
                        aBehavior = result.aBehavior.orEmpty(),
                        aDiet = result.aDiet.orEmpty(),
                        aCrisis = result.aCrisis.orEmpty(),
                        aInterpretation = result.aInterpretation.orEmpty(),
                        picture = result.getPicUrl(),
                        aUpdate = result.aUpdate.orEmpty()
                    ), cardType = ExhibitInfoCardType.LIST_NORMAL_CARD
                )
                list.add(item)
            }
            if (results.size < listPageSizeRule) {
                shouldListLoadingData = false
            } else {
                shouldListLoadingData = true
            }
        } else {
            shouldListLoadingData = false
            //TODO list end
        }

        return list
    }


    private fun isListViewLoading(b: Boolean) {
        _listIsLoading.value = b
    }
}