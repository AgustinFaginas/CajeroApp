package ar.edu.unlam.cajeroapp.data.room

import androidx.lifecycle.MutableLiveData
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.model.Cuenta

interface CuentaRepository {

    suspend fun save (cuenta: Cuenta)

    suspend fun getAll(): List <CuentaEntity>

    suspend fun searchAccount(usuario:Long) : CuentaEntity?

    suspend fun update(cuentaEntity: CuentaEntity)






}