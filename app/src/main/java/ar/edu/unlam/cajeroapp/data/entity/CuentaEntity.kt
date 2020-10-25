package ar.edu.unlam.cajeroapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cuenta")
class CuentaEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Long=0,
    @ColumnInfo(name = "dinero")
    val dinero :Int,
    @ColumnInfo(name = "usuario")
    val usuarioId : Long


)
