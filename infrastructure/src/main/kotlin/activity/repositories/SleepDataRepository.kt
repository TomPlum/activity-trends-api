package activity.repositories

import activity.entity.SleepData
import org.springframework.data.mongodb.repository.MongoRepository

interface SleepDataRepository : MongoRepository<String, SleepData>