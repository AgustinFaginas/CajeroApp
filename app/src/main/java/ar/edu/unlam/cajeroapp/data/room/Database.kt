package ar.edu.unlam.cajeroapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.model.Cuenta
import ar.edu.unlam.cajeroapp.model.Usuario

@Database(
    version = 1,
    entities = [UsuarioEntity::class , CuentaEntity::class]

)
abstract class  CajeroDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun cuentaDato(): CuentaDao

}