package com.example.listacompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.listacompose.data.model.Task
import com.example.listacompose.ui.theme.ListaComposeTheme

@Composable
fun TaskItem(modifier: Modifier, task: Task) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = task.title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(text = task.description, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    ListaComposeTheme() {
        Surface {
            TaskItem(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .heightIn(30.dp)
                    .fillMaxWidth(),
                task = Task(
                    0,
                    "Titulo",
                    LoremIpsum(50).values.first()
                )
            )
        }
    }
}