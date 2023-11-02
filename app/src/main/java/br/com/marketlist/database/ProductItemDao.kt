package br.com.marketlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.marketlist.data.ItemsProduct
import br.com.marketlist.data.ProductItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductItemDao{

    @Query("SELECT * FROM ProductItem")
    fun findAll(): Flow<List<ProductItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(listItens: List<ProductItem>)



}