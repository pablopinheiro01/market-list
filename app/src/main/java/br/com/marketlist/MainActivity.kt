package br.com.marketlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import br.com.marketlist.navigation.MarkListNavHost
import br.com.marketlist.navigation.navigateToFormMarketListScreen
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.screens.HomeListViewMarketScreen
import br.com.marketlist.ui.theme.MarketListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    Scaffold() {
        Box(modifier = modifier.padding(it)) {
            val navController = rememberNavController()
            MarkListNavHost(navController = navController)

        }
    }

}


@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun AppPreview() {
    MarketListTheme {
        App()
    }
}
