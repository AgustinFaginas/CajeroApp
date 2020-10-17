package ar.edu.unlam.cajeroapp.data

import ar.edu.unlam.cajeroapp.model.Usuario
import ar.edu.unlam.cajeroapp.model.UsuarioRepository

class RoomUsuarioRepository (private  val usuarioDao: UsuarioDao) : UsuarioRepository {

    override fun save(usuario :Usuario) {

        val usuarioEntity = UsuarioEntity(nombre=usuario.nombre)
        usuarioDao.saveUsuario(usuarioEntity)
    }

    override fun getAll(): List<Usuario> {

     return  usuarioDao.getAll()
    }


}