package util

sealed interface WaitType {
    object Default : WaitType
    object Loading : WaitType
    object Success : WaitType
    object Failure : WaitType
    data class Error(val message: String? = null) : WaitType
}