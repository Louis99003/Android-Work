package com.houseprice.cathaybkxtaipeizoo.data.usecase

import com.houseprice.cathaybkxtaipeizoo.data.NetworkResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooAnimalResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooPlantResult
import kotlinx.coroutines.flow.Flow

interface UseCaseRepository {

    /**
     * 臺北市立動物園動物資料。
     *
     * @param query 關鍵字。
     * @param limit 查詢的限制筆數。
     * @param offset 查詢的偏移量。
     * @return 臺北市立動物園動物資料。
     */
    suspend fun getZooAnimal(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooAnimalResult>>

    /**
     * 臺北市立動物園館區簡介。
     *
     * @param query 關鍵字。
     * @param limit 查詢的限制筆數。
     * @param offset 查詢的偏移量。
     * @return 臺北市立動物園館區簡介。
     */
    suspend fun getZooExhibit(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooExhibitResult>>

    /**
     * 臺北市立動物園植物資料。
     *
     * @param query 關鍵字。
     * @param limit 查詢的限制筆數。
     * @param offset 查詢的偏移量。
     * @return 臺北市立動物園植物資料。
     */
    suspend fun getZooPlant(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooPlantResult>>
}