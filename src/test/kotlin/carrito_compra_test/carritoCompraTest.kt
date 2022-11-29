package carrito_compra_test

import carrito_compra.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class carritoCompraTest {

    var numeroProducto = 0
    var tamañoCarro = 10
    var carritoCompra: Array<Array<String>> = arrayOf(
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00")
    )


    @BeforeEach
    fun antesDeLosTest(){
        numeroProducto = 0
        tamañoCarro = 10
        carritoCompra = arrayOf(
            arrayOf("romero", "2.00", "2", "0.00", "1", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00")
        )
    }

    @Test
    fun calcularTotalConIVA() {
        var carritoCompra2 = arrayOf(
            arrayOf("romero", "2.00", "2", "4.00", "1", "4.84"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00")
        )
        assertEquals("4.84", calcularTotalConIVA(carritoCompra2))
    }

    @Test
    fun calcularTotalSinIVA() {
        var carritoCompra2 = arrayOf(
            arrayOf("romero", "2.00", "2", "4.00", "1", "4.84"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
            arrayOf("", "0.00", "0", "0.00", "0", "0.00")
        )
        assertEquals("4.0", calcularTotalSinIVA(carritoCompra2))
    }

    @Test
    fun calcularPrecioFinalIVATest() {
        assertEquals("4.84", calcularPrecioFinalIVA(carritoCompra, numeroProducto))
    }

    @Test
    fun calcularPrecioFinalTest() {
        assertEquals("4.0", calcularPrecioFinal(carritoCompra, numeroProducto))
    }

    @Test
    fun esValidoTipoIVATest() {
        var opcionV = "2"
        assertTrue(esValidoTipoIVA(opcionV))
    }

    @Test
    fun noEsValidoTipoIVATest(){
        var opcionI = "5"
        val res = assertThrows(IllegalArgumentException::class.java){
            esValidoTipoIVA(opcionI)
        }
        assertEquals("Esa opcion de IVA no existe.", res.message)
    }

    @Test
    fun esValidoCantidadTest() {
        var cant1 = 1
        var cant2 = 2
        var cant3 = 9
        var cant4 = 10
        assertAll(
            {assertTrue(esValidoCantidad(cant1, tamañoCarro))},
            {assertTrue(esValidoCantidad(cant2, tamañoCarro))},
            {assertTrue(esValidoCantidad(cant3, tamañoCarro))},
            {assertTrue(esValidoCantidad(cant4, tamañoCarro))}
        )
    }

    @Test
    fun noEsValidoCantidadTest(){
        var cant1 = 11
        val res1 = assertThrows(IllegalArgumentException::class.java){
            esValidoCantidad(cant1, tamañoCarro)
        }
        assertEquals("La cantidad no se encuentra entre 1 y 10", res1.message)
        tamañoCarro = 4
        var cant2 = 5
        val res2 = assertThrows(IllegalArgumentException::class.java){
            esValidoCantidad(cant2, tamañoCarro)
        }
        assertEquals("La cantidad no puede ser mayor que la cantidad de productos restantes que podemos meter al carro", res2.message)
    }

    @Test
    fun esValidoPrecioUnitarioTest() {
        var cant1 = 0.10
        var cant2 = 2.00
        assertAll(
            {assertTrue(esValidoPrecioUnitario(cant1))},
            {assertTrue(esValidoPrecioUnitario(cant2))}
        )
    }

    @Test
    fun noEsValidoPrecioUnitarioTest(){
        var cant = 0.00
        val res = assertThrows(IllegalArgumentException::class.java){
            esValidoPrecioUnitario(cant)
        }
        assertEquals("No puede existir un precio unitario menor a 0.1 euros", res.message)
        var cant1 = -1.0000
        val res1 = assertThrows(IllegalArgumentException::class.java){
            esValidoPrecioUnitario(cant1)
        }
        assertEquals("Ese precio unitario no cumple con el patrón necesario, debe tener como mínimo un decimal y como máximo 2 decimales", res1.message)
    }

    @Test
    fun esValidoNombreTest(){
        var nombreV = "clavo"
        assertTrue(esValidoNombre(nombreV))
    }

    @Test
    fun noEsValidoNombreTest(){
        var nombreI = "-.´`,+  hola -."
        var res = assertThrows(IllegalArgumentException::class.java){
            esValidoNombre(nombreI)
        }
        assertEquals("El nombre no puede tener más que letras y números", res.message)
    }
}