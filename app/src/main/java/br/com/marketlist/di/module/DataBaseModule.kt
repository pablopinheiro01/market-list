package br.com.marketlist.di.module

import android.content.Context
import androidx.room.Room
import br.com.marketlist.database.ListMarketDao
import br.com.marketlist.database.MIGRATION_1_TO_2
import br.com.marketlist.database.MarketAppDatabase
import br.com.marketlist.database.ProductItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "marketApp.db"
@Module
@InstallIn(SingletonComponent::class)
internal class DataBaseModule {

    @Provides
    fun provideListMarketDao(db: MarketAppDatabase): ListMarketDao{
        return db.listMarketDao()
    }
    @Provides
    fun provideProductItemDao(db: MarketAppDatabase): ProductItemDao{
        return db.productItemDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context): MarketAppDatabase {
        return Room.databaseBuilder(
            context,
            MarketAppDatabase::class.java,
            DATABASE_NAME
        )
            .addMigrations(MIGRATION_1_TO_2)
            .build()
    }

}