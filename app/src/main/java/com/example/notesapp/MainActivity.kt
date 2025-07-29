package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.notesapp.navigation.AppNavigation
import com.example.notesapp.screen.HomeScreen
import com.example.notesapp.screen.TodoListScreen
import com.example.notesapp.screen.TodoScreen

import com.example.notesapp.ui.theme.NotesappTheme
import com.example.notesapp.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        enableEdgeToEdge()
        val viewModel = TodoViewModel()
        setContent {
            NotesappTheme {
//                StatusBarProtection()
                SetStatusBarColor(color = Color.Yellow)
                 AppNavigation()
                }
            }
        }
    }


@Composable
fun SetStatusBarColor(color: Color) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as ComponentActivity).window
        window.statusBarColor = color.toArgb()

        // Set dark icons for light background
        val insetsController = WindowInsetsControllerCompat(window, view)
        insetsController.isAppearanceLightStatusBars = true
    }
}






@Composable
private fun StatusBarProtection(
    color: Color = Color.White

) {

    Canvas(Modifier.fillMaxSize()) {

        val gradient = Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 1f),
                color.copy(alpha = .8f),
                Color.Transparent
            ),
            startY = 0f,

        )
        drawRect(
            brush = gradient,

        )
    }
}
@Composable
private fun NavigationBarProtection(
    color: Color = Color.Yellow
) {
    // Draws a solid background behind the system nav bar
    Canvas(
        Modifier
            .fillMaxSize()
    ) {
        val gradient = Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                color.copy(alpha = 0.8f),
                color.copy(alpha = 1f)
            ),
            startY = size.height / 2,
            endY = size.height
        )

        drawRect(
            brush = gradient,
            size = Size(size.width, size.height)
        )
    }
}

