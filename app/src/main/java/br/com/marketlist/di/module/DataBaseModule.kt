package br.com.marketlist.di.module

import android.content.Context
import androidx.room.Room
import br.com.marketlist.database.MarketAppDatabase
import br.com.marketlist.database.ProductItemDao
import br.com.marketlist.navigation.marketListRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataBaseModule {

    @Provides
    fun provideProductItemDao(db: MarketAppDatabase): ProductItemDao{
        return db.dao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context): MarketAppDatabase {
        return Room.databaseBuilder(
            context,
            MarketAppDatabase::class.java,
            "marketApp.db"
        ).build()
    }

}