package ar.edu.unlam.cajeroapp.data.room

import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.model.Cuenta

interface CuentaRepository {

    suspend fun save (cuenta: Cuenta)

    suspend fun getAll(): List <CuentaEntity>



}