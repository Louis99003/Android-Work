package com.houseprice.cathaybkxtaipeizoo.data.model

import com.houseprice.cathaybkxtaipeizoo.data.enumclass.ExhibitInfoCardType

data class ExhibitInfoCardView(
    val exhibitInfoView: ZooExhibitViewItem? = null,
    val animalInfoView: AnimalInfoViewItem? = null,
    val cardType: ExhibitInfoCardType = ExhibitInfoCardType.LIST_NORMAL_CARD
)
