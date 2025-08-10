package com.example.todoapp.screen

import android.R.attr.statusBarColor
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.viewmodel.TaskViewModel


@SuppressLint("ContextCastToActivity")
@Composable
fun TaskListScreen() {
    val viewModel: TaskViewModel = viewModel()
    val tasks = viewModel.tasks.collectAsState().value

    val statusBarColor = MaterialTheme.colorScheme.primaryContainer

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val window = (LocalContext.current as Activity).window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = true
        window.statusBarColor = statusBarColor.toArgb()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                content = {
                    Icon(Icons.Default.Add, contentDescription = "Add ToDo")
                }
            )
        },
        topBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .statusBarsPadding(),
                contentAlignment = Alignment.Center){
                Text(text = "To Do",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onToggleCompletion = { viewModel.toggleTaskCompletion(task) },
                        onDelete = { viewModel.remove(task) }
                    )
                }
            }
        }
    }
}
