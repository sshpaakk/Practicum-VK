package com.example.rustore

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(
    appId: String,
    onBack: () -> Unit,
    onOpenScreenshot: (Int) -> Unit
) {
    val repo = remember { Repository() }
    val app = repo.getById(appId) ?: return
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(app.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(Modifier.padding(16.dp)) {
                when {
                    app.iconRes != null -> Image(
                        painter = painterResource(app.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    app.iconUrl != null -> AsyncImage(
                        model = app.iconUrl,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    else -> Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(Modifier.width(16.dp))

                Column(Modifier.weight(1f)) {
                    Text(app.name, style = MaterialTheme.typography.titleLarge)
                    Text(app.company, style = MaterialTheme.typography.bodyMedium)
                    Text("Возраст: ${app.ageRating}", style = MaterialTheme.typography.labelMedium)
                }

                Button(
                    onClick = { app.apkUrl?.let { Installer.installApk(context, it) } },
                    enabled = app.apkUrl != null
                ) { Text("Установить") }
            }

            Text("Категория: ${categoryTitle(app.category)}", modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(Modifier.height(12.dp))

            Text("Скриншоты", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp))

            LazyRow(contentPadding = PaddingValues(16.dp)) {
                itemsIndexed(app.screenshots) { index, resId ->
                    Image(
                        painter = painterResource(resId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(180.dp)
                            .clickable { onOpenScreenshot(index) }
                    )
                    Spacer(Modifier.width(12.dp))
                }
            }

            Spacer(Modifier.height(12.dp))
            Text("Описание", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp))
            Text(app.fullDescription, modifier = Modifier.padding(16.dp))
        }
    }
}
