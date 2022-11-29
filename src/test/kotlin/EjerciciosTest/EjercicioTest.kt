package EjerciciosTest

import buscarElemento
import ordenarBurbujaAscendente
import ordenarBurbujaDescendente
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import palindromo
import reverse
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.Test

class EjercicioTest {

    @Test
    fun ordenarBurbujaAscendenteTest(){
        val array: IntArray = intArrayOf(2,4,1,5,3)
        var arrayOrdenado = intArrayOf(1,2,3,4,5)
        kotlin.test.assertEquals(arrayOrdenado, ordenarBurbujaAscendente(array))
    }

    @Test
    fun ordenarBurbujaDescendenteTest(){
        val array: IntArray = intArrayOf(2,4,1,5,3)
        var arrayOrdenado = intArrayOf(5,4,3,2,1)
        kotlin.test.assertEquals(arrayOrdenado, ordenarBurbujaDescendente(array))
    }

    @Test
    fun reverseTest(){
        val array: IntArray = intArrayOf(2,4,1,5,3)
        var arrayReverse = intArrayOf(3,5,1,4,2)
        kotlin.test.assertEquals(arrayReverse, reverse(array))
    }

    @Test
    fun buscarElementoTest(){
        val array: IntArray = intArrayOf(2,4,1,5,3)
        assertAll(
            { kotlin.test.assertEquals("El elemento que buscabas si existe, y se encuentra en la posicion 4", buscarElemento(array, 5)) },
            { kotlin.test.assertEquals("El elemento que buscabas no existe, FFFFFFFFFFFFFFFF", buscarElemento(array, 7)) }
        )
    }

    @Test
    fun palindromoTest(){
        val array: IntArray = intArrayOf(2,4,1,5,3)
        var array2 = intArrayOf(2,4,1,5,3)
        assertAll(
            { kotlin.test.assertEquals(true, palindromo(array, array)) },
            { kotlin.test.assertEquals(false, palindromo(array, array2)) }
        )
    }
}