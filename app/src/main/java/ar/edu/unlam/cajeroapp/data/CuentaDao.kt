package ar.edu.unlam.cajeroapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.cajeroapp.model.Cuenta

@Dao
interface CuentaDao {

    @Insert
    suspend fun saveCuenta(entity: CuentaEntity)
}