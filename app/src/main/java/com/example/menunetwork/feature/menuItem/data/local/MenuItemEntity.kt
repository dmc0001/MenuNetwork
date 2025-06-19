package com.example.menunetwork.feature.menuItem.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.menunetwork.feature.menuItem.domain.MenuItem

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: String,
    val category: String
) {
    fun toDomain() = MenuItem(id, title, price, category)
}