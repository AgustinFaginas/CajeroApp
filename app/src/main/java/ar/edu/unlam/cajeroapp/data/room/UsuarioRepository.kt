package ar.edu.unlam.cajeroapp.data.room

import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.model.Usuario

interface UsuarioRepository {

   suspend fun save (usuario: Usuario)

    suspend fun getAll(): List <UsuarioEntity>

   suspend fun getById(id:Long) : Usuario

    suspend fun getByName(nombre:String):Usuario
}