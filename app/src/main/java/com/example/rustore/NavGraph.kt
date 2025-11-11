package com.example.rustore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch

private object Route {
    const val ONBOARDING = "onboarding"
    const val STOREFRONT = "storefront"
    const val CATEGORIES = "categories"
    const val APP_DETAIL = "app/{id}"
    const val GALLERY = "gallery/{id}/{index}"
}

@Composable
fun RuNavHost() {
    val nav = rememberNavController()
    val ctx = LocalContext.current
    val onboarding = remember { OnboardingStore(ctx) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (onboarding.isSeen()) {
            nav.navigate(Route.STOREFRONT) { popUpTo(0) }
        }
    }

    NavHost(navController = nav, startDestination = Route.ONBOARDING) {

        composable(Route.ONBOARDING) {
            OnboardingScreen(
                onContinue = {
                    scope.launch { onboarding.setSeen(true) }
                    nav.navigate(Route.STOREFRONT) {
                        popUpTo(Route.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.STOREFRONT) {
            StorefrontScreen(
                onOpenApp = { id -> nav.navigate("app/$id") },
                onOpenCategories = { nav.navigate(Route.CATEGORIES) }
            )
        }

        composable(Route.CATEGORIES) {
            CategoriesScreen(
                onBack = { nav.popBackStack() },
                onSelectCategory = { _ -> nav.popBackStack() }
            )
        }

        composable(
            route = Route.APP_DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("id") ?: return@composable
            AppDetailScreen(
                appId = id,
                onBack = { nav.popBackStack() },
                onOpenScreenshot = { ix -> nav.navigate("gallery/$id/$ix") }
            )
        }

        composable(
            route = Route.GALLERY,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("index") { type = NavType.IntType }
            )
        ) { backStack ->
            val id = backStack.arguments?.getString("id") ?: return@composable
            val index = backStack.arguments?.getInt("index") ?: 0
            FullscreenGalleryScreen(
                appId = id,
                startIndex = index,
                onBack = { nav.popBackStack() }
            )
        }
    }
}
