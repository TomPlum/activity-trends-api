package activity.repositories

import activity.document.SleepData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SleepDataRepository : MongoRepository<SleepData, String>