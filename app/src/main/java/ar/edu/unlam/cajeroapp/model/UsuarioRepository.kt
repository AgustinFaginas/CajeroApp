package ar.edu.unlam.cajeroapp.model

import ar.edu.unlam.cajeroapp.data.UsuarioEntity

interface UsuarioRepository {

   suspend fun save (usuario: Usuario)

    suspend fun getAll(): List <UsuarioEntity>
}