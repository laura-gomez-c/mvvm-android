package com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ceiba.capacitacion.mvvmpattern.BuildConfig.DataBaseName
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.dao.MovieDao
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.daos.ShoppingCartDao
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.entities.MovieEntity
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.entities.ShoppingCarEntity

@Database(
        entities = [MovieEntity::class
            , ShoppingCarEntity::class],
        version = 1,
        exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun shoppingCartDao(): ShoppingCartDao

    companion object {

        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DataBaseName)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}