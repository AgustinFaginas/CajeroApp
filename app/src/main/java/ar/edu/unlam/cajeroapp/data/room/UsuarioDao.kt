package ar.edu.unlam.cajeroapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.model.Usuario

@Dao
interface UsuarioDao  {

    @Query (value = "SELECT * FROM  usuario")
   suspend fun getAll():List<UsuarioEntity>

    @Insert
     suspend fun saveUsuario(entity: UsuarioEntity)

    @Query("SELECT * FROM usuario WHERE id=:id")
   suspend fun getById (id:Long):UsuarioEntity

    @Query("SELECT * FROM usuario WHERE nombre=:nombre")
    suspend fun getByName (nombre:String) :List<UsuarioEntity>

}