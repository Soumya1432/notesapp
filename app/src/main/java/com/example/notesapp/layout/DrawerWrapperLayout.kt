package com.example.notesapp.layout


import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DrawerWrapperLayout(
    navController: NavController,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth: Dp = screenWidth *0.7f

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true, // allows swipe to close
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(drawerWidth)) {
                DrawerContent(
                    onDestinationClick = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route)
                    }
                )
            }
        }
    ) {
        // Pass `onMenuClick` down to BaseLayout -> Footer
        BaseLayout(
            navController = navController,
            onMenuClick = { scope.launch { drawerState.open() } }
        ) {
            content()
        }
    }
}
