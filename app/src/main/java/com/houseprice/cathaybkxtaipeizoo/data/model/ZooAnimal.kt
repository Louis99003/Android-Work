package com.houseprice.cathaybkxtaipeizoo.data.model

import com.google.gson.annotations.SerializedName

/**
 * 代表動物園動物查詢的結果。
 *
 * @param result 動物園動物的資料。
 */
data class ZooAnimalResult(
    val result: ZooAnimalData? = null
)

/**
 * 代表動物園動物的資料。
 *
 * @param limit 查詢的限制筆數。
 * @param offset 查詢的偏移量。
 * @param count 查詢結果的數量。
 * @param sort 查詢的排序方式。
 * @param results 動物項目的列表。
 */
data class ZooAnimalData(
    val limit: Int = 0,
    val offset: Int = 0,
    val count: Int = 0,
    val sort: String = "",
    val results: MutableList<AnimalItem>? = null
)

/**
 * 代表一個動物項目。
 *
 * @param id 動物的 ID。
 * @param importDate 動物的導入日期。
 * @param aNameCh 動物的中文名稱。
 * @param aSummary 動物的摘要。
 * @param aKeywords 動物的關鍵字。
 * @param aAlsoknown 動物的別稱。
 * @param aGeo 動物的地理位置。
 * @param aLocation 動物的所在位置。
 * @param aPoigroup 動物的族群。
 * @param aNameEn 動物的英文名稱。
 * @param aNameLatin 動物的拉丁名稱。
 * @param aConservation 動物的保育狀況。
 * @param aDistribution 動物的分布。
 * @param aHabitat 動物的棲息地。
 * @param aFeature 動物的特徵。
 * @param aBehavior 動物的習性。
 * @param aDiet 動物的飲食。
 * @param aCrisis 動物的危機。
 * @param aInterpretation 動物的詮釋。
 * @param aPic01Alt 動物的圖片1備註。
 * @param aPic01Url 動物的圖片1網址。
 * @param aPic02Alt 動物的圖片2備註。
 * @param aPic02Url 動物的圖片2網址。
 * @param aPic03Alt 動物的圖片3備註。
 * @param aPic03Url 動物的圖片3網址。
 * @param aPic04Alt 動物的圖片4備註。
 * @param aPic04Url 動物的圖片4網址。
 * @param aUpdate 動物的資訊更新日期。
 */
data class AnimalItem(
    @SerializedName("_id") val id: Int = 0,
    @SerializedName("_importdate") val importDate: ImportDate? = null,
    @SerializedName("a_name_ch") val aNameCh: String? = null,
    @SerializedName("a_summary") val aSummary: String? = null,
    @SerializedName("a_keywords") val aKeywords: String? = null,
    @SerializedName("a_alsoknown") val aAlsoknown: String? = null,
    @SerializedName("a_geo") val aGeo: String? = null,
    @SerializedName("a_location") val aLocation: String? = null,
    @SerializedName("a_poigroup") val aPoigroup: String? = null,
    @SerializedName("a_name_en") val aNameEn: String? = null,
    @SerializedName("a_name_latin") val aNameLatin: String? = null,
    @SerializedName("a_conservation") val aConservation: String? = null,
    @SerializedName("a_distribution") val aDistribution: String? = null,
    @SerializedName("a_habitat") val aHabitat: String? = null,
    @SerializedName("a_feature") val aFeature: String? = null,
    @SerializedName("a_behavior") val aBehavior: String? = null,
    @SerializedName("a_diet") val aDiet: String? = null,
    @SerializedName("a_crisis") val aCrisis: String? = null,
    @SerializedName("a_interpretation") val aInterpretation: String? = null,
    @SerializedName("a_pic01_alt") val aPic01Alt: String? = null,
    @SerializedName("a_pic01_url") val aPic01Url: String? = null,
    @SerializedName("a_pic02_alt") val aPic02Alt: String? = null,
    @SerializedName("a_pic02_url") val aPic02Url: String? = null,
    @SerializedName("a_pic03_alt") val aPic03Alt: String? = null,
    @SerializedName("a_pic03_url") val aPic03Url: String? = null,
    @SerializedName("a_pic04_alt") val aPic04Alt: String? = null,
    @SerializedName("a_pic04_url") val aPic04Url: String? = null,
    @SerializedName("a_update") val aUpdate: String? = null
) {
    fun getPicUrl(): String {
        return if (!aPic01Url.isNullOrEmpty()) {
            aPic01Url
        } else if (!aPic02Url.isNullOrEmpty()) {
            aPic02Url
        } else if (!aPic03Url.isNullOrEmpty()) {
            aPic03Url
        } else if (!aPic04Url.isNullOrEmpty()) {
            aPic04Url
        } else {
            ""
        }
    }
}