package com.rayray.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rayray.madlevel5task2.dao.GameDao
import com.rayray.madlevel5task2.database.GameReleaseRoomDatabase
import com.rayray.madlevel5task2.model.Game

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameReleaseRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>>{
        return gameDao.getAllGames() ?: MutableLiveData(emptyList())
    }

    suspend fun insertGame(game: Game){
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game){
        gameDao.deleteGame(game)
    }

    suspend fun deleteAll(){
        gameDao.deleteAll()
    }

}