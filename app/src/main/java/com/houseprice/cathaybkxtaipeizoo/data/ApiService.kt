package com.houseprice.cathaybkxtaipeizoo.data


import com.houseprice.cathaybkxtaipeizoo.data.model.ZooAnimalResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitResult
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooPlantResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    /**
     * 臺北市立動物園館區簡介。
     *
     * @param scope scope。
     * @param query 關鍵字。
     * @param limit 查詢的限制筆數。
     * @param offset 查詢的偏移量。
     * @return 臺北市立動物園館區簡介。
     */
    @GET("/9683ba26-109e-4cb8-8f3d-03d1b349db9f")
    suspend fun getZooExhibit(
        @Query("scope") scope: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ZooExhibitResult>


    /**
     * 臺北市立動物園動物資料。
     *
     * @param scope android裝置識別碼。
     * @param query 關鍵字。
     * @param limit 查詢的限制筆數。
     * @param offset 查詢的偏移量。
     * @return 臺北市立動物園動物資料。
     */
    @GET("/6afa114d-38a2-4e3c-9cfd-29d3bd26b65b")
    suspend fun getZooAnimal(
        @Query("scope") scope: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ZooAnimalResult>

    /**
     * 臺北市立動物園植物資料。
     *
     * @param scope android裝置識別碼。
     * @param query 關鍵字。
     * @param limit 查詢的限制筆數。
     * @param offset 查詢的偏移量。
     * @return 臺北市立動物園植物資料。
     */
    @GET("/e20706d8-bf89-4e6a-9768-db2a10bb2ba4")
    suspend fun getZooPlant(
        @Query("scope") scope: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ZooPlantResult>
}