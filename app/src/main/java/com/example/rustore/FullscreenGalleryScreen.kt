package com.example.rustore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullscreenGalleryScreen(
    appId: String,
    startIndex: Int,
    onBack: () -> Unit
) {
    val repo = remember { Repository() }
    val app = repo.getById(appId) ?: return
    var index by remember { mutableIntStateOf(startIndex.coerceIn(0, app.screenshots.lastIndex)) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(app.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            Image(
                painter = painterResource(app.screenshots[index]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { index = (index - 1).coerceAtLeast(0) },
                    enabled = index > 0
                ) { Text("Назад") }

                OutlinedButton(
                    onClick = { index = (index + 1).coerceAtMost(app.screenshots.lastIndex) },
                    enabled = index < app.screenshots.lastIndex
                ) { Text("Далее") }
            }
        }
    }
}
