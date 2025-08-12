package com.example.todoapp.screen

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskInputDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var taskInput by rememberSaveable { mutableStateOf("") }
    var context= LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Todo") },
        text = {
            TextField(
                value = taskInput,
                onValueChange = { taskInput = it },
                label = { Text(text = "Enter Todo") }
            )
        },
        confirmButton = {
            Button (onClick = {
                if (taskInput.isNotBlank()) {
                    onConfirm(taskInput)
                    taskInput = ""
                }else{
                    Toast.makeText(context, "The text field is empty. Please enter a text!", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

