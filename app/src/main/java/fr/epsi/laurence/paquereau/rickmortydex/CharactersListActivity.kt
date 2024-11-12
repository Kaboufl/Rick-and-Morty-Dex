package fr.epsi.laurence.paquereau.rickmortydex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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
        items(characters) { character ->
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = character.name)
                AsyncImage(
                    model = character.image,
                    contentDescription = "Image de ${character.name}",
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    DisposableEffect(Unit) {
        viewModel.getCharacters()
        onDispose {  }
    }
}
