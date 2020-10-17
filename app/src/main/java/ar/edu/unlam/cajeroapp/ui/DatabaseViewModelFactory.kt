
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ar.edu.unlam.cajeroapp.data.CajeroDatabase
import ar.edu.unlam.cajeroapp.data.RoomUsuarioRepository
import ar.edu.unlam.cajeroapp.ui.MainViewModel
import ar.edu.unlam.cajeroapp.ui.UsuarioViewModel


class DatabaseViewModelFactory( private val applicationContext: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val database = Room.databaseBuilder(
            applicationContext,
            CajeroDatabase::class.java,
            "users-db"
        ).build()

        val dao = database.usuarioDao()

        return UsuarioViewModel(
            RoomUsuarioRepository(dao)

        ) as T
    }

}


