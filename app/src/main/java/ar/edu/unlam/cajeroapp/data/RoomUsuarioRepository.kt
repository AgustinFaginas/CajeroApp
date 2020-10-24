package ar.edu.unlam.cajeroapp.data

import ar.edu.unlam.cajeroapp.model.Usuario
import ar.edu.unlam.cajeroapp.model.UsuarioRepository

class RoomUsuarioRepository (private  val usuarioDao: UsuarioDao) : UsuarioRepository {

    override suspend fun save(usuario :Usuario) {

        val usuarioEntity = UsuarioEntity(nombre=usuario.nombre)
        usuarioDao.saveUsuario(usuarioEntity)
    }

    override suspend fun getAll(): List<UsuarioEntity> {

     return  usuarioDao.getAll()
    }


}