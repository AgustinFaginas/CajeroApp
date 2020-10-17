package ar.edu.unlam.cajeroapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.cajeroapp.model.Usuario
@Dao
interface UsuarioDao {

    @Query (value = "SELECT * FROM  usuario")
    fun getAll():List<Usuario>

    @Insert
    fun saveUsuario(entity: UsuarioEntity)
}