package ar.edu.unlam.cajeroapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity

@Dao
interface CuentaDao {

    @Insert
    suspend fun saveCuenta(entity: CuentaEntity)
}