package com.example.menunetwork.feature.menuItem.data.network

import com.example.menunetwork.core.networking.utils.ApiResults
import com.example.menunetwork.core.networking.utils.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class MenuItemService(private val httpClient: HttpClient) {

    suspend fun fetchMenuItem(): ApiResults<List<MenuItemDto>, NetworkError> {
        val response = try {
            httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json") {
                accept(ContentType.Application.Json)
            }
        } catch (e: UnresolvedAddressException) {
            return ApiResults.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return ApiResults.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            return ApiResults.Error(NetworkError.UNKNOWN)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val rawJson = response.bodyAsText()
                val menuResponse = Json.decodeFromString(MenuResponseDto.serializer(), rawJson)
                ApiResults.Success(menuResponse.menu)
            }

            401 -> ApiResults.Error(NetworkError.UNAUTHORIZED)
            408 -> ApiResults.Error(NetworkError.REQUEST_TIMEOUT)
            409 -> ApiResults.Error(NetworkError.CONFLICT)
            413 -> ApiResults.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> ApiResults.Error(NetworkError.SERVER_ERROR)
            else -> ApiResults.Error(NetworkError.UNKNOWN)
        }
    }
}
