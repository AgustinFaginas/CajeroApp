package ar.edu.unlam.cajeroapp.data.impl

import ar.edu.unlam.cajeroapp.data.room.CuentaDao
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.model.Cuenta

class RoomCuentaRepository(private val cuentaDao: CuentaDao) :
    CuentaRepository {


    override suspend fun save(cuenta: Cuenta) {
        val cuentaEntity = CuentaEntity(
            dinero = cuenta.dinero,
            usuario = cuenta.usuario
        )
        cuentaDao.saveCuenta(cuentaEntity)
    }

    override suspend fun getAll(): List<CuentaEntity> {

        return  cuentaDao.getAll()
    }



}