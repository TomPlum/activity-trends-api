package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.document.Activity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ActivityDataRepository : MongoRepository<Activity, UUID>