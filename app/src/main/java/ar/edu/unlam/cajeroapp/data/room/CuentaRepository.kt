package ar.edu.unlam.cajeroapp.data.room

import ar.edu.unlam.cajeroapp.model.Cuenta

interface CuentaRepository {

    suspend fun save (cuenta: Cuenta)

}