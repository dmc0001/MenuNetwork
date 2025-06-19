package com.example.menunetwork.core.networking

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger

import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object Client {
    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine = engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = CustomLogger()
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true

                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
                connectTimeoutMillis = 60_000
                socketTimeoutMillis = 60_000
            }
        }
    }
    class CustomLogger : Logger {
        override fun log(message: String) {
            Log.d("Ktor", message)
        }
    }
}

