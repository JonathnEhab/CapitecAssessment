package com.example.data.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.offline.converters.DataConverter
import com.example.domain.entity.GitHubResponse
import com.example.domain.entity.Item


@Database(entities = [GitHubResponse::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class RepoDatabase :RoomDatabase(){
    abstract fun repoDao():Dao
}