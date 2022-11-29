package dniTest

import dni.*
import org.junit.jupiter.api.Assertions.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class validardniTest {

    @Test
    fun comprobarPatronDNITest(){
        val dniV = "12345678M".uppercase()
        val dniI = "12345B84M".uppercase()
        assertAll(
            { assertEquals(true, comprobarPatronDNI(dniV)) },
            { assertEquals(false, comprobarPatronDNI(dniI)) }
        )
    }

    @Test
    fun comprobarLongitudTest(){
        val dniV = "12345678M".uppercase()
        val dniI = "12332M".uppercase()
        val longitud = 9
        assertAll(
            { assertEquals(true, comprobarLongitud(dniV, longitud)) },
            { assertEquals(false, comprobarLongitud(dniI, longitud)) }
        )
    }

    @Test
    fun comprobarLetraTest(){
        val dniV = "53907934M".uppercase()
        val dniI = "12345678M".uppercase()
        val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
        assertAll(
            { assertEquals(true, comprobarLetra(dniV, letras)) },
            { assertEquals(false, comprobarLetra(dniI, letras)) }
        )
    }

    @Test
    fun substringTest() {
        val dni = "53907934M".uppercase()
        assertEquals(53907934, substring(0, 7, dni))
    }

    @Test
    fun restasSucesivasTest(){
        val dni = 53907934
        assertEquals(5, restasSucesivas(dni))
    }

}