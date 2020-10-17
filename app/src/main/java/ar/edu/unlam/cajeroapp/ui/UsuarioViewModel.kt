package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.edu.unlam.cajeroapp.data.UsuarioEntity
import ar.edu.unlam.cajeroapp.model.Usuario
import ar.edu.unlam.cajeroapp.model.UsuarioRepository

class UsuarioViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    val listaUsuarios = MutableLiveData<List<Usuario>>()

    init {
        listaUsuarios.value = usuarioRepository.getAll()
    }

    fun save(usuario: Usuario) {
        usuarioRepository.save(usuario)
        listaUsuarios.value = usuarioRepository.getAll()
    }


}