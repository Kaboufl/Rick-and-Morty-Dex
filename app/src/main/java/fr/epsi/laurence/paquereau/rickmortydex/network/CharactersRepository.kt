package fr.epsi.laurence.paquereau.rickmortydex.network

class CharactersRepository() {
    suspend fun getCharacters() = RickAndMortyApiDataSource.dataSource.getCharacters()
}