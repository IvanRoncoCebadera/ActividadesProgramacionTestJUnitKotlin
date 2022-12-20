package ModelsSalaDeCine

fun main(){

    println("Primero introduce los datos de la sal del cine:")
    println("Introduce el nombre que tiene su cine:")
    val nombre = salaDeCine.introducirNombre()
    println("Introduce el número de fila que tiene su cine:")
    val filas = salaDeCine.introducirFilaColumna()
    println("Introduce el número de columnas que tiene su cine:")
    val columnas = salaDeCine.introducirFilaColumna()

    println("Ahora introduce los datos de la pelicula que se va a mostrar:")
    println("Introduce el titulo de la pelicula:")
    val titulo = pelicula.introducirTitulo()
    println("Introduce el año de publicación de la pelicula:")
    val añoPublicacion =  pelicula.introducirAñoPublicación()
    println("Introduce el director de la pelicula:")
    val director = pelicula.introducirDirector()
    println("Introduce el genero de la pelicula:")
    val genero = pelicula.introducirGenero()

    var salaDeCine = salaDeCine(nombre, filas, columnas, pelicula.create(titulo, añoPublicacion, director, genero))

    println("La sala del cine es la siguiente:")
    salaDeCine.representacionInicialDeLaSala()

    var opcion = 0
    do{
        println("Seleccione la opción que desea:")
        opcion = salaDeCine.menu()
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