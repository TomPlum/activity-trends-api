package com.github.tomplum.activity.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.github.tomplum.activity.repositories"])
open class MongoConfig(val props: MongoProperties) : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String = props.database

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(props.uri)
        val settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build()

        return MongoClients.create(settings)
    }
}