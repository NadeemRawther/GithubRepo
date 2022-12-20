package com.nads.githubrepo.di



import android.content.Context
import androidx.room.Room
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.nads.githubrepo.data.apiservice.GitHubRepoService
import com.nads.githubrepo.data.repo.GitHubDefaultRep
import com.nads.githubrepo.data.repo.GithubRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GitHubRepoModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor.invoke { chain: Interceptor.Chain ->
                val orginal: Request = chain.request()
                val request = orginal.newBuilder().method(
                    orginal.method,
                    orginal.body
                )
                    .build()
                chain.proceed(request)
            }).build()
        return client
    }
    @Singleton
    @Provides
    fun providesRetrofitClient(): GitHubRepoService {
//        val gson = GsonBuilder()
//            .registerTypeAdapter(Int::class.javaPrimitiveType, IntTypeAdapter())
//            .registerTypeAdapter(Int::class.java, IntTypeAdapter()).create()
        return Retrofit.Builder()
            .baseUrl("https://63995c2dfe03352a94ecf489.mockapi.io/")
            .client(providesOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubRepoService::class.java)
    }
//yes sir
    @Singleton
    @Provides
    fun provideRepository(service:GitHubRepoService/*,gitHubDao: GitHubDao*/,caroutinedispatchers:CoroutineDispatcher): GithubRepo{
        return GitHubDefaultRep(service/*,gitHubDao*/,caroutinedispatchers)
    }

    @Singleton
    @Provides
    fun provideCoroutine(): CoroutineDispatcher {
        return Dispatchers.IO
    }

//    @Provides
//    fun provideGitDao(gitHubDatabase: GitHubDatabase): GitHubDao {
//        return gitHubDatabase.gitHubDao()
//    }
//
//    @Singleton
//    @Provides
//    fun provideDataBase(@ApplicationContext context: Context): GitHubDatabase {
//        return Room.databaseBuilder(
//            context,
//            GitHubDatabase::class.java, "gitTable"
//        ).build()
//    }



    class IntTypeAdapter : TypeAdapter<Number?>() {
        @Throws(IOException::class)
        override fun write(out: JsonWriter, value: Number?) {
            out.value(value)
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): Number? {
            if (`in`.peek() == JsonToken.NULL) {
                `in`.nextNull()
                return null
            }
            return try {
                val result = `in`.nextString()
                if ("" == result) {
                    null
                } else result.toInt()
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }
        }
    }
}