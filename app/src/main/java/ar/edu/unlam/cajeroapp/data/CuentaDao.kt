package ar.edu.unlam.cajeroapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CuentaDao {

    @Query(value = "SELECT * FROM  cuenta")
    fun getAll():List<CuentaEntity>

    @Insert
    fun saveCuenta(entity: CuentaEntity)
}