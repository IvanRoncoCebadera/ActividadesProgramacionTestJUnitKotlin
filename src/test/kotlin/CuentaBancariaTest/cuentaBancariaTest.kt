package CuentaBancariaTest

import ModelsCuentaBancaria.Cuenta
import ModelsCuentaBancaria.Dinero
import ModelsCuentaBancaria.Titular
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class cuentaBancariaTest {

    var cuenta: Cuenta? = Cuenta("Iván")
    var titulares: Array<Titular?> = Array<Titular?>(3){null}
    var dineroTitulares: Array<Dinero?> = Array<Dinero?>(3){null}

    @BeforeEach
    fun setUp(){
        titulares[0] = Titular("53907934M", "Iván", "ivanrc2004@gmail.com")
        dineroTitulares[0] = Dinero(250.0)
        titulares[1] = Titular("78389934M", "Manuel", "manuelcito@gmail.com")
        dineroTitulares[1] = Dinero(250.0)
    }

    @Test
    fun opcionValidaTest(){
        val opcion = 3
        assertTrue(cuenta!!.opcionValida(opcion))
    }

    @Test
    fun opcionNoValidaTest(){
        val opcionI = -5
        val mensaje: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.opcionValida(opcionI)  }
        assertEquals("La opcion elegida no existe, vuelve a probar:", mensaje.message)
    }

    @Test
    fun dniValidoTest(){
        val dni = "53907934M"
        assertTrue(cuenta!!.dniValido(dni))
    }

    @Test
    fun dniNoValidoTest(){
        val dniI = "5"
        val mensaje: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.dniValido(dniI)  }
        assertEquals("El dni introducido no es acepable, vuelve a probar:", mensaje.message)
    }

    @Test
    fun nombreValidoTest(){
        val nombre = "Iván"
        assertTrue(cuenta!!.nombreValido(nombre))
    }

    @Test
    fun nombreNoValidoTest(){
        val nombreI = ""
        val mensaje: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.nombreValido(nombreI)  }
        assertEquals("El nombre no puede estar vacio, vuelve a probar:", mensaje.message)
        val nombreI2 = "42525323"
        val mensaje2: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.nombreValido(nombreI2)  }
        assertEquals("El nombre no puede contener números, vuelve a probar:", mensaje2.message)
    }

    @Test
    fun emailValidoTest(){
        val email = "ivan@gmail.com"
        assertTrue(cuenta!!.emailValido(email))
    }

    @Test
    fun emailNoValidoTest(){
        val emailI = "ouhco3oiee"
        val mensaje: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.emailValido(emailI)  }
        assertEquals("El email introducido no es acepable, vuelve a probar:", mensaje.message)
    }

    @Test
    fun cantidadValidaTest(){
        val cantidad = 100.0
        assertTrue(cuenta!!.cantidadValida(cantidad))
    }

    @Test
    fun cantidadNoValidaTest(){
        val cantidadI = 0.0
        val mensaje: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.cantidadValida(cantidadI)  }
        assertEquals("La cantidad no puede ser 0.0, vuelve a probar:", mensaje.message)
        val cantidadI2 = -1.0
        val mensaje2: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.cantidadValida(cantidadI2)  }
        assertEquals("La cantidad no puede ser negativa, vuelve a probar:", mensaje2.message)
        val cantidadI3 = 501.0
        val mensaje3: IllegalArgumentException = assertThrows(IllegalArgumentException::class.java) { cuenta!!.cantidadValida(cantidadI3)  }
        assertEquals("La cantidad no puede ser mayor que 500.0, vuelve a probar:", mensaje3.message)
    }

    @Test
    fun buscarTitularPorDniTest(){
        val dni = "53907934M"
        assertEquals(0, cuenta!!.buscarTitularPorDni(titulares, dni))
        val dniI = "5"
        assertEquals(-1, cuenta!!.buscarTitularPorDni(titulares, dniI))
    }

    @Test
    fun ordenarMetodoBurbujaAscendenteTest(){
        val titulares2 = Array<Titular?>(3) { null }
        titulares2[0] = Titular("53907934M", "Iván", "ivanrc2004@gmail.com")
        titulares2[1] = Titular("78389934M", "Manuel", "manuelcito@gmail.com")
        //assertArrayEquals(titulares2, cuenta!!.ordenarMetodoBurbujaAscendente(titulares))
    }

    @Test
    fun primerNullTest(){
        val posC = 2
        val posFallo = -1
        val titulares2 = Array<Titular?>(3) { null }
        titulares2[0] = Titular("53907934M", "Iván", "ivanrc2004@gmail.com")
        titulares2[1] = Titular("78389934M", "Manuel", "manuelcito@gmail.com")
        titulares2[2] = Titular("78389934M", "Manuel", "manuelcito@gmail.com")
        assertAll(
            { assertEquals(posC, cuenta!!.primerNull(titulares)) },
            { assertEquals(posFallo, cuenta!!.primerNull(titulares2)) },
        )
    }

    @Test
    fun crearTitularTest(){
        val titular = Titular("53907934M", "Iván", "ivanrc2004@gmail.com")
        assertEquals(titular, cuenta!!.crearTitular("53907934M", "Iván", "ivanrc2004@gmail.com"))
    }

    @Test
    fun actualizarTitularTest(){
        val titular = Titular("53907934M", "Iván", "ivanrc2004@gmail.com")
        assertEquals(titular, cuenta!!.actualizarTitular(0,"53907934M", "Iván", "ivanrc2004@gmail.com"))
    }

    @Test
    fun borrarTitularTest(){
        val titular = null
        assertEquals(titular, cuenta!!.borrarTitular(0))
    }

    @Test
    fun informacionTest(){
        val mensaje = println(cuenta!!.informacion(titulares, dineroTitulares))
        assertEquals(mensaje, println(cuenta!!.informacion(titulares, dineroTitulares)))
    }
}