package br.com.marketlist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductItem(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    val name:String = "",
    var bought:Boolean = false
)

@Entity
data class ItemsProduct(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    val list:List<ProductItem> = listOf()
)
