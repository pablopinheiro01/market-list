package br.com.marketlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.marketlist.data.ListMarket

@Dao
interface ListMarketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(list: ListMarket): Long



}