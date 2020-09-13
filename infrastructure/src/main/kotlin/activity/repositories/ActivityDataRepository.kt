package activity.repositories

import activity.entity.Activity
import org.springframework.data.mongodb.repository.MongoRepository

interface ActivityDataRepository : MongoRepository<String, Activity>