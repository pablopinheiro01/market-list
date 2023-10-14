package br.com.marketlist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MarketAppDatabase::class], version = 1)
abstract class MarketAppDatabase: RoomDatabase(){

    //TODO implement DAO

}