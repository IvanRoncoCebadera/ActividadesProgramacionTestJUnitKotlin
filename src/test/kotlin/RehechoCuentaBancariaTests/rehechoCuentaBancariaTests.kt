package RehechoCuentaBancariaTests

import RehechoModelsCuentaBancaria.RehechoCuenta
import RehechoModelsCuentaBancaria.RehechoTitular
import org.junit.jupiter.api.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class rehechoCuentaBancariaTests {

    var cuenta = RehechoCuenta("674683vjnhF4", 200.00)

    @BeforeEach
    fun setup(){
        cuenta.saldo = 200.0
        cuenta.titulares[0] = RehechoTitular("53907934M", "Iván", "685697988")
    }

    @Test
    fun opcionValidoTest(){
        val opcion = 4
        assertTrue(cuenta.opcionValida(opcion))
    }

    @Test
    fun opcionNoValido(){
        val opcionI1 = -1
        val mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.opcionValida(opcionI1) }
        assertEquals("La opción introducida no es una de las opciones posibles, vuelva a probar:", mensaje.message)
    }

    @Test
    fun cantidadValidaTest(){
        val cantidad = 200.00
        assertTrue(cuenta.cantidadValida(cantidad))
    }

    @Test
    fun cantidadNoValida(){
        val cantidadI1 = 0.0
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.cantidadValida(cantidadI1) }
        assertEquals("La cantidad debe estar entre 0.1 y 500.0, vuelve a probar:", mensaje.message)
    }

    @Test
    fun nombreValidoTest(){
        val nombre = "Iván"
        assertTrue(cuenta.nombreValido(nombre))
    }

    @Test
    fun nombreNoValido(){
        val nombreI1 = ""
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.nombreValido(nombreI1) }
        assertEquals("El nombre no puede estar vacio, vuelve a probar:", mensaje.message)
        val nombreI2 = "898080"
        mensaje = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.nombreValido(nombreI2) }
        assertEquals("El nombre introducido no puede tener mas que letras, vuelve a probar:", mensaje.message)
    }

    @Test
    fun dniValidoTest(){
        val dni = "53907934M"
        assertTrue(cuenta.dniValido(dni))
    }

    @Test
    fun dniNoValido(){
        val dniI1 = ""
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.dniValido(dniI1) }
        assertEquals("El dni no puede estar vacio, vuelve a probar:", mensaje.message)
        val dniI2 = "898080"
        mensaje = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.dniValido(dniI2) }
        assertEquals("El dni introducido no cumple con el patrón esperado, vuelve a probar:", mensaje.message)
    }

    @Test
    fun telefonoValidoTest(){
        val telefono = "685697988"
        assertTrue(cuenta.telefonoValido(telefono))
    }

    @Test
    fun telefonoNoValido(){
        val telefonoI1 = ""
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.telefonoValido(telefonoI1) }
        assertEquals("El telefono no puede estar vacio, vuelve a probar:", mensaje.message)
        val telefonoI2 = "898080"
        mensaje = Assertions.assertThrows(IllegalArgumentException::class.java) { cuenta!!.telefonoValido(telefonoI2) }
        assertEquals("El telefono introducido no cumple con el patrón esperado, vuelve a probar:", mensaje.message)
    }

    @Test
    fun mostrarTodosLosTitularesTest(){
        var mensaje = println(cuenta.mostrarTodosLosTitulares())
        assertEquals(println(cuenta.mostrarTodosLosTitulares()), mensaje)
    }

    @Test
    fun mostrarTitularPorDniTest(){
        var mensaje = println(cuenta.mostrarTitularPorDni(0))
        assertEquals(println(cuenta.mostrarTitularPorDni(0)), mensaje)
    }

    @Test
    fun crearTitularTest(){
        val nuevoTitular = cuenta.crearTitular("53907934M", "Iván", "685697988")
        assertEquals(cuenta.titulares[0], nuevoTitular)
    }

    @Test
    fun borrarTitularTest(){
        val titularBorrado = cuenta.borrarTitular(0)
        assertEquals(RehechoTitular("53907934M", "Iván", "685697988"), titularBorrado)
    }

    @Test
    fun actualizarTitularTest(){
        val nuevoTitular = cuenta.actualizarTitular("Iván", "685697988", cuenta.titulares[0])
        assertEquals(cuenta.titulares[0], nuevoTitular)
    }

    @Test
    fun buscarTitularPorDniTest(){
        val dni = "53907934M"
        assertEquals(0, cuenta.buscarTitularPorDni(dni))
    }

    @Test
    fun buscarHuecoLibreTes(){
        assertEquals(1, cuenta.buscarHuecoLibre())
    }

    @Test
    fun ingresarDineroTest(){
        cuenta.ingresarDinero(20.0)
        assertEquals(220.0, cuenta.saldo)
    }

    @Test
    fun sacarDineroTest(){
        cuenta.sacarDinero(20.0)
        assertEquals(180.0, cuenta.saldo)
    }
}