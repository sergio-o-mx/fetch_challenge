package mxrampage.fetchsample.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mxrampage.fetchsample.BuildConfig
import mxrampage.fetchsample.rest.services.ItemsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor {
            Log.d("OkHttp", it)
        }
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .header("Content-Type", "application/json")
                .header("accept", "text/html")
                .header("accept", "application/xhtml+xml")
                .header("accept", "application/xml")
                .url(BuildConfig.BASE_URL)
            return@addInterceptor chain.proceed(request.build())
        }
        .retryOnConnectionFailure(true)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideAPIService(retrofit: Retrofit): ItemsService =
        retrofit.create(ItemsService::class.java)
}
