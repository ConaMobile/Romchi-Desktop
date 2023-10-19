package splash.api

import api.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import util.BuildConfig

class SplashApiModule(
    private val ktorClient: KtorClient,
) {

    suspend fun checkAppVersion(
        version: Int,
        response: suspend (AppVersion) -> Unit,
        errorsStatusCode: suspend (Throwable?, Int?) -> Unit,
    ): Job {
        var request: HttpResponse? = null
        val handler = CoroutineExceptionHandler { _, exception ->
            CoroutineScope(Dispatchers.Default).launch {
                request?.let {
                    errorsStatusCode.invoke(exception, it.status.value)
                } ?: run { errorsStatusCode.invoke(exception, null) }
            }
        }
        return CoroutineScope(Dispatchers.IO + handler).launch(Dispatchers.IO) {
            request = ktorClient.httpClient.request("${BuildConfig.BASE_URL}api/version/check") {
                setBody(AppVersionRequest(version))
//                header("Accept", BuildConfig.ACCESS_TOKEN)
                method = HttpMethod.Post
            }
            request?.let {
                response.invoke(it.body<AppVersion>())
            }
        }
    }
}