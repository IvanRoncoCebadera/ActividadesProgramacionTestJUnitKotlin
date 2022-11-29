package DAMZumTest

import enviar
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertAll
import recibir
import validarDinero
import validarOpcion
import validarSaldo
import verEstado
import kotlin.test.Test
import kotlin.test.assertEquals

class DAMZumTests {

    var saldo: Double = 0.00

    @BeforeEach
    fun fijarSaldo(){
        saldo = 600.00
    }

    @Test
    fun validarOpcionTest(){
        var opcionV = 2
        var opcionI = 5
        assertAll(
            { assertEquals(true, validarOpcion(opcionV))},
            { assertEquals(false, validarOpcion(opcionI))}
        )
    }

    @Test
    fun validarSaldoTest(){
        var saldoV = "500.00"
        var saldoI1 = "0.05"
        var saldoI2 = "5.5"
        assertAll(
            { assertEquals(true, validarSaldo(saldoV))},
            { assertEquals(false, validarSaldo(saldoI1))},
            { assertEquals(true, validarSaldo(saldoI2))}
        )
    }

    @Test
    fun validarDineroTest(){
        var dineroV = "300.00"
        var dineroI1 = "555.00"
        var dineroI2 = "651.00"
        var dineroI3 = "5.5"
        assertAll(
            { assertEquals(true, validarDinero(dineroV, saldo))},
            { assertEquals(false, validarDinero(dineroI1, saldo))},
            { assertEquals(false, validarDinero(dineroI2, saldo))},
            { assertEquals(true, validarDinero(dineroI3, saldo))}
        )
    }

    @Test
    fun enviarTest(){
        val dinero = 100.00
        assertEquals((saldo - dinero), enviar(dinero, saldo))
    }

    @Test
    fun recibirTest(){
        val dinero = 100.00
        assertEquals((saldo + dinero), recibir(dinero, saldo))
    }

    @Test
    fun verEstadoTest(){
        assertEquals("Tienes en cuenta, un total de $saldo euros.", verEstado(saldo))
    }

}