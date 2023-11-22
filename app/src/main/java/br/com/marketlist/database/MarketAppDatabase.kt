package br.com.marketlist.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.marketlist.data.ListMarket
import br.com.marketlist.data.ProductItem

@Database(
    entities = [
        ProductItem::class,
        ListMarket::class
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(1,2)
    ]
)
abstract class MarketAppDatabase : RoomDatabase() {

    abstract fun dao(): ProductItemDao

}

val MIGRATION_1_TO_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS ListMarket (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL DEFAULT 0, `titleList` TEXT NOT NULL DEFAULT '')")
        database.execSQL("INSERT INTO ListMarket (id,titleList) VALUES (0,'Titulo')")
        database.execSQL("CREATE TABLE IF NOT EXISTS ProductItemCopy (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `bought` INTEGER NOT NULL," +
                " `idListMarket` INTEGER NOT NULL DEFAULT 0, FOREIGN KEY(`idListMarket`) REFERENCES `ListMarket`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        database.execSQL("INSERT INTO ProductItemCopy (id,name,bought,idListMarket) SELECT id,name,bought,0 FROM ProductItem")
        database.execSQL("DROP TABLE ProductItem")
        database.execSQL("ALTER TABLE ProductItemCopy RENAME TO ProductItem")
    }

}