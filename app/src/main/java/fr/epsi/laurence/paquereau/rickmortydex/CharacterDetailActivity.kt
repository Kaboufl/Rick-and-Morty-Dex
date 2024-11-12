package fr.epsi.laurence.paquereau.rickmortydex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.epsi.laurence.paquereau.rickmortydex.model.Character
import fr.epsi.laurence.paquereau.rickmortydex.ui.theme.RickMortyDexTheme
import androidx.compose.material3.ExperimentalMaterial3Api

class CharacterDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val character = intent.getParcelableExtra<Character>("character")
        setContent {
            RickMortyDexTheme {
                character?.let {
                    CharacterDetailScreen(it) {
                        finish()
                    }
                } ?: run {
                    Text(text = "Détails du personnage non disponibles")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(character: Character, onBackClick: () -> Unit) {
    Column {
        TopAppBar(
            title = { Text(text = character.name) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Retour")
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Statut: ${character.status}")
            Text(text = "Espèce: ${character.species}")
            Text(text = "Type: ${character.type}")
            Text(text = "Genre: ${character.gender}")
            Text(text = "Origine: ${character.origin.name}")
            Text(text = "Localisation: ${character.location.name}")
        }
    }
}