package br.com.marketlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.marketlist.data.ProductItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductItemDao{

    @Query("SELECT * FROM ProductItem")
    fun findAll(): Flow<List<ProductItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(listItens: List<ProductItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItemBought(item: ProductItem)

    @Query("SELECT * FROM ProductItem WHERE id = :id")
    fun findItem(id: Long): ProductItem

    @Query("DELETE FROM ProductItem")
    fun deleteAll()

    @Query("SELECT * FROM ProductItem WHERE idListMarket = :idCurrentList")
    fun findAllWithList(idCurrentList: Long?): Flow<List<ProductItem>>

    @Query("SELECT * FROM ProductItem")
    fun findAllSimple(): List<ProductItem>


}