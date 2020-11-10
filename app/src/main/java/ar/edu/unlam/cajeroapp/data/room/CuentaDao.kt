package ar.edu.unlam.cajeroapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity

@Dao
interface CuentaDao {


    @Query(value = "SELECT * FROM  cuenta")
    suspend fun getAll():List<CuentaEntity>

    @Insert
    suspend fun saveCuenta(entity: CuentaEntity)



}