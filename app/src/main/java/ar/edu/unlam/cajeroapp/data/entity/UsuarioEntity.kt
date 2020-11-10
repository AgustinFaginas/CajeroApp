package ar.edu.unlam.cajeroapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
 class UsuarioEntity (


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    val id:Long=0,
    @ColumnInfo(name = "nombre")
    val nombre : String
)






