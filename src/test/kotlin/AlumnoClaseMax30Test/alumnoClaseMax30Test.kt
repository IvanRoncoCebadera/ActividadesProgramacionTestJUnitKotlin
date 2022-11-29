package AlumnoClaseMax30Test

import AlumnoClaseMax30.*
import Models.AlumnosMax30
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class alumnoClaseMax30Test {

    var clase = Array<AlumnosMax30?>(30) { null }
    var opcion = 4
    var dni = "53907934M"
    var nombre = "Iván"
    var calificacion = 5.0

    @BeforeEach
    fun antesDeTodo(){
        clase[0] = AlumnosMax30("53986048A", "Iván", 5.89)
        clase[1] = AlumnosMax30("53364648D", "Manuel", 10.0)
        clase[2] = AlumnosMax30("26486048G", "Belén", 2.45)
        clase[3] = AlumnosMax30("52928599F", "Cristobal", 0.34)
        clase[4] = AlumnosMax30("53739520Z", "Mario", 7.6524)
    }

    @Test
    fun opcionValidaTest(){
        assertTrue(opcionValida(opcion))
    }

    @Test
    fun opcionNoValidaTest(){
        var opcionI = 8
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { opcionValida(opcionI) }
        assertEquals("No has elegido una de las opciones posibles", mensaje.message)
    }

    @Test
    fun DNIValidoTest(){
        assertTrue(DNIValido(dni))
    }

    @Test
    fun DNINoValidoTest(){
        var dniI = "7988B443"
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { DNIValido(dniI) }
        assertEquals("No has introducido un dni válido", mensaje.message)
    }

    @Test
    fun nombreValidoTest(){
        assertTrue(nombreValido(nombre))
    }

    @Test
    fun nombreNoValidoTest(){
        var nombreI = ""
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { nombreValido(nombreI) }
        assertEquals("El nombre no puede estar vacio", mensaje.message)
    }

    @Test
    fun calificacionValidaTest(){
        assertTrue(calificacionValida(calificacion))
    }

    @Test
    fun calificacionNoValidaTest(){
        var calificacionI = -1.0
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { calificacionValida(calificacionI) }
        assertEquals("La calificacion, no puede ser menor que 0, ni mayor que 10", mensaje.message)
    }

    @Test
    fun ordenarMetodoBurbujaDescendenteTest(){
        var clase2 = Array<AlumnosMax30?>(30) { null }
        clase2[0] = AlumnosMax30("53364648D", "Manuel", 10.0)
        clase2[1] = AlumnosMax30("53739520Z", "Mario", 7.6524)
        clase2[2] = AlumnosMax30("53986048A", "Iván", 5.89)
        clase2[3] = AlumnosMax30("26486048G", "Belén", 2.45)
        clase2[4] = AlumnosMax30("52928599F", "Cristobal", 0.34)
        assertArrayEquals(clase2, ordenarMetodoBurbujaDescendente(clase))
    }

    @Test
    fun leerPorDNITest(){
        val posC = 1
        var dniC = "53986048A"
        val posFallo = -1
        var dniNoExistente = "539353FGS"
        assertAll(
            { assertEquals(posC, leerPorDNI(clase, dniC)) },
            { assertEquals(posFallo, leerPorDNI(clase, dniNoExistente)) },
        )
    }

    @Test
    fun primerNullTest(){
        val posC = 5
        val posFallo = -1
        var clase2 = Array<AlumnosMax30?>(5) { null }
        clase2[0] = AlumnosMax30("53364648D", "Manuel", 10.0)
        clase2[1] = AlumnosMax30("53739520Z", "Mario", 7.6524)
        clase2[2] = AlumnosMax30("53986048A", "Iván", 5.89)
        clase2[3] = AlumnosMax30("26486048G", "Belén", 2.45)
        clase2[4] = AlumnosMax30("52928599F", "Cristobal", 0.34)
        assertAll(
            { assertEquals(posC, primerNull(clase)) },
            { assertEquals(posFallo, primerNull(clase2)) },
        )
    }
}