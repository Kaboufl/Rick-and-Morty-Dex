package fr.epsi.laurence.paquereau.rickmortydex.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.epsi.laurence.paquereau.rickmortydex.network.RickAndMortyApiDataSource
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {
    private val apiService = RickAndMortyApiDataSource.dataSource
    val characters: MutableState<List<Character>> = mutableStateOf(emptyList())

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val response = apiService.getCharacters()
                val data = response.body()
                if (data == null) {
                    throw Exception("Empty response ! D:")
                } else {
                    characters.value = data.results
                }

            } catch (e: Exception) {
                Log.e("HTTP", e.toString())
            }
        }
    }
}