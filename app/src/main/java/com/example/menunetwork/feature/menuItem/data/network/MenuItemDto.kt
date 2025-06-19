package com.example.menunetwork.feature.menuItem.data.network

import com.example.menunetwork.feature.menuItem.data.local.MenuItemEntity
import com.example.menunetwork.feature.menuItem.domain.MenuItem
import kotlinx.serialization.Serializable
@Serializable
data class MenuResponseDto(
    val menu: List<MenuItemDto>
)

@Serializable
data class MenuItemDto(
    val id: Int,
    val title: String,
    val price: String,
) {
    fun toEntity(): MenuItemEntity {
        val category = when (id) {
            in 1..4 -> "Appetizer"
            in 5..8 -> "Salad"
            in 9..12 -> "Beverage"
            else -> "Other"
        }

        return MenuItemEntity(
            id = id,
            title = title,
            price = price,
            category = category
        )
    }
}
