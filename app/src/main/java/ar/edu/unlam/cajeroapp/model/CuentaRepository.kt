package ar.edu.unlam.cajeroapp.model

interface CuentaRepository {

    suspend fun save (cuenta: Cuenta)

}