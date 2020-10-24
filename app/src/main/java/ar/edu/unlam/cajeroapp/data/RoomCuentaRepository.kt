package ar.edu.unlam.cajeroapp.data

import ar.edu.unlam.cajeroapp.model.Cuenta
import ar.edu.unlam.cajeroapp.model.CuentaRepository

class RoomCuentaRepository(private val cuentaDao: CuentaDao) : CuentaRepository {


    override suspend fun save(cuenta: Cuenta) {
        val cuentaEntity = CuentaEntity(dinero = cuenta.dinero,usuarioId = cuenta.usuarioId)
        cuentaDao.saveCuenta(cuentaEntity)
    }

}