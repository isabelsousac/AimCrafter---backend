package com.isabel.aimCrafter

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    @Value("\${spring.datasource.url}")
    private lateinit var dbUrl: String
    @Value("\${spring.datasource.username}")
    private lateinit var username: String
    @Value("\${spring.datasource.password}")
    private lateinit var password: String

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dbUrl
        config.password = password
        config.username = username
        return HikariDataSource(config)
    }
}