package com.houseprice.cathaybkxtaipeizoo.data.model

import com.google.gson.annotations.SerializedName

/**
 * 代表植物查詢的結果。
 *
 * @param result 植物資料。
 */
data class ZooPlantResult(
    val result: ZooPlantData? = null
)

/**
 * 代表植物資料。
 *
 * @param limit 查詢的限制筆數。
 * @param offset 查詢的偏移量。
 * @param count 查詢結果的數量。
 * @param sort 查詢的排序方式。
 * @param results 植物項目的列表。
 */
data class ZooPlantData(
    val limit: Int = 0,
    val offset: Int = 0,
    val count: Int = 0,
    val sort: String = "",
    val results: MutableList<PlantItem>? = null
)

/**
 * 代表一個植物項目。
 *
 * @param id 植物的 ID。
 * @param importDate 植物的導入日期。
 * @param fNameCh 植物的中文名稱。
 * @param fSummary 植物的摘要。
 * @param fKeywords 植物的關鍵字。
 * @param fAlsoknown 植物的別稱。
 * @param fGeo 植物的地理位置。
 * @param fLocation 植物的所在位置。
 * @param fNameEn 植物的英文名稱。
 * @param fNameLatin 植物的拉丁名稱。
 * @param fFamily 植物的科。
 * @param fGenus 植物的屬。
 * @param fBrief 植物簡述。
 * @param fFeature 植物的特徵。
 * @param fFunctionApplication 植物的功能與應用。
 * @param fCode 植物的代碼。
 * @param fPic01Alt 植物的圖片1備註。
 * @param fPic01Url 植物的圖片1網址。
 * @param fPic02Alt 植物的圖片2備註。
 * @param fPic02Url 植物的圖片2網址。
 * @param fPic03Alt 植物的圖片3備註。
 * @param fPic03Url 植物的圖片3網址。
 * @param fPic04Alt 植物的圖片4備註。
 * @param fPic04Url 植物的圖片4網址。
 */
data class PlantItem(
    @SerializedName("_id") val id: Int = 0,
    @SerializedName("_importdate") val importDate: ImportDate? = null,
    @SerializedName("f_name_ch") val fNameCh: String? = null,
    @SerializedName("f_summary") val fSummary: String? = null,
    @SerializedName("f_keywords") val fKeywords: String? = null,
    @SerializedName("f_alsoknown") val fAlsoknown: String? = null,
    @SerializedName("f_geo") val fGeo: String? = null,
    @SerializedName("f_location") val fLocation: String? = null,
    @SerializedName("f_name_en") val fNameEn: String? = null,
    @SerializedName("f_name_latin") val fNameLatin: String? = null,
    @SerializedName("f_family") val fFamily: String? = null,
    @SerializedName("f_genus") val fGenus: String? = null,
    @SerializedName("f_brief") val fBrief: String? = null,
    @SerializedName("f_feature") val fFeature: String? = null,
    @SerializedName("f_function＆application") val fFunctionApplication: String? = null,
    @SerializedName("f_code") val fCode: String? = null,
    @SerializedName("f_pic01_alt") val fPic01Alt: String? = null,
    @SerializedName("f_pic01_url") val fPic01Url: String? = null,
    @SerializedName("f_pic02_alt") val fPic02Alt: String? = null,
    @SerializedName("f_pic02_url") val fPic02Url: String? = null,
    @SerializedName("f_pic03_alt") val fPic03Alt: String? = null,
    @SerializedName("f_pic03_url") val fPic03Url: String? = null,
    @SerializedName("f_pic04_alt") val fPic04Alt: String? = null,
    @SerializedName("f_pic04_url") val fPic04Url: String? = null
)