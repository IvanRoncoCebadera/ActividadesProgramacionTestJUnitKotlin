package SalaDeCineTests

import ModelsSalaDeCine.butaca
import ModelsSalaDeCine.pelicula
import ModelsSalaDeCine.salaDeCine
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class salaDeCineTests {

    var salaDeCine = salaDeCine("s",5,5, pelicula.create("s", 1960, "s", "s"))

    @BeforeEach
    fun setup(){
        for(i in 0 until salaDeCine.butacas.size){
            for(j in 0 until salaDeCine.butacas[i].size){
                salaDeCine.butacas[i][j]!!.estado = "libre"
            }
        }
    }

    @Test
    fun crearPeliculaTest(){
        val nuevaPelicula = pelicula.create("s", 1960, "s", "s")
        assertEquals(pelicula("s",1960,"s","s"), nuevaPelicula)
    }

    @Test
    fun tituloValidoTest(){
        val titulo = "Los tres Mosqueteros"
        assertTrue(salaDeCine.pelicula.tituloValido(titulo))
    }

    @Test
    fun tituloNoValidoTest(){
        val titulo1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.tituloValido(titulo1) }
        val titulo2 = ""
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.tituloValido(titulo2) }
        assertAll(
            {assertEquals("El título no puede ser nulo, vuelve a probar:", mensaje1.message)},
            {assertEquals("El título de la peli no puede estar vacio, vuelve a probar:", mensaje2.message)}
        )
    }

    @Test
    fun añoPublicacionValidoTest(){
        val añoPublicacion = 2022
        assertTrue(salaDeCine.pelicula.añoPublicacionValido(añoPublicacion))
    }

    @Test
    fun añoPublicacionNoValidoTest(){
        val añoPublicacion1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.añoPublicacionValido(añoPublicacion1) }
        val añoPublicacion2 = -1
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.añoPublicacionValido(añoPublicacion2) }
        val añoPublicacion3 = 1949
        val mensaje3: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.añoPublicacionValido(añoPublicacion3) }
        val añoPublicacion4 = 2023
        val mensaje4: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.añoPublicacionValido(añoPublicacion4) }
        assertAll(
            {assertEquals("El año de publicación no puede ser nulo, vuelve a probar:", mensaje1.message)},
            {assertEquals("El año de publicación no puede ser negativo, vuelve a probar:" , mensaje2.message)},
            {assertEquals("El año de publicación de la peli debe ser entre 1950 y 2022, vuelve a probar:", mensaje3.message)},
            {assertEquals("El año de publicación de la peli debe ser entre 1950 y 2022, vuelve a probar:", mensaje4.message)}
        )
    }

    @Test
    fun directorValidoTest(){
        val director = "Jorge"
        assertTrue(salaDeCine.pelicula.directorValido(director))
    }

    @Test
    fun directorNoValidoTest(){
        val director1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.directorValido(director1) }
        val director2 = ""
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.directorValido(director2) }
        assertAll(
            {assertEquals("El director no puede ser nulo, vuelve a probar:", mensaje1.message)},
            {assertEquals("El director de la peli no puede estar vacio, vuelve a probar:", mensaje2.message)}
        )
    }

    @Test
    fun generoValidoTest(){
        val genero = "Ciencia Ficción"
        assertTrue(salaDeCine.pelicula.generoValido(genero))
    }

    @Test
    fun generoNoValidoTest(){
        val genero1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.generoValido(genero1) }
        val genero2 = ""
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.pelicula.generoValido(genero2) }
        assertAll(
            {assertEquals("El género no puede ser nulo, vuelve a probar:", mensaje1.message)},
            {assertEquals("El género de la peli no puede estar vacio, vuelve a probar:", mensaje2.message)}
        )
    }

    @Test
    fun crearButacaTest(){
        val nuevaButaca = salaDeCine.butacas[0][0]!!.create("A1","libre")
        assertEquals(butaca("A1","libre"), nuevaButaca)
    }

    @Test
    fun crearSalaDeCineTest(){
        val nuevaSalaDeCine = salaDeCine.create("s",5,5, pelicula.create("s", 1960, "s", "s"))
        assertEquals(salaDeCine("s",5,5, pelicula.create("s", 1960, "s", "s")), nuevaSalaDeCine)
    }

    @Test
    fun filaColumnaValidaTest(){
        val filaColumna = 8
        assertTrue(salaDeCine.filaColumnaValida(filaColumna))
    }

    @Test
    fun filaColumnaNoValidaTest(){
        val filaColumna1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.filaColumnaValida(filaColumna1) }
        val filaColumna2 = 0
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.filaColumnaValida(filaColumna2) }
        val filaColumna3 = 27
        val mensaje3: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.filaColumnaValida(filaColumna3) }
        assertAll(
            {assertEquals("La fila/columna no puede ser nulo, vuelve a probar:", mensaje1.message)},
            {assertEquals("La fila/columna no puede ser menor que 1, vuelve a probar:" , mensaje2.message)},
            {assertEquals("La fila/columna sobrepasar el tamaño 26, vuelve a probar:", mensaje3.message)}
        )
    }

    @Test
    fun nombreValidoTest(){
        val nombre = "Ciencia Ficción"
        assertTrue(salaDeCine.nombreValido(nombre))
    }

    @Test
    fun nombreNoValidoTest(){
        val nombre1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.nombreValido(nombre1) }
        val nombre2 = ""
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.nombreValido(nombre2) }
        assertAll(
            {assertEquals("El nombre no puede ser nulo, vuelve a probar:", mensaje1.message)},
            {assertEquals("El nombre no puede estar vacio, vuelve a probar:", mensaje2.message)}
        )
    }

    @Test
    fun butacaValidaTest(){
        val butaca1 = "A1"
        val butaca2 = "stop"
        assertAll(
            {assertTrue(salaDeCine.butacaValida(butaca1))},
            {assertTrue(salaDeCine.butacaValida(butaca2))}
        )
    }

    @Test
    fun butacaNoValidaTest(){
        val butaca1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.butacaValida(butaca1) }
        val butaca2 = ""
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.butacaValida(butaca2) }
        val butaca3 = "5B"
        val mensaje3: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.butacaValida(butaca3) }
        assertAll(
            {assertEquals("El mensaje no puede ser nulo, vuelve a probar:", mensaje1.message)},
            {assertEquals("El mensaje no puede estar vacio, vuelve a probar:", mensaje2.message)},
            {assertEquals("EL menaje introducido no es válido, vuelve a probar:", mensaje3.message)}
        )
    }

    @Test
    fun opcionValidaTest(){
        val opcion = 4
        assertTrue(salaDeCine.opcionValida(opcion))
    }

    @Test
    fun opcionNoValidaTest(){
        val opcion1 = null
        val mensaje1: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.opcionValida(opcion1) }
        val opcion2 = -1
        val mensaje2: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.opcionValida(opcion2) }
        val opcion3 = 7
        val mensaje3: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) { salaDeCine.opcionValida(opcion3) }
        assertAll(
            {assertEquals("La opción no puede ser nula, vuelve a probar:", mensaje1.message)},
            {assertEquals("No has elegido una de las opciones posibles, vuelve a probar:" , mensaje2.message)},
            {assertEquals("No has elegido una de las opciones posibles, vuelve a probar:", mensaje3.message)}
        )
    }

    @Test
    fun hallarIdentificadorButacaTest(){
        val fila = 1
        val columna = 1
        val identificadorButaca = "B2"
        assertEquals(identificadorButaca, salaDeCine.hallarIdentificadorButaca(fila, columna))
    }

    @Test
    fun comprarEntradaTest(){
        val posicion = Pair(0,0)
        assertEquals("comprado", salaDeCine.comprar(posicion)!!.estado)
    }

    @Test
    fun reservarEntradaTest(){
        val posicion = Pair(0,0)
        assertEquals("reservado", salaDeCine.reservar(posicion)!!.estado)
    }

    @Test
    fun anularEntradaTest(){
        val posicion = Pair(0,0)
        assertEquals("libre", salaDeCine.anular(posicion)!!.estado)
    }

    @Test
    fun informeDeLaSalaDeCineTest(){
        val mensaje = println(salaDeCine.informeDeLaSalaDeCine())
        assertEquals(mensaje, salaDeCine.informeDeLaSalaDeCine())
    }

    @Test
    fun calcularDineroEnCajaTest(){
        salaDeCine.butacas[0][0]!!.estado = "comprado"
        salaDeCine.butacas[0][0]!!.esVip = false
        salaDeCine.butacas[0][1]!!.estado = "comprado"
        salaDeCine.butacas[0][1]!!.esVip = true
        val dinero = 13.75
        assertEquals(dinero, salaDeCine.calcularDineroEnCaja())
    }

    @Test
    fun contarNumeroDeButacasPorTipoTest(){
        salaDeCine.butacas[0][0]!!.estado = "comprado"
        salaDeCine.butacas[0][1]!!.estado = "comprado"
        salaDeCine.butacas[0][2]!!.estado = "comprado"
        salaDeCine.butacas[0][3]!!.estado = "comprado"
        salaDeCine.butacas[0][4]!!.estado = "comprado"
        salaDeCine.butacas[1][0]!!.estado = "reservado"
        salaDeCine.butacas[1][1]!!.estado = "reservado"
        salaDeCine.butacas[1][2]!!.estado = "reservado"
        salaDeCine.butacas[1][3]!!.estado = "reservado"
        salaDeCine.butacas[1][4]!!.estado = "reservado"
        val numeroTipoButacas = Triple(15, 5, 5)
        assertEquals(numeroTipoButacas, salaDeCine.contarNumeroDeButacasPorTipo())
    }

    @Test
    fun representarButacasTest(){
        val mensaje = println(salaDeCine.representarButacas())
        assertEquals(mensaje, salaDeCine.representarButacas())
    }

    @Test
    fun buscarButacaPorIdentificadorTest(){
        val identificador1 = "A1"
        val posicion1 = Pair(0,0)
        val identificador2 = "Z27"
        val posicion2 = Pair(-1,-1)
        assertAll(
            {assertEquals(posicion1, salaDeCine.buscarButacaPorIndentificador(identificador1))},
            {assertEquals(posicion2, salaDeCine.buscarButacaPorIndentificador(identificador2))}
        )
    }

}