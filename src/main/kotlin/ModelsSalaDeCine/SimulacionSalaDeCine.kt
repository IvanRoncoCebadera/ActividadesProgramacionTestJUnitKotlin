package ModelsSalaDeCine

fun main(){

    println("Primero introduce los datos de la sal del cine:")
    println("Introduce el nombre que tiene su cine:")
    val nombre = introducirNombre()
    println("Introduce el número de fila que tiene su cine:")
    val filas = introducirFilaColumna()
    println("Introduce el número de columnas que tiene su cine:")
    val columnas = introducirFilaColumna()

    println("Ahora introduce los datos de la pelicula que se va a mostrar:")
    println("Introduce el titulo de la pelicula:")
    val titulo = introducirTitulo()
    println("Introduce el año de publicación de la pelicula:")
    val añoPublicacion =  introducirAñoPublicación()
    println("Introduce el director de la pelicula:")
    val director = introducirDirector()
    println("Introduce el genero de la pelicula:")
    val genero = introducirGenero()

    var salaDeCine = salaDeCine(nombre, filas, columnas, pelicula.create(titulo, añoPublicacion, director, genero))

    println("La sala del cine es la siguiente:")
    salaDeCine.representacionInicialDeLaSala()

    var opcion = 0
    do{
        println("Seleccione la opción que desea:")
        opcion = menu()
        when(opcion){
            1 -> salaDeCine.comprarEntrada()
            2 -> salaDeCine.reservarEntrada()
            3 -> salaDeCine.formalizarReserva()
            4 -> salaDeCine.anularReservaCompra()
            5 -> salaDeCine.informeDeLaSalaDeCine()
            6 -> salaDeCine.dineroTotalEnCaja()
        }

    }while(opcion != 0)
    println("Adios...")

}

/**
 * función que sirve para introducir un identificador de butaca válido o "stop"
 * @return la butaca o el "stop" introducido por teclado
 */
fun introducirButaca(): String{
    var butaca = ""
    do{
        try{
            butaca = readln().trim()
            butacaValida(butaca)
        }catch(e: Exception){
            println(e.message)
            butaca = ""
        }
    }while(butaca == "")
    return butaca
}

/**
 * función que sirve para validar la butaca o el "stop" introducido por teclado
 * @param butaca lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun butacaValida(butaca: String?): Boolean {
    require(butaca != null){("El mensaje no puede ser nulo, vuelve a probar:")}
    require(butaca.isNotEmpty()){"El mensaje no puede estar vacio, vuelve a probar:"}
    val regex = Regex("[A-Z][0-9]+")
    require(butaca == "stop" || butaca.matches(regex)){"EL menaje introducido no es válido, vuelve a probar:"}
    return true
}

/**
 * función que sirve para introducir una fila o columna válida
 * @return la fila o columna introducida por teclado
 */
fun introducirFilaColumna(): Int{
    var filaColumna = 0
    do{
        try{
            filaColumna = readln().toInt()
            filaColumnaValida(filaColumna)
        }catch(e: Exception){
            println(e.message)
            filaColumna = -1
        }
    }while(filaColumna == -1)
    return filaColumna
}

/**
 * función que sirve para validar el año de publicación introducido por teclado
 * @param filaColumna lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun filaColumnaValida(filaColumna: Int?): Boolean {
    require(filaColumna != null){("La fila/columna no puede ser nulo, vuelve a probar:")}
    require(filaColumna > 0){"La fila/columna no puede ser menor que 1, vuelve a probar:"}
    require(filaColumna < 26){"La fila/columna sobrepasar el tamaño 26, vuelve a probar:"}
    return true
}
/**
 * función que sirve para introducir un nombre válido
 * @return el nombre introducido por teclado
 */
fun introducirNombre(): String{
    var nombre = ""
    do{
        try{
            nombre = readln().trim()
            nombreValido(nombre)
        }catch(e: Exception){
            println(e.message)
            nombre = ""
        }
    }while(nombre == "")
    return nombre
}

/**
 * función que sirve para validar el nombre introducido por teclado
 * @param nombre lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun nombreValido(nombre: String?): Boolean {
    require(nombre != null){("El nombre no puede ser nulo, vuelve a probar:")}
    require(nombre.isNotEmpty()){"El nombre no puede estar vacio, vuelve a probar:"}
    return true
}

/**
 * función que sirve para introducir un titulo válido
 * @return el título introducido por teclado
 */
