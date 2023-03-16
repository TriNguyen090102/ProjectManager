package com.example.projectmanager.ui.theme.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun primaryButton(text: String, modifier: Modifier,color: Color, onClick: () -> Unit,hasStroke: Boolean = false ,
                  strokeColor: Color = Color.Black ,
                  strokeWidth: Dp = 1.dp,
                  textColor: Color = Color.White
) {
    Button(
        onClick = onClick ,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(10.dp),
        border = if(hasStroke) BorderStroke(strokeWidth, strokeColor) else null
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp),
            color = textColor,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )
    }
}
