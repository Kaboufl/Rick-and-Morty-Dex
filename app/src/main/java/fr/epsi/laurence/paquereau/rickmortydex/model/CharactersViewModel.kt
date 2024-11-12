package fr.epsi.laurence.paquereau.rickmortydex.model

import android.util.Log
import android.util.MutableBoolean
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.epsi.laurence.paquereau.rickmortydex.network.RickAndMortyApiDataSource
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {
    private val apiService = RickAndMortyApiDataSource.dataSource
    val characters: MutableState<List<Character>> = mutableStateOf(emptyList())
    private val pageIndex: MutableIntState = mutableIntStateOf(1)
    val isMoreCharacters: MutableState<Boolean> = mutableStateOf(true)

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val response = apiService.getCharacters(pageIndex.intValue)
                val data = response.body() ?: throw Exception("Empty response ! D:")

                characters.value += data.results
                // characters.value = data.results

                if (data.info.next != null) {
                    pageIndex.value += 1
                } else {
                    isMoreCharacters.value = false
                }

            } catch (e: Exception) {
                Log.e("HTTP", e.toString())
            }
        }
    }
}