fun introducirTitulo(): String {
    var titulo = ""
    do {
        try {
            titulo = readln().trim()
            tituloValido(titulo)
        } catch (e: Exception) {
            println(e.message)
            titulo = ""
        }
    } while (titulo == "")
    return titulo
}

/**
 * función que sirve para validar el título introducido por teclado
 * @param titulo lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun tituloValido(titulo: String?): Boolean {
    require(titulo != null) { "El título no puede ser nulo, vuelve a probar:" }
    require(titulo.isNotEmpty()) { "El título de la peli no puede estar vacio, vuelve a probar:" }
    return true
}

/**
 * función que sirve para introducir un año de publicacion válido
 * @return el año de publicacion introducido por teclado
 */
fun introducirAñoPublicación(): Int {
    var añoPublicacion = 0
    do {
        try {
            añoPublicacion = readln().toInt()
            añoPublicacionValido(añoPublicacion)
        } catch (e: Exception) {
            println(e.message)
            añoPublicacion = -1
        }
    } while (añoPublicacion == -1)
    return añoPublicacion
}

/**
 * función que sirve para validar el año de publicación introducido por teclado
 * @param añoPublicacion lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun añoPublicacionValido(añoPublicacion: Int?): Boolean {
    require(añoPublicacion != null) { "El año de publicación no puede ser nulo, vuelve a probar:" }
    require(añoPublicacion > 0) { "El año de publicación no puede ser negativo, vuelve a probar:" }
    require(añoPublicacion in 1950..2022) { "El año de publicación de la peli debe ser entre 1950 y 2022, vuelve a probar:" }
    return true
}

/**
 * función que sirve para introducir un director válido
 * @return el director introducido por teclado
 */
fun introducirDirector(): String {
    var director = ""
    do {
        try {
            director = readln().trim()
            directorValido(director)
        } catch (e: Exception) {
            println(e.message)
            director = ""
        }
    } while (director == "")
    return director
}

/**
 * función que sirve para validar el director introducido por teclado
 * @param director lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun directorValido(director: String?): Boolean {
    require(director != null) { "El director no puede ser nulo, vuelve a probar:" }
    require(director.isNotEmpty()) { "El director de la peli no puede estar vacio, vuelve a probar:" }
    return true
}

/**
 * función que sirve para introducir un género válido
 * @return el género introducido por teclado
 */
fun introducirGenero(): String {
    var genero = ""
    do {
        try {
            genero = readln().trim()
            generoValido(genero)
        } catch (e: Exception) {
            println(e.message)
            genero = ""
        }
    } while (genero == "")
    return genero
}

/**
 * función que sirve para validar el género introducido por teclado
 * @param genero lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun generoValido(genero: String?): Boolean {
    require(genero != null) { "El género no puede ser nulo, vuelve a probar:" }
    require(genero.isNotEmpty()) { "El género de la peli no puede estar vacio, vuelve a probar:" }
    return true
}

/**
 * función que sirve para presentar un menu al usuario y conseguir la opción que seleccione
 * @return la opcion seleccionada por el usuario
 */
fun menu(): Int{
    println("[1] Comprar entrada")
    println("[2] Reservar entrada")
    println("[3] Formalizar reserva de entrada")
    println("[4] Anular reserva o compra de entrada")
    println("[5] Conseguir informe de la sala")
    println("[6] Conseguir recaudación total de la caja")
    println("[0] Salir")
    return introducirOpcion()
}

/**
 * función que sirve para introducir una opción válida
 * @return la opcion válida
 */
fun introducirOpcion(): Int {
    var opcion = 0
    do {
        try {
            opcion = readln().toInt()
            opcionValida(opcion)
        } catch (e: Exception) {
            println(e.message)
            opcion = -1
        }
    } while (opcion == -1)
    return opcion
}

/**
 * función que sirve para validar la opción introducida por teclado
 * @param opcion lo que queremos validar
 * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
 * @return true en caso de que sea válido
 */
fun opcionValida(opcion: Int?): Boolean {
    require(opcion != null){"La opción no puede ser nula, vuelve a probar:"}
    require(opcion in 0..6){"No has elegido una de las opciones posibles, vuelve a probar:"}
    return true
}