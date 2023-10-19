import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import koin.appModule
import org.koin.compose.KoinApplication
import splash.SplashScreen
import java.awt.Dimension

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme {
            Navigator(SplashScreen)
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Romchi",
        icon = painterResource("icon.svg"),
    ) {
        window.minimumSize = Dimension(400, 600)
        App()
    }
}
