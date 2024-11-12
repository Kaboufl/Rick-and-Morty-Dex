package fr.epsi.laurence.paquereau.rickmortydex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.epsi.laurence.paquereau.rickmortydex.model.CharactersViewModel
import fr.epsi.laurence.paquereau.rickmortydex.ui.theme.RickMortyDexTheme

class CharactersListActivity : ComponentActivity() {
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickMortyDexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharactersList(viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    RickMortyDexTheme {
        Greeting2("Android")
    }
}

@Composable
fun CharactersList(viewModel: CharactersViewModel, modifier: Modifier) {
    val characters by viewModel.characters

    LazyColumn(
        modifier = modifier
    ) {
        items(characters) {
            character ->
                Text(text = character.name)
        }
        if (viewModel.isMoreCharacters.value) {
            item {
                IconButton({
                    viewModel.getCharacters()
                }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Charger plus")
                }
            }
        }
    }

    DisposableEffect(Unit) {
        viewModel.getCharacters()
        onDispose {  }
    }
}