package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.model.Usuario
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    val listaUsuarios = MutableLiveData<List<UsuarioEntity>>()

    init {
        viewModelScope.launch { listaUsuarios.value = usuarioRepository.getAll() }

    }

    fun save(usuario: Usuario) {

        viewModelScope.launch { usuarioRepository.save(usuario)
            listaUsuarios.value = usuarioRepository.getAll() }

    }






}