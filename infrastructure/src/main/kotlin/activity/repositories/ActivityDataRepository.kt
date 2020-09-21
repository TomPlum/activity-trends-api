package activity.repositories

import activity.document.Activity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ActivityDataRepository : MongoRepository<Activity, UUID>