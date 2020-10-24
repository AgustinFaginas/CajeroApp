package ar.edu.unlam.cajeroapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 2,
    entities = [UsuarioEntity ::class , CuentaEntity::class]

)
abstract class  CajeroDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun cuentaDato(): CuentaDao

}