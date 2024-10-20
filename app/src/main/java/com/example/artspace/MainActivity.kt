package com.example.artspace
import androidx.compose.ui.res.painterResource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

data class Artwork(val id: Int, val title: String, val artist: String, val year: String, val image: Painter)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtGallery()
                }
            }
        }
    }
}

@Composable
fun ArtGallery() {
    val artworks = listOf(
        Artwork(1, "Starry Night", "Vincent van Gogh", "1889", painterResource(id = R.drawable.starry_night)),
        Artwork(2, "Mona Lisa", "Leonardo da Vinci", "1503", painterResource(id = R.drawable.mona_lisa)),
        Artwork(3, "The Scream", "Edvard Munch", "1893", painterResource(id = R.drawable.the_scream))
    )

    var currentArtworkIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentArtworkIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = currentArtwork.image,
            contentDescription = currentArtwork.title,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        )
        Text(text = currentArtwork.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Artist: ${currentArtwork.artist}", fontSize = 18.sp)
        Text(text = "Year: ${currentArtwork.year}", fontSize = 18.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                currentArtworkIndex = if (currentArtworkIndex > 0) currentArtworkIndex - 1 else artworks.lastIndex
            }) {
                Text(text = "Previous")
            }
            Button(onClick = {
                currentArtworkIndex = if (currentArtworkIndex < artworks.lastIndex) currentArtworkIndex + 1 else 0
            }) {
                Text(text = "Next")
            }
        }
    }
}
