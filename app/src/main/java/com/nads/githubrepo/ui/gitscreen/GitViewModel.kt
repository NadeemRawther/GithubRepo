package com.nads.githubrepo.ui.gitscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nads.githubrepo.data.models.BuiltBy
import com.nads.githubrepo.data.models.GitItem
import com.nads.githubrepo.data.repo.GithubRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GitViewModel @Inject constructor(private val repository:
                                       GithubRepo): ViewModel() {

    private val _cards = MutableStateFlow(listOf<GitItem>())
    val cards: StateFlow<List<GitItem>> get() = _cards
    private val _loading = MutableStateFlow(true)
    val loading: MutableStateFlow<Boolean> get() = _loading
    private val _online = MutableStateFlow(true)
    val online: MutableStateFlow<Boolean> get() = _online
    private val _error = MutableStateFlow(false)
    val error: MutableStateFlow<Boolean> get() = _error
    private val _expandedCardUrlList = MutableStateFlow(listOf<String>())
    val expandedCardUrlList: StateFlow<List<String>> get() = _expandedCardUrlList

    init {
          _loading.value = true
         viewModelScope.launch {
             val result = repository.getGitItemList()
             if (result.isSuccess){
                 result.map {
                     it->
                     _cards.emit(it)
                 }
             }else{
                 _error.value = true
             }
             _loading.value = false

         }
    }
    fun refresh(){
        _loading.value = true
        viewModelScope.launch {
            val result = repository.getGitItemList()

            if (result.isSuccess){
                Log.e("SDFSD",result.toString())
                result.map {
                        it->
                    _cards.emit(it)
                }
            }else{
                _error.value = true
            }
            _loading.value = false

        }
    }
    fun onCardClicked(url:String) {
        _expandedCardUrlList.value = _expandedCardUrlList.value.toMutableList().also { list ->
            list.clear()
            //if (list.contains(url)) list.remove(url) else
            list.add(url)
        }
    }
    fun onCardClickedRemove(url:String) {
        _expandedCardUrlList.value = _expandedCardUrlList.value.toMutableList().also { list ->
            list.clear()
            //if (list.contains(url)) list.remove(url) else
        }
    }
    companion object{
        val gitarr = arrayListOf<GitItem>(GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),
            GitItem(author="xingshaocheng",name="architect-awesome",avatar="https://github.com/xingshaocheng.png",
        url="https://github.com/xingshaocheng/architect-awesome",description="后端架构师技术图谱",
        language="Go",languageColor="#3572A5",stars= 7333,forks=1546,currentPeriodStars=1528,
        builtBy = arrayListOf(BuiltBy (href ="https://github.com/viatsko",avatar="https://avatars0.githubusercontent.com/u/376065",
            username="viatsko"))),
            GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )),GitItem(author="kusti8",name="proton-native",
            avatar="https://github.com/kusti8.png",url="https://github.com/kusti8/proton-native",
            description="A React environment for cross platform native desktop apps",
            language="JavaScript",languageColor="#3572A5",stars=4711,forks=124,currentPeriodStars=1186,
            builtBy= arrayListOf<BuiltBy>(
                BuiltBy( href ="https://github.com/viatsko",
                avatar="https://avatars0.githubusercontent.com/u/376065",
                username ="viatsko")
            )))


//            fun getColor(colorString: String): androidx.compose.ui.graphics.Color {
//                return Color(android.graphics.Color.parseColor("#" + colorString))
//            }

    }
}