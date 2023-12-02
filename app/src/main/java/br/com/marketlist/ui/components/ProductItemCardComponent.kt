package br.com.marketlist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marketlist.data.ProductItem
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.theme.MarketListTheme

@Preview
@Composable
fun ProductItemCardComponentPreviewChecked() {
    ProductItemCardComponent(item = sampleFirstList.first().copy(bought = true))
}

@Preview
@Composable
fun ProductItemCardComponentPreview() {
    ProductItemCardComponent(item = sampleFirstList.first())
}

@Composable
fun ProductItemCardComponent(
    modifier: Modifier = Modifier,
    item: ProductItem,
    onClickItem: (idItemClicked: Long) -> Unit = {},
) {

    val cardColor = if (item.bought) {
            CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)

    } else {
            CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)

    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(12.dp)
            )
            .heightIn(50.dp),
        colors = cardColor
    ) {
        Row {
            Column {
                Checkbox(
                    checked = item.bought,
                    onCheckedChange = {
                        onClickItem(item.id)
                    }
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Column(
                modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                if(item.bought){
                    Text(text = item.name, textDecoration = TextDecoration.LineThrough)
                }else{
                    Text(text = item.name)
                }
            }
        }
    }
}
