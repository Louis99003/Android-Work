package com.houseprice.cathaybkxtaipeizoo.data.usecase

import com.houseprice.cathaybkxtaipeizoo.data.NetworkResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooAnimalResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooPlantResult
import com.houseprice.cathaybkxtaipeizoo.data.remote.RemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UseCaseRepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : UseCaseRepository {
    override suspend fun getZooAnimal(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooAnimalResult>> {
        return remoteRepository.getZooAnimal(query, limit, offset).flowOn(ioDispatcher)
    }

    override suspend fun getZooExhibit(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooExhibitResult>> {
        return remoteRepository.getZooExhibit(query, limit, offset).flowOn(ioDispatcher)
    }

    override suspend fun getZooPlant(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooPlantResult>> {
        return remoteRepository.getZooPlant(query, limit, offset).flowOn(ioDispatcher)
    }

}