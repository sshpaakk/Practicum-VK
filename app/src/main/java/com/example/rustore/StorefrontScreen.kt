package com.example.rustore

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorefrontScreen(
    onOpenApp: (String) -> Unit,
    onOpenCategories: () -> Unit
) {
    val repo = remember { Repository() }
    var selected: CategoryId? by remember { mutableStateOf(null) }
    val apps = remember(selected) { repo.getAll(selected) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("RuStore") },
                actions = {
                    TextButton(onClick = onOpenCategories) { Text("Категории") }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(apps) { app -> AppCard(app) { onOpenApp(app.id) } }
        }
    }
}

@Composable
private fun AppCard(app: AppItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(Modifier.padding(16.dp)) {

            when {
                app.iconRes != null -> Image(
                    painter = painterResource(app.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
                app.iconUrl != null -> AsyncImage(
                    model = app.iconUrl,
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
                else -> Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(Modifier.weight(1f)) {
                Text(app.name, style = MaterialTheme.typography.titleMedium)
                Text(app.shortDescription, style = MaterialTheme.typography.bodyMedium)
                Text(categoryTitle(app.category), style = MaterialTheme.typography.labelMedium)
            }

            Text(app.ageRating, style = MaterialTheme.typography.labelLarge)
        }
    }
}
