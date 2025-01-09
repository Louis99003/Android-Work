package com.houseprice.cathaybkxtaipeizoo.data.usecase

import com.houseprice.cathaybkxtaipeizoo.data.NetworkResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooAnimalResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooPlantResult
import kotlinx.coroutines.flow.Flow

interface UswCaseRepository {
    suspend fun getZooAnimal(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooAnimalResult>>

    suspend fun getZooExhibit(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooExhibitResult>>

    suspend fun getZooPlant(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooPlantResult>>
}