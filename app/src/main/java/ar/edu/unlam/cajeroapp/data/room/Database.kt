package ar.edu.unlam.cajeroapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity

@Database(
    version = 2,
    entities = [UsuarioEntity::class , CuentaEntity::class]

)
abstract class  CajeroDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun cuentaDato(): CuentaDao

}