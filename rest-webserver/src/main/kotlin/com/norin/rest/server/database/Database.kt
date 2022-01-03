package com.norin.rest.server.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class Database(
    val host: String,
    val port: String,
    val database: String,
    val username: String,
    val password: String
) {
    fun connect(): com.norin.rest.server.database.Database = this.apply {
        val dataSource = HikariDataSource(HikariConfig(Properties().apply {
            setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource")
            setProperty("dataSource.user", username)
            setProperty("dataSource.password", password)
            setProperty("dataSource.databaseName", database)
        }).apply {
            jdbcUrl = "jdbc:postgresql://$host:$port/$database?useTimezone=true&serverTimezone=UTC"
        })

        Database.connect(dataSource)
    }

    fun createTables(vararg tables: Table) = apply {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(*tables)
        }
    }

    companion object {
        fun config(config: ApplicationConfig): com.norin.rest.server.database.Database =
            Database(
                host = config.databaseProperty("host"),
                port = config.databaseProperty("port"),
                database = config.databaseProperty("database"),
                username = config.databaseProperty("username"),
                password = config.databaseProperty("password"),
            )
        private fun ApplicationConfig.databaseProperty(label: String) = this.config("database").property(label).getString()
    }
}
