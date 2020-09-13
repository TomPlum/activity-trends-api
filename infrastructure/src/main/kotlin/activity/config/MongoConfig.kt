package activity.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["activity.repositories"])
class MongoConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String = "activity-data"
}