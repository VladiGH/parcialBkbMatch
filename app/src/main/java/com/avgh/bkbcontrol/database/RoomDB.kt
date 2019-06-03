package com.avgh.bkbcontrol.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Match::class], version = 1, exportSchema = false)
public abstract class RoomDB: RoomDatabase() {

    abstract fun matchDao(): MatchDAO


    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            context: Context
        ): RoomDB {
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            else{
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(context, RoomDB::class.java, "match_database")
                        .build()
                    return INSTANCE!!
                }
            }

        }

    }


}