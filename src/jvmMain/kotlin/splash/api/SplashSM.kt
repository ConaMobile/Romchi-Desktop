package splash.api

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.BuildConfig
import util.WaitType
import util.logcat

class SplashSM(
    private val api: SplashApiModule,
) : ScreenModel {

    val versionHandle = Channel<WaitType>()
    val testSplash = Channel<WaitType>()
    private lateinit var job: Job

    init {
        testSplash()
//        checkVersion()
    }

    fun testSplash() = coroutineScope.launch {
        delay(4000)
        testSplash.send(WaitType.Success)
    }

    fun checkVersion() = coroutineScope.launch(Dispatchers.IO) {
        versionHandle.send(WaitType.Loading)
        job = api.checkAppVersion(version = BuildConfig.VERSION_CODE,
            response = {
                if (it.success) versionHandle.send(WaitType.Success)
                else versionHandle.send(WaitType.Failure)
                versionHandle.close()
            },
            errorsStatusCode = { exception, statusCode ->
                logcat("statusCode: $statusCode")
                statusCode?.let { code ->
                    when (code) {
                        in (300..399) -> {}
                        in (400..499) -> {}
                        in (500..599) -> {}
                        else -> {}
                    }
                }
                versionHandle.send(WaitType.Error())
                versionHandle.close()
            })
    }

    override fun onDispose() {
//        job.cancel()
        super.onDispose()
    }
}