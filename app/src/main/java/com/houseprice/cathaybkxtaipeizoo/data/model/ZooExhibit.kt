package com.houseprice.cathaybkxtaipeizoo.data.model

import com.google.gson.annotations.SerializedName

/**
 * 代表動物園展區查詢的結果。
 *
 * @param result 動物園展區的資料。
 */
data class ZooExhibitResult(
    val result: ZooExhibitData? = null
)

/**
 * 代表動物園展區的資料。
 *
 * @param limit 查詢的限制筆數。
 * @param offset 查詢的偏移量。
 * @param count 查詢結果的數量。
 * @param sort 查詢的排序方式。
 * @param results 展區項目的列表。
 */
data class ZooExhibitData(
    val limit: Int = 0,
    val offset: Int = 0,
    val count: Int = 0,
    val sort: String = "",
    val results: MutableList<ExhibitItem>? = null
)

/**
 * 代表一個展區項目。
 *
 * @param id 展區的 ID。
 * @param importDate 展區的導入日期。
 * @param eNo 展區編號。
 * @param eCategory 展區類別。
 * @param eName 展區名稱。
 * @param ePicUrl 展區圖片 URL。
 * @param eInfo 展區資訊。
 * @param eMemo 展區備註。
 * @param eGeo 展區地理位置。
 * @param eUrl 展區 URL。
 */
data class ExhibitItem(
    @SerializedName("_id") val id: Int = 0,
    @SerializedName("_importdate") val importDate: ImportDate? = null,
    @SerializedName("e_no") val eNo: String? = null,
    @SerializedName("e_category") val eCategory: String? = null,
    @SerializedName("e_name") val eName: String? = null,
    @SerializedName("e_pic_url") val ePicUrl: String? = null,
    @SerializedName("e_info") val eInfo: String? = null,
    @SerializedName("e_memo") val eMemo: String? = null,
    @SerializedName("e_geo") val eGeo: String? = null,
    @SerializedName("e_url") val eUrl: String? = null
)

/**
 * 代表展區項目的導入日期。
 *
 * @param date 導入日期。
 * @param timezone_type 時區類型。
 * @param timezone 時區。
 */
data class ImportDate(
    val date: String? = null,
    val timezone_type: Int? = null,
    val timezone: String? = null
)