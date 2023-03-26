package com.example.projectmanager.Models

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id:String,
    val title:String,
    val icon:Painter
)