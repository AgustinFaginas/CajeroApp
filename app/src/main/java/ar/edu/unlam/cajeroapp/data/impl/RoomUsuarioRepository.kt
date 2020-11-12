package ar.edu.unlam.cajeroapp.data.impl

import ar.edu.unlam.cajeroapp.data.room.UsuarioDao
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import ar.edu.unlam.cajeroapp.model.Usuario

class RoomUsuarioRepository (private  val usuarioDao: UsuarioDao) :
    UsuarioRepository {

    override suspend fun save(usuario :Usuario) {

        val usuarioEntity =
            UsuarioEntity(nombre = usuario.nombre)
        usuarioDao.saveUsuario(usuarioEntity)
    }

    override suspend fun getAll(): List<UsuarioEntity> {

     return  usuarioDao.getAll()
    }

    override suspend fun getById(id: Long): UsuarioEntity {
        return  usuarioDao.getById(id)
    }

    override suspend fun getByName(nombre: String): UsuarioEntity {
        val usuarios = usuarioDao.getByName(nombre)

        return usuarios[0]

    }


}