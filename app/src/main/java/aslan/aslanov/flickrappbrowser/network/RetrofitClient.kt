package aslan.aslanov.flickrappbrowser.network

import aslan.aslanov.flickrappbrowser.network.services.FlickerService
import aslan.aslanov.flickrappbrowser.util.NetworkConstant.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private var retrofit: Retrofit? = null

    private fun getClientHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val url = original
                    .url
                    .newBuilder()
                    //.addQueryParameter("key", API_KEY)
                    .build()
                val request = original
                    .newBuilder()
                    .url(url)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    private fun getClient(): Retrofit {
        when (retrofit) {
            null -> {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .baseUrl(BASE_URL)
                    .build()

            }
        }
        return retrofit as Retrofit
    }

    val flickerService: FlickerService by lazy { getClient().create(FlickerService::class.java) }
}