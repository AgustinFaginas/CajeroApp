package ar.edu.unlam.cajeroapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cuenta")
public class CuentaEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "dinero")
    var dinero: Int,
    @ColumnInfo(name = "idUsuario")
    val idUsuario: Long


)
