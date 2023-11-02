package br.com.marketlist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marketlist.ui.theme.MarketListTheme

@Composable
fun RowDataFormatedComponent(
    modifier: Modifier = Modifier,
    item: String = "",
    position: Int = 0,
    onRemoveItemTransformed: (position: Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .padding(
                    top = 8.dp,
                    bottom = 8.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier
                    .weight(1f)
                    .align(CenterVertically),
                text = "$item",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.width(20.dp))
            IconButton(
                modifier = modifier
                    .weight(1f)
                    .align(CenterVertically),
                onClick = { onRemoveItemTransformed(position) }
            ) {
                Icon(
                    Icons.Default.Delete,
                    tint = Color.Black,
                    contentDescription = "Deletar"
                )
            }
        }
    }
}

@Preview
@Composable
fun RowDataFormatedComponentPreview() {
    MarketListTheme {
        RowDataFormatedComponent(
            item = "Maça"
        )
    }
}