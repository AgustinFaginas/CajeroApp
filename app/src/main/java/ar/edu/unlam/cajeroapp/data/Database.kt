package ar.edu.unlam.cajeroapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [UsuarioEntity ::class]

)
abstract class  CajeroDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

}