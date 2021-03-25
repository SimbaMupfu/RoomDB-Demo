package com.sims.roomdbdemo.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface SubscribeDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber)
}