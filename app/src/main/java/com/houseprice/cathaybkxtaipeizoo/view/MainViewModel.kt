package com.houseprice.cathaybkxtaipeizoo.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.houseprice.cathaybkxtaipeizoo.SingleLiveEvent
import com.houseprice.cathaybkxtaipeizoo.data.NetworkResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ExhibitItem
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitViewItem
import com.houseprice.cathaybkxtaipeizoo.data.orEmpty
import com.houseprice.cathaybkxtaipeizoo.data.usecase.UseCaseRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCaseRepository: UseCaseRepository
) : BaseViewModel() {
    var shouldListLoadingData = true

    private val _listData = SingleLiveEvent<MutableList<ZooExhibitViewItem>>()
    val listData: SingleLiveEvent<MutableList<ZooExhibitViewItem>> = _listData

    private val _listIsLoading = MutableLiveData(false)
    val listIsLoading: LiveData<Boolean> = _listIsLoading
    private var listPageSizeRule = 10 // 列表 api 拉取筆數
    private var currentPage = 0 // 初始化頁數

    fun getListData() {
        viewModelScope.launch {
            isListViewLoading(true)
            shouldListLoadingData = false

            useCaseRepository.getZooExhibit("", listPageSizeRule, currentPage * listPageSizeRule)
                .collect {
                    when (it) {
                        is NetworkResult.Success -> {
                            var items = listData.value
                            if (items == null) {
                                items = ArrayList()
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
        results: MutableList<ExhibitItem>?
    ): MutableList<ZooExhibitViewItem> {
        val list: MutableList<ZooExhibitViewItem> = ArrayList()

        if (!results.isNullOrEmpty()) {
            results.forEach { result ->
                val zooExhibitViewItem = ZooExhibitViewItem(
                    id = result.id,
                    eNo = result.eNo.orEmpty(),
                    eCategory = result.eCategory.orEmpty(),
                    eName = result.eName.orEmpty(),
                    ePicUrl = result.ePicUrl.orEmpty(),
                    eInfo = result.eInfo.orEmpty(),
                    eMemo = result.eMemo.orEmpty()
                )
                list.add(zooExhibitViewItem)
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

    fun refreshSearchList() {
        currentPage = 0
        _listData.value = ArrayList()
        getListData()
    }

}