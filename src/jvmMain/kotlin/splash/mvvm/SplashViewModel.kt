package splash.mvvm

import api.KtorClient
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import splash.api.AppVersion
import splash.api.AppVersionRequest
import util.BuildConfig
import java.net.ConnectException

class SplashViewModel(private val ktorClient: KtorClient) : ViewModel() {

    //    private val ktorClient: KtorClient by inject()
//    private val ktorClient = koinInject<KtorClient>()

    fun checkAppVersion(
        version: Int,
        response: (Boolean) -> Unit,
        error: (String) -> Unit = {},
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val request =
                ktorClient.httpClient.request("${BuildConfig.BASE_URL}api/version/check") {
                    setBody(AppVersionRequest(version))
                    method = HttpMethod.Post
                }
            if (request.status.isSuccess()) response.invoke(request.body<AppVersion>().success)
//            else error(context.resources.getString(R.string.unknown_error))
            else error("Unknown Error")
        } catch (e: ConnectException) {
            error("Network Error")
        } catch (e: Exception) {
            error("Unknown Error")
        }
    }
}