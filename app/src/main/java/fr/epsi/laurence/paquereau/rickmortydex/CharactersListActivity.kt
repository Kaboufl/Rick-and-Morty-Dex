package fr.epsi.laurence.paquereau.rickmortydex

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import fr.epsi.laurence.paquereau.rickmortydex.model.Character
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
fun CharactersList(viewModel: CharactersViewModel, modifier: Modifier) {
    val characters by viewModel.characters
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
    ) {
        items(characters) { character ->
            CharacterItem(character, context)
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

@Composable
fun CharacterItem(character: Character, context: android.content.Context) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val intent = Intent(context, CharacterDetailActivity::class.java).apply {
                    putExtra("character", character)
                }
                context.startActivity(intent)
            }
    ) {
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