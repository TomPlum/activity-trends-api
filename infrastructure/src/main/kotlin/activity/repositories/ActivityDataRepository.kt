package activity.repositories

import activity.entity.Activity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ActivityDataRepository : MongoRepository<Activity, UUID>