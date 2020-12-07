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
    fun `que la base de datos busque bien al usuario por nombre`() {
        runBlockingTest {

            instance = RoomUsuarioRepository(usuarioDao)
            coEvery { usuarioDao.getByName("Agustin") } returns
                    listOf(UsuarioEntity(0, "Agustin"))

            val result = usuarioDao.getByName("Agustin")

            assertThat(result[0].nombre == "Agustin")

        }
    }

    @Test
    fun `que la base de datos busque bien al usuario por id`() {
        runBlockingTest {

            instance = RoomUsuarioRepository(usuarioDao)
            coEvery { usuarioDao.getById(0) } returns UsuarioEntity(0, "Agustin")

            val result = usuarioDao.getById(0)

            assertThat(result.nombre == "Agustin")

        }
    }

    @Test
    fun `que la base de datos traiga bien todos los usuarios`() {
        runBlockingTest {

            instance = RoomUsuarioRepository(usuarioDao)
            coEvery { usuarioDao.getAll() } returns
                    listOf(
                        UsuarioEntity(0, "Agustin"),
                        UsuarioEntity(1, "Jose"),
                        UsuarioEntity(2, "Pablo")
                    )

            val result = usuarioDao.getAll()

            assertThat(result.size == 3)
            assertThat(result[0].nombre == "Agustin")
            assertThat(result[1].nombre == "Jose")
            assertThat(result[2].nombre == "Pablo")

        }
    }


}


