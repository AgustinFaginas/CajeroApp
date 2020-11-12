package ar.edu.unlam.cajeroapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity

@Dao
interface CuentaDao {


    @Query(value = "SELECT * FROM  cuenta")
    suspend fun getAll():List<CuentaEntity>

    @Insert
    suspend fun saveCuenta(entity: CuentaEntity)

    @Query(value = "SELECT * FROM cuenta WHERE usuario=:usuario")
    suspend fun searchAccount(usuario :Long) : List<CuentaEntity>




}