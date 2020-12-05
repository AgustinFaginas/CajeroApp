package ar.edu.unlam.cajeroapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import ar.edu.unlam.cajeroapp.model.Usuario
import ar.edu.unlam.cajeroapp.ui.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    lateinit var instance: MainViewModel


    @MockK
    lateinit var usuarioRepository: UsuarioRepository

    @MockK
    lateinit var cuentaRepository: CuentaRepository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val couroutineTestRule = CoroutineTestRule()

    @ExperimentalCoroutinesApi
    @Test
    fun inicioDeSesionOk() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = MainViewModel(usuarioRepository, cuentaRepository)

            coEvery{ usuarioRepository.getByName("usuario") } returns UsuarioEntity(1,"usuario")

            instance.estadoinicioSesion.observeForever {
                assertThat(it).isEqualTo(MainViewModel.estadoInicioSesion.OK)
            }

            instance.iniciarSesion("usuario")

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun inicioDeSesionNo() {


        couroutineTestRule.testDispatcher.runBlockingTest {


            instance = MainViewModel(usuarioRepository, cuentaRepository)

            coEvery { usuarioRepository.getByName(any()) }

            instance.estadoinicioSesion.observeForever {
                assertThat(it).isEqualTo(MainViewModel.estadoInicioSesion.ERROR)
            }

            instance.iniciarSesion("name")

        }

    }
}