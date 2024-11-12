package fr.epsi.laurence.paquereau.rickmortydex.network

import fr.epsi.laurence.paquereau.rickmortydex.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyCharacterService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharactersResponse>
}