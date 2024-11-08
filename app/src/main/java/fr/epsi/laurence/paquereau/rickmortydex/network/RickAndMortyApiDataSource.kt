package fr.epsi.laurence.paquereau.rickmortydex.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyApiDataSource {
    companion object {
        private val retrofit: Retrofit by lazy {
            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rickandmortyapi.com/api/")
                .client(client)
                .build()
        }
        val dataSource: RickAndMortyCharacterService by lazy {
            retrofit.create(RickAndMortyCharacterService::class.java)
        }
    }
}