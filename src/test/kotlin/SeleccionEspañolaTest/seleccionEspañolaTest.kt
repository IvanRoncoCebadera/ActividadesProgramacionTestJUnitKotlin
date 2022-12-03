package SeleccionEspañolaTest

import ModelsSeleccionEspañola.Entrenador
import ModelsSeleccionEspañola.Jugador
import ModelsSeleccionEspañola.Seleccion
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class seleccionEspañolaTest {

    var seleccion: Seleccion? = Seleccion("España", Entrenador("Iván"))
    var jugador: Jugador? = Jugador(2,"Iván",TipoDemarcacion.PORTERO)
    var jugadores: Array<Jugador?> = Array<Jugador?>(15) {null}
    val opcion = 2
    val opcionI = -4
    val dorsal = 1
    val dorsalI = -1
    val nombre = "Iván"
    val nombreI = ""
    val nombreI2 = "7398592"

    @BeforeEach
    fun setUp(){
        jugadores[0] = Jugador(1,"Marianito",TipoDemarcacion.DEFENSA)
        jugadores[1] = Jugador(5,"Marianito",TipoDemarcacion.DEFENSA)
        jugadores[2] = Jugador(3,"Marianito",TipoDemarcacion.PORTERO)
        jugadores[3] = Jugador(12,"Marianito",TipoDemarcacion.CENTROCAMPISTA)
        jugadores[4] = Jugador(7,"Marianito",TipoDemarcacion.DELANTERO)
    }

    @Test
    fun opcionValidaJugadoresTest(){
        assertTrue(seleccion!!.opcionValidaJugadores(opcion))
    }

    @Test
    fun opcionNoValidaTest(){
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { seleccion!!.opcionValidaJugadores(opcionI) }
        assertEquals("No has elegido una de las opciones permitidas, vuelva a probar:", mensaje.message)
    }

    @Test
    fun dorsalValidoTest(){
        assertTrue(seleccion!!.dorsalValido(dorsal))
    }

    @Test
    fun dorsalNoValidoTest(){
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { seleccion!!.dorsalValido(dorsalI) }
        assertEquals("El dorsal no puede ser negativo, vuelve a probar:", mensaje.message)
        var mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { seleccion!!.dorsalValido(1) }
        assertEquals("El dorsal que has seleccionado ya estaba elegido, vuelve a probar", mensaje2.message)
    }

    @Test
    fun nombreValidoTest(){
        assertTrue(seleccion!!.nombreValido(nombre))
    }

    @Test
    fun nombreNoValidoTest(){
        var mensaje: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { seleccion!!.nombreValido(nombreI) }
        assertEquals("No puedes introducir un nombre vacio, vuelve a probar:", mensaje.message)
        var mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { seleccion!!.nombreValido(nombreI2) }
        assertEquals("No has introducido un nombre válido, vuelve a probar:", mensaje1.message)
    }

    @Test
    fun ordenarMetodoBurbujaAscendenteTest(){
        var jugadores2 = Array<Jugador?>(15) { null }
        jugadores2[0] = Jugador(1,"Marianito",TipoDemarcacion.DEFENSA)
        jugadores2[1] = Jugador(3,"Marianito",TipoDemarcacion.PORTERO)
        jugadores2[2] = Jugador(5,"Marianito",TipoDemarcacion.DEFENSA)
        jugadores2[3] = Jugador(7,"Marianito",TipoDemarcacion.DELANTERO)
        jugadores2[4] = Jugador(12,"Marianito",TipoDemarcacion.CENTROCAMPISTA)
        assertArrayEquals(jugadores2, seleccion!!.ordenarMetodoBurbujaAscendente(jugadores))
    }

    @Test
    fun buscarJugadorPorDorsalTest(){
        var dorsalC = 1
        val dorsalFallo = -1
        assertAll(
            { assertEquals(Jugador(1,"Marianito",TipoDemarcacion.DEFENSA), seleccion!!.buscarJugadorPorDorsal(jugadores, dorsalC)) },
            { assertEquals(null, seleccion!!.buscarJugadorPorDorsal(jugadores, dorsalFallo)) },
        )
    }

    @Test
    fun primerNullTest(){
        val posC = 5
        val posFallo = -1
        var jugadores2 = Array<Jugador?>(5) { null }
        jugadores2[0] = Jugador(1,"Marianito",TipoDemarcacion.DEFENSA)
        jugadores2[1] = Jugador(3,"Marianito",TipoDemarcacion.PORTERO)
        jugadores2[2] = Jugador(5,"Marianito",TipoDemarcacion.DEFENSA)
        jugadores2[3] = Jugador(7,"Marianito",TipoDemarcacion.DELANTERO)
        jugadores2[4] = Jugador(12,"Marianito",TipoDemarcacion.CENTROCAMPISTA)
        assertAll(
            { assertEquals(posC, seleccion!!.primerNull(jugadores)) },
            { assertEquals(posFallo, seleccion!!.primerNull(jugadores2)) },
        )
    }

    @Test
    fun createTest(){
        assertEquals(Jugador(2,"Iván",TipoDemarcacion.PORTERO), Jugador.create(2, "Iván", TipoDemarcacion.PORTERO))
    }

    @Test
    fun informacionTest(){
        val mensaje = println(seleccion!!.informacion(jugadores))
        assertEquals(mensaje, seleccion!!.informacion(jugadores))
    }

    @Test
    fun eliminarJugadorTest(){
        jugadores[0] = null
        assertArrayEquals(jugadores, seleccion!!.eliminarJugador(jugadores, 1))
    }

    @Test
    fun actualizarJugadorTest(){
        jugadores[0] = Jugador(1, "Iván", TipoDemarcacion.PORTERO)
        assertEquals(jugadores[0], seleccion!!.actualizarJugador(jugadores[0]!!.copy(nombre = "Iván", demarcacion =  TipoDemarcacion.PORTERO), 1) )
    }
}