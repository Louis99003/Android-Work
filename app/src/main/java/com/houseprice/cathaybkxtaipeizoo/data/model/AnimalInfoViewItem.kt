package com.houseprice.cathaybkxtaipeizoo.data.model

/**
 * 代表一個動物項目。
 *
 * @param id 動物的 ID。
 * @param aNameCh 動物的中文名稱。
 * @param aSummary 動物的摘要。
 * @param aKeywords 動物的關鍵字。
 * @param aAlsoknown 動物的別稱。
 * @param aPoigroup 動物的族群。
 * @param aNameEn 動物的英文名稱。
 * @param aConservation 動物的保育狀況。
 * @param aDistribution 動物的分布。
 * @param aHabitat 動物的棲息地。
 * @param aFeature 動物的特徵。
 * @param aBehavior 動物的習性。
 * @param aDiet 動物的飲食。
 * @param aCrisis 動物的危機。
 * @param aInterpretation 動物的詮釋。
 * @param aUpdate 動物的資訊更新日期。
 */
data class AnimalInfoViewItem(
    val id: Int = 0,
    val aNameCh: String = "",
    val aSummary: String = "",
    val aKeywords: String = "",
    val aAlsoknown: String = "",
    val aPoigroup: String = "",
    val aNameEn: String = "",
    val aConservation: String = "",
    val aDistribution: String = "",
    val aHabitat: String = "",
    val aFeature: String = "",
    val aBehavior: String = "",
    val aDiet: String = "",
    val aCrisis: String = "",
    val aInterpretation: String = "",
    val picture: String = "",
    val aUpdate: String = ""
)