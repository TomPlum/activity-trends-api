package activity.repositories

import activity.entity.SleepData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SleepDataRepository : MongoRepository<SleepData, UUID>