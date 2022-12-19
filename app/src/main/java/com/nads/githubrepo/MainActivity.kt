package com.nads.githubrepo


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.nads.githubrepo.ui.gitscreen.GitTrendingScreen
import com.nads.githubrepo.ui.gitscreen.GitViewModel
import com.nads.githubrepo.ui.gitscreen.ProgressBars
import com.nads.githubrepo.ui.theme.GitHubRepoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val gitViewModel:GitViewModel  by viewModels()
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            GitHubRepoTheme {

                Scaffold(
                    topBar = {
                        TopAppBar() {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "TRENDING")
                            }
                        }
                    },
                    content = {
                        val onLine = isOnline()
                        val loading by gitViewModel.loading.collectAsState()
                        val error by gitViewModel.error.collectAsState()
                        val swiprerefreshstate = rememberSwipeRefreshState(isRefreshing = loading)
                        if (loading) ProgressBars(true) else ProgressBars(false)
                        val gitFlow = gitViewModel.cards.collectAsState()
                        if (!gitFlow.value.isEmpty()) {
                          SwipeRefresh(state = swiprerefreshstate, onRefresh = {gitViewModel.refresh() }) {
                              GitTrendingScreen(viewModel = gitViewModel, gitItems = gitFlow.value)
                          }
                        }
                        else if(error)
                            {ErrorScreen()}
                    },

                )
            }
        }
    }
    fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}

@Composable
fun ErrorScreen() {
    AsyncImage(model = R.drawable.nointernet_connection,
        contentDescription = "No Internet Connection",
        modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitHubRepoTheme {
        ErrorScreen()
    }
}