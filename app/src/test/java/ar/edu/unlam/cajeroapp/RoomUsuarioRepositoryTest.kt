package ar.edu.unlam.cajeroapp

import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.impl.RoomUsuarioRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

import org.junit.Test

class RoomUsuarioRepositoryTest {

    lateinit var instance: RoomUsuarioRepository

    @MockK
    lateinit var usuarioDao: UsuarioDao

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)




    @Test
    fun    `que la base de datos busque bien al usuario por nombre` () {
        runBlockingTest {

            instance = RoomUsuarioRepository(usuarioDao)
            coEvery { usuarioDao.getByName("Agustin") } returns
                    listOf(UsuarioEntity(0, "Agustin"))

            val result = usuarioDao.getByName("Agustin")

            assertThat(result[0].nombre == "Agustin")

        }
    }



}


