package zechs.dashapi.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import zechs.dashapi.models.DashResponse
import zechs.dashapi.repository.DriveRepository


fun Application.streamsRoute(
    driveRepository: DriveRepository
) {
    routing {
        route("/streams", HttpMethod.Get) {
            handle {
                val params = call.request.queryParameters
                val fileId = params["fileId"]
                val accessToken = params["accessToken"]

                if (fileId == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        DashResponse(error = "fileId is required", streams = emptyList())
                    )
                } else {
                    val dashResponse = driveRepository.getVideoInfo(fileId, accessToken)
                    val responseCode = if (dashResponse.error == "Access denied") {
                        HttpStatusCode.Forbidden
                    } else HttpStatusCode.OK
                    call.respond(responseCode, dashResponse)
                }

            }
        }
    }
}
