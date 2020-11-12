package ar.edu.unlam.cajeroapp.data.impl

import androidx.lifecycle.MutableLiveData
import ar.edu.unlam.cajeroapp.data.room.CuentaDao
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.model.Cuenta

class RoomCuentaRepository(private val cuentaDao: CuentaDao) :
    CuentaRepository {


    override suspend fun save(cuenta: Cuenta) {
        val cuentaEntity = CuentaEntity(
            dinero = cuenta.dinero,
            nombreUsuario = cuenta.nombreUsuario
        )
        cuentaDao.saveCuenta(cuentaEntity)
    }

    override suspend fun getAll(): List<CuentaEntity> {

        return  cuentaDao.getAll()
    }

    override suspend fun searchAccount(usuario: Long): CuentaEntity? {
        val cuentas = cuentaDao.getAll()

        return cuentas[0]

    }

    override suspend fun depositar(idUsuario: Long, dinero: Int) {
        return cuentaDao.depositar(idUsuario,dinero)
    }

    override suspend fun extraer(idUsuario: Long, dinero: Int) {
        return cuentaDao.extraer(idUsuario,dinero)
    }


}