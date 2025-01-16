package com.example.data.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.GitHubResponse

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(data: GitHubResponse)

    @Query("SELECT * FROM repo_data")
    suspend fun getData(): GitHubResponse
}
