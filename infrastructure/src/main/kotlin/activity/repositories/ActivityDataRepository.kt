package activity.repositories

import activity.dto.Activity
import org.springframework.data.mongodb.repository.MongoRepository

interface ActivityDataRepository : MongoRepository<String,  Activity>