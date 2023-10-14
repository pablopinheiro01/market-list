package br.com.marketlist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductItem(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    val name:String = "",
    val bought:Boolean = false
)
