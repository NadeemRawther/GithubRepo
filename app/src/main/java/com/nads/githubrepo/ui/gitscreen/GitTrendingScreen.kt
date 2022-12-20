package com.nads.githubrepo.ui.gitscreen

import android.widget.ProgressBar
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.nads.githubrepo.R
import com.nads.githubrepo.data.models.GitItem
import com.nads.githubrepo.data.persistence.GitTable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun GitTrendingScreen(viewModel:GitViewModel, gitItems:List<GitTable>,paddingValues: PaddingValues) {
    val listState = rememberLazyListState()

    val cards by viewModel.cards.collectAsStateWithLifecycle()
    val expandedCardIds by viewModel.expandedCardUrlList.collectAsStateWithLifecycle()

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp), state = listState,
        modifier = Modifier.padding(paddingValues)) {

            items(gitItems) { gitItem ->
                GitItemScreen(
                    viewModel = viewModel,
                    gitItem = gitItem,
                    expanded = expandedCardIds.contains(gitItem.url)
                )
            }

    }

}

@Composable
fun Header() {
    Text(text = "TRENDING", modifier = Modifier.background(Color.Cyan))
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitItemScreen(viewModel: GitViewModel,gitItem: GitTable,expanded:Boolean) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, start = 10.dp)
        .clip(
            shape = RoundedCornerShape(
                10.dp
            )
        )
        .background(Color(0xfffdedec))
        .padding(10.dp)
        .clickable {
            if (expanded) {
                viewModel.onCardClickedRemove(gitItem.url)

            } else {
                viewModel.onCardClicked(gitItem.url)

            }

        }) {


        Row() {

            AsyncImage(
                model = gitItem.avatar,
                contentDescription = "just a profile avatar image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {

                Text(
                    text = gitItem.author, Modifier.fillMaxWidth()
                )

                Text(
                    text = gitItem.name, Modifier.fillMaxWidth()
                )
            }
        }
        if (expanded){
            Column() {
                Text(
                    text = gitItem.description, Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row() {
                    AsyncImage(
                        model = R.drawable.drawable_dot,
                        modifier = Modifier.padding(top = 5.dp),
                        //colorFilter = ColorFilter.tint(getColor( gitItem.languageColor)),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = gitItem.language
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    AsyncImage(
                        model = R.drawable.star_yellow_16,
                        modifier = Modifier.padding(top = 5.dp),
                        //colorFilter = ColorFilter.tint(getColor( gitItem.languageColor)),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = gitItem.currentPeriodStars.toString()
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    AsyncImage(
                        model = R.drawable.fork_black_16,
                        modifier = Modifier.padding(top = 5.dp),
                        //colorFilter = ColorFilter.tint(getColor( gitItem.languageColor)),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = gitItem.forks.toString()
                    )


                }
            }
         }
    }

}

@Composable
fun ProgressBars(enable:Boolean) {
    val progressValue = 0.99f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(900)))


    if (enable) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator(progress = progressAnimationValue)

            }
         }


}

//@OptIn(ExperimentalMaterialApi::class)
//@Preview
//@Composable
//fun SwipeRefresh() {
//    val refreshScope = rememberCoroutineScope()
//    var refreshing by remember { mutableStateOf(false) }
//    var itemCount by remember { mutableStateOf(15) }
//
//    fun refresh() = refreshScope.launch {
//        refreshing = true
//        delay(1500)
//      //  itemCount += 5
//        refreshing = false
//    }
//
//    val state = rememberPullRefreshState(refreshing, ::refresh)
//
//    Box(Modifier.pullRefresh(state)) {
//        LazyColumn(Modifier.fillMaxSize()) {
//            if (!refreshing) {
//                items(itemCount) {
//                    ListItem { Text(text = "Item ${itemCount - it}") }
//                }
//            }
//        }
//
//        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
//    }
//
//}

@Preview
@Composable
fun SampleGitCard() {
ProgressBars(true)
}