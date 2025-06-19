package com.example.menunetwork.feature.menuItem.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu_items") fun getAll(): Flow<List<MenuItemEntity>>
    @Query("""
    SELECT * FROM menu_items
    WHERE (:matchAllCategories OR category IN (:categories))
    AND title LIKE '%' || :query || '%'
""")
    fun getFiltered(
        categories: List<String>,
        query: String,
        matchAllCategories: Boolean = false
    ): Flow<List<MenuItemEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE) suspend fun insertAll(items: List<MenuItemEntity>)
}