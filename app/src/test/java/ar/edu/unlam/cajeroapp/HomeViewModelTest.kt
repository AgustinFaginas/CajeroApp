package ar.edu.unlam.cajeroapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import ar.edu.unlam.cajeroapp.ui.HomeViewModel
import ar.edu.unlam.cajeroapp.ui.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class HomeViewModelTest {

    lateinit var instance: HomeViewModel

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
    fun depositoOK() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = HomeViewModel(cuentaRepository, usuarioRepository)

            val cuentaAct = CuentaEntity(1, 20, 0)

            coJustRun { cuentaRepository.update(cuentaAct) }

            instance.cuenta.observeForever {
                Assertions.assertThat(it).isEqualTo(cuentaAct)
            }

            instance.depositar("10")

            assertThat(cuentaAct.dinero == 30)

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun depositoError() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = HomeViewModel(cuentaRepository, usuarioRepository)


            coJustRun { cuentaRepository.update(any()) }

            instance.estadoDeposito.observeForever {
                Assertions.assertThat(it).isEqualTo(HomeViewModel.EstadoDeposito.ERROR)
            }


            instance.depositar("10")

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun depositoStringVacio() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = HomeViewModel(cuentaRepository, usuarioRepository)

            val cuentaAct = CuentaEntity(1, 20, 0)

            coJustRun { cuentaRepository.update(cuentaAct) }

            instance.estadoDeposito.observeForever {
                Assertions.assertThat(it).isEqualTo(HomeViewModel.EstadoDeposito.STRING_VACIO)
            }

            instance.depositar("")

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun extraccionOK() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = HomeViewModel(cuentaRepository, usuarioRepository)

            val cuentaAct = CuentaEntity(1, 20, 0)

            coJustRun { cuentaRepository.update(cuentaAct) }

            instance.cuenta.observeForever {
                Assertions.assertThat(it).isEqualTo(cuentaAct)
            }

            instance.extraer("10")

            assertThat(cuentaAct.dinero == 10)

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun extraccionDineroInsuficiente() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = HomeViewModel(cuentaRepository, usuarioRepository)

            val cuentaAct = CuentaEntity(1, 20, 0)

            coJustRun { cuentaRepository.update(cuentaAct) }

            instance.estadoDeposito.observeForever {
                Assertions.assertThat(it)
                    .isEqualTo(HomeViewModel.EstadoDeposito.DINERO_INSUFICIENTE)
            }

            instance.extraer("2000")


        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun extraerStringVacio() {
        couroutineTestRule.testDispatcher.runBlockingTest {

            instance = HomeViewModel(cuentaRepository, usuarioRepository)

            val cuentaAct = CuentaEntity(1, 20, 0)

            coJustRun { cuentaRepository.update(cuentaAct) }

            instance.estadoDeposito.observeForever {
                Assertions.assertThat(it).isEqualTo(HomeViewModel.EstadoDeposito.STRING_VACIO)
            }

            instance.extraer("")

        }
    }


}