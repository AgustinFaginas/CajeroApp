package ar.edu.unlam.cajeroapp

import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.impl.RoomCuentaRepository
import ar.edu.unlam.cajeroapp.data.impl.RoomUsuarioRepository
import ar.edu.unlam.cajeroapp.data.room.CuentaDao
import ar.edu.unlam.cajeroapp.data.room.UsuarioDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

import org.junit.Test

class RoomCuentaRepositoryTest {

    lateinit var instance: RoomCuentaRepository

    @MockK
    lateinit var cuentaDao: CuentaDao

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `que la base de datos busque bien la cuenta por id`() {
        runBlockingTest {

            instance = RoomCuentaRepository(cuentaDao)
            coEvery { cuentaDao.searchAccount(0) } returns listOf(CuentaEntity(0, 20, 0))


            val result = cuentaDao.searchAccount(0)

            assertThat(result[0].id.equals(0))

        }
    }


    @Test
    fun `que la base de datos traiga todos los usuarios`() {
        runBlockingTest {

            instance = RoomCuentaRepository(cuentaDao)
            coEvery { cuentaDao.getAll() } returns listOf(
                CuentaEntity(0, 20, 0),
                CuentaEntity(1, 20, 1)
            )


            val result = cuentaDao.getAll()

            assertThat(result[0].id.equals(0))
            assertThat(result[1].id.equals(1))

        }
    }


}