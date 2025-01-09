package com.houseprice.cathaybkxtaipeizoo.data.remote

import com.houseprice.cathaybkxtaipeizoo.data.ApiService
import com.houseprice.cathaybkxtaipeizoo.data.NetworkResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ErrorModel
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooAnimalResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooPlantResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteRepository {

    val scope = "resourceAquire"

    override suspend fun getZooAnimal(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooAnimalResult>> = flow {
        emit(handleApi {
            apiService.getZooAnimal(
                scope = scope,
                query = query,
                limit = limit,
                offset = offset
            )
        })
    }

    override suspend fun getZooExhibit(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooExhibitResult>> = flow {
        emit(handleApi {
            apiService.getZooExhibit(
                scope = scope,
                query = query,
                limit = limit,
                offset = offset
            )
        })
    }

    override suspend fun getZooPlant(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<ZooPlantResult>> = flow {
        emit(handleApi {
            apiService.getZooPlant(
                scope = scope,
                query = query,
                limit = limit,
                offset = offset
            )
        })
    }

    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful) {
                NetworkResult.Success(body)
            } else {
                val errorBody = response.errorBody()
                if (errorBody == null) {
                    NetworkResult.Error(
                        ErrorModel(
                            code = response.code(),
                            description = response.message()
                        )
                    )
                } else {
                    NetworkResult.Error(
                        ErrorModel(
                            code = response.code(),
                            description = errorBody.string()
                        )
                    )
                }

            }
        } catch (e: Exception) {
            NetworkResult.Error(
                ErrorModel(
                    description = e.message.toString()
                )
            )
        }
    }
}