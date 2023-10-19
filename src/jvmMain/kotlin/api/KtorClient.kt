package api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import util.logError
import util.logSuccess

object KtorClient {

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    val httpClient = HttpClient(Android) {

        engine {
            connectTimeout = 10_000
//            proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 8080))
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 10000
            requestTimeoutMillis = 10000
            connectTimeoutMillis = 10000
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    logError(message)
                }
            }
            level = LogLevel.ALL
        }

//        install(ContentNegotiation) {
//            json(json)
//        }

        install(ResponseObserver) {
            onResponse { response ->
                logSuccess("onResponse: " + response.status.value.toString())
            }
        }

        install(DefaultRequest) {
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//            parameter("api_key", TmdbApiKeys.API_KEY)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

}