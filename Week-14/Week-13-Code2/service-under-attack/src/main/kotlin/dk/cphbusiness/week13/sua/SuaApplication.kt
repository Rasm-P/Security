package dk.cphbusiness.week13.sua

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import java.text.DateFormat

fun Application.main() {
    install(DefaultHeaders)
    install(CORS) { anyHost() }
    install(Compression)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
            }
        }
    routing {
        get("/hello") {
            call.respond("Hello World")
            }
        post("/attack-me") {
            val body = call.receiveText()
            call.respond("$body received")
            }
        }
    }
