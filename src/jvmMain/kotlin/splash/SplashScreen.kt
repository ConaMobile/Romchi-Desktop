package splash

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
//        val viewModel =
        Column {
            Text("SplashScreen")
            Button(onClick = { navigator.push(HomeScreen) }) {
                Text("Button")
            }
        }

    }
}

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column {
            Text("HomeScreen")
            Button(onClick = { navigator.pop() }){
                Text("Back")
            }
        }
    }
}