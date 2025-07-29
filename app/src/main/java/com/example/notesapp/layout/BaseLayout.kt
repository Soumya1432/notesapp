// layout/BaseLayout.kt
package com.example.notesapp.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BaseLayout(
    navController: NavController,
    onMenuClick: () ->Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fixed Top Navbar
        Navbar()

        // Main Content in Middle
        Box(
            modifier = Modifier
                .weight(1f)

//                .padding(
//                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
//                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
//                )
        ) {
            content()
        }

        // Fixed Bottom Footer
        Footer(navController = navController,onMenuClick=onMenuClick)
    }
}
