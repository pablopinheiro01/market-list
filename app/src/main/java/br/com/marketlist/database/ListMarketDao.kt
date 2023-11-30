package br.com.marketlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.marketlist.data.ListMarket
import kotlinx.coroutines.flow.Flow

@Dao
interface ListMarketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(list: ListMarket): Long

    @Query("SELECT * FROM ListMarket")
    fun findAll(): List<ListMarket>


}