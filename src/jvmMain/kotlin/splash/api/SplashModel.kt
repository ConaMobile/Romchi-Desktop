package splash.api

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AppVersion(val success: Boolean)

@Keep
@Serializable
data class AppVersionRequest(val version: Int)