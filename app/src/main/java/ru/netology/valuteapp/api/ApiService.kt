package ru.netology.valuteapp.api


import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import ru.netology.valuteapp.dto.FeedValute
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://www.cbr-xml-daily.ru/"
private val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(ApiInterceptor())
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

interface ValutesApiService {
    @GET("daily_json.js")
    suspend fun getAll(): FeedValute
}

object ValutesApi {
    val retrofitService: ValutesApiService by lazy(retrofit::create)
}

