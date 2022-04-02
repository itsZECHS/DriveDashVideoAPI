package zechs.dashapi.repository

import zechs.dashapi.api.RetrofitInstance
import zechs.dashapi.models.DashResponse
import zechs.dashapi.utils.DashResponseParser

class DriveRepository {

    suspend fun getVideoInfo(
        fileId: String,
        accessToken: String?
    ): DashResponse {

        val response = RetrofitInstance.drive.getVideoInfo(
            fileId = fileId,
            accessToken = if (accessToken != null) "Bearer $accessToken" else null
        )

        val cookiesList = response.headers().values("Set-Cookie")

        val cookie = cookiesList[0]
            .split(";")
            .toTypedArray()
            .firstOrNull { it.contains("DRIVE_STREAM") }
            ?.split("=")?.toTypedArray()?.get(1)
            ?: return DashResponse(
                error = "Unable to get DRIVE_STREAM cookie",
                streams = emptyList()
            )

        return DashResponseParser.parse(response.body(), cookie)
    }

}