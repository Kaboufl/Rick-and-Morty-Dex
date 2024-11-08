package fr.epsi.laurence.paquereau.rickmortydex.network

import fr.epsi.laurence.paquereau.rickmortydex.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyCharacterService {
    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponse>
}