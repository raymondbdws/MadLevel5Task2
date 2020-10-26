package com.rayray.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rayray.madlevel5task2.dao.GameDao
import com.rayray.madlevel5task2.model.Game

//todo lees abstract class door
@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameReleaseRoomDatabase: RoomDatabase() {

    abstract fun gameDao(): GameDao

    //static
    companion object{
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameReleaseRoomDatabaseInstance: GameReleaseRoomDatabase? = null

        fun getDatabase(context: Context): GameReleaseRoomDatabase?{
            if (gameReleaseRoomDatabaseInstance == null){
                synchronized(GameReleaseRoomDatabase::class.java){
                    if (gameReleaseRoomDatabaseInstance == null){
                        gameReleaseRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameReleaseRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return gameReleaseRoomDatabaseInstance
        }
    }
}