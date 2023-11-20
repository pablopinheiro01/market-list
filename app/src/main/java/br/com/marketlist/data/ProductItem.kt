package br.com.marketlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    ListMarket::class,
    parentColumns = ["id"],
    childColumns = ["idListMarket"],
    onDelete = ForeignKey.CASCADE
)])
data class ProductItem(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    val name:String = "",
    var bought:Boolean = false,
    @ColumnInfo(defaultValue = "0")
    val idListMarket: Long = 0L
)

@Entity
data class ListMarket(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    val id: Long = 0L,
    @ColumnInfo(defaultValue = "")
    val titleList: String = "List Title"
)
