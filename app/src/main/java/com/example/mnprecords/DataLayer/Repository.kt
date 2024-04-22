package com.example.mnprecords.DataLayer

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllStrem(): Flow<List<MNPRecode>>
    suspend fun AddRecode(mnpRecode: MNPRecode)
    fun  getViTotal():Flow<Int>
    fun getJioTotal():Flow<Int>
    fun getAirtel():Flow<Int>
    suspend fun selectcteRecode(int: Int):MNPRecode
    suspend fun deleteRecode(mnpRecode: MNPRecode)
    fun getTotalUIBalance():Flow<Int>
}

class AppRepository(
    private val recodeDao: RecodeDao
):Repository{
    override fun getAllStrem(): Flow<List<MNPRecode>> =recodeDao.GetAllStrem()
    override suspend fun AddRecode(mnpRecode: MNPRecode) = recodeDao.AddRecode(mnpRecode)
    override fun getViTotal(): Flow<Int> =recodeDao.getVITotal()
    override fun getJioTotal(): Flow<Int> = recodeDao.getJioTotal()
    override fun getAirtel(): Flow<Int>  = recodeDao.getAirtelTotal()
    override suspend fun deleteRecode(mnpRecode: MNPRecode)  =recodeDao.DeleteRecode(mnpRecode)
    override suspend fun selectcteRecode(int: Int): MNPRecode = recodeDao.SelectRecode(int)
    override fun getTotalUIBalance(): Flow<Int>  =recodeDao.getTotalULBalance()

}