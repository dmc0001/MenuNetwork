package com.example.menunetwork.feature.menuItem.data.local


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}