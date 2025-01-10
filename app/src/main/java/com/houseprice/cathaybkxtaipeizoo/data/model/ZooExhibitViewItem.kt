package com.houseprice.cathaybkxtaipeizoo.data.model

/**
 *
 * @param id 展區的 ID。
 * @param eNo 展區編號。
 * @param eCategory 展區類別。
 * @param eName 展區名稱。
 * @param ePicUrl 展區圖片 URL。
 * @param eInfo 展區資訊。
 * @param eMemo 展區備註。
 */
data class ZooExhibitViewItem(
    val id: Int = 0,
    val eNo: String = "",
    val eCategory: String = "",
    val eName: String = "",
    val ePicUrl: String = "",
    val eInfo: String = "",
    val eMemo: String = "",
    val eUrl: String = ""
)
