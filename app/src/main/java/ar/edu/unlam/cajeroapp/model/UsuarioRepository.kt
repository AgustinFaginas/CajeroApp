package ar.edu.unlam.cajeroapp.model

import ar.edu.unlam.cajeroapp.data.UsuarioEntity

interface UsuarioRepository {

    fun save (usuario: Usuario)

    fun getAll(): List <Usuario>
}