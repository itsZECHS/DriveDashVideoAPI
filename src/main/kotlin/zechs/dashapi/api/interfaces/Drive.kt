package zechs.dashapi.api.interfaces

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface Drive {

    @Headers("Content-Type: application/json")
    @GET("/get_video_info")
    suspend fun getVideoInfo(
        @Header("Authorization")
        accessToken: String? = null,
        @Query("docid")
        fileId: String
    ): Response<String>

}