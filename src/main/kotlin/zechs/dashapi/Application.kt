package zechs.dashapi

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level
import zechs.dashapi.repository.DriveRepository
import zechs.dashapi.routes.streamsRoute

fun main() {

    val port = System.getenv("PORT") ?: "6800"

    val driveRepository = DriveRepository()

    embeddedServer(Netty, port = port.toInt(), host = "0.0.0.0") {

        install(ContentNegotiation) { gson() }

        install(CallLogging) {
            level = Level.INFO
            filter { call -> call.request.path().startsWith("/") }
        }

        streamsRoute(driveRepository)

    }.start(wait = true)

}
