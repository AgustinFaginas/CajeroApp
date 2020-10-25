package ar.edu.unlam.cajeroapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    @Query (value = "SELECT * FROM  usuario")
   suspend fun getAll():List<UsuarioEntity>

    @Insert
     suspend fun saveUsuario(entity: UsuarioEntity)
}