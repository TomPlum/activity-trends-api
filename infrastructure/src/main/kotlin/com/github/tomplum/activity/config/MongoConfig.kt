package com.github.tomplum.activity.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["activity.repositories"])
open class MongoConfig : AbstractMongoClientConfiguration() {
    @Autowired
    private lateinit var props: MongoProperties

    override fun getDatabaseName(): String = "activity-data"

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(props.host)
        val settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build()

        return MongoClients.create(settings)
    }
}