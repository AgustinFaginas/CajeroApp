package ar.edu.unlam.cajeroapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import ar.edu.unlam.cajeroapp.model.Usuario

import ar.edu.unlam.cajeroapp.ui.RegistrarseViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegistrarseViewModelTest {

    lateinit var instance:RegistrarseViewModel

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
    fun registroUsuarioOk() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = RegistrarseViewModel(usuarioRepository, cuentaRepository)

            coEvery { usuarioRepository.getAll() } returns listOf(UsuarioEntity(0,"Pablo"))

            coEvery { usuarioRepository.getByName("Jose") } returns UsuarioEntity(1,"Jose")


            instance.estadoRegistracion.observeForever{
                Assertions.assertThat(it).isEqualTo(RegistrarseViewModel.EstadoRegistro.OK)
            }



            instance.guardarUsuario(Usuario("Jose"))


        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun registroUsuarioError() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = RegistrarseViewModel(usuarioRepository, cuentaRepository)

            coEvery { usuarioRepository.getAll() }

            instance.estadoRegistracion.observeForever{
                Assertions.assertThat(it).isEqualTo(RegistrarseViewModel.EstadoRegistro.ERROR)
            }


            instance.guardarUsuario(Usuario("Jose"))


        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun registroUsuarioExisteUsuario() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = RegistrarseViewModel(usuarioRepository, cuentaRepository)

            coEvery {usuarioRepository.getAll() } returns listOf(UsuarioEntity(2,"Jose"))


            instance.estadoRegistracion.observeForever{
                Assertions.assertThat(it).isEqualTo(RegistrarseViewModel.EstadoRegistro.EXISTEUSUARIO)
            }


            instance.guardarUsuario(Usuario("Jose"))


        }
    }


}