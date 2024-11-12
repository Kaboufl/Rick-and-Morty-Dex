package fr.epsi.laurence.paquereau.rickmortydex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.epsi.laurence.paquereau.rickmortydex.model.Character
import fr.epsi.laurence.paquereau.rickmortydex.ui.theme.RickMortyDexTheme

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

@Composable
fun CharacterDetailScreen(character: Character, onBackClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Retour")
            }
            Text(text = "Détails du personnage")
        }
        Text(text = "Nom: ${character.name}")
        Text(text = "Statut: ${character.status}")
        Text(text = "Espèce: ${character.species}")
        Text(text = "Type: ${character.type}")
        Text(text = "Genre: ${character.gender}")
        Text(text = "Origine: ${character.origin.name}")
        Text(text = "Localisation: ${character.location.name}")
    }
}