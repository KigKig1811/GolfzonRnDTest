package com.xt.core_data.remote

import com.xt.core_data.dto.ImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("v1/images/search?")
    suspend fun fetchImages(
        @Query("page") page: Int,
        @Query("limit") size: Int,
    ) : Response<List<ImageDto>>
}
