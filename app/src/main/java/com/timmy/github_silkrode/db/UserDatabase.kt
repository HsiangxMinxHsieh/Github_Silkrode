package com.timmy.github_silkrode.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [ReceivedEvent::class],
        version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userReceivedEventDao(): UserReceivedEventDao

}