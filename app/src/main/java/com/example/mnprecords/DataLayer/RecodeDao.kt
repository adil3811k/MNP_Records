package com.example.mnprecords.DataLayer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecodeDao {
    @Query("""select * from recode""")
    fun GetAllStrem(): Flow<List<MNPRecode>>

    @Upsert
    suspend fun AddRecode(mnpRecode: MNPRecode)

    @Query("""select sum(vi) from recode""")
    fun getVITotal():Flow<Int>

    @Query("""select sum(jio) from recode""")
    fun getJioTotal():Flow<Int>

    @Query("""select sum(Airtel) from recode""")
    fun getAirtelTotal():Flow<Int>

    @Query("""select sum(UIBalance) from recode""")
    fun getTotalULBalance():Flow<Int>

    @Query("SELECT * FROM Recode WHERE ID=:id LIMIT 1")
    suspend fun SelectRecode(id: Int):MNPRecode

    @Delete
   suspend fun DeleteRecode(mnpRecode: MNPRecode)
}
