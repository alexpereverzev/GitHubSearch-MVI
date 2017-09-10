package project.test.ru.githubsearch.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import project.test.ru.githubsearch.api.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.facebook.stetho.okhttp3.StethoInterceptor



@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Api {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                        .addNetworkInterceptor(StethoInterceptor())
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(Api::class.java)
    }
}