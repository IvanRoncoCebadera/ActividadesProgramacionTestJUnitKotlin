
const val TAMAÑOFC = 10
const val FALSE = 0
const val TRUE = 1
const val TIEMPO_MAX = 30

fun main(){

    var matrizCalculos: Array<BooleanArray> = Array(TAMAÑOFC){BooleanArray(TAMAÑOFC)}
    matrizCalculos[0][1] = true
    matrizCalculos[1][2] = true
    matrizCalculos[2][2] = true
    matrizCalculos[2][0] = true
    matrizCalculos[2][1] = true
    matrizCalculos[0][0] = true
    matrizCalculos[1][1] = true

    var matrizRepresentacion: Array<IntArray> = Array(TAMAÑOFC){IntArray(TAMAÑOFC)}
    matrizRepresentacion[0][1] = 1
    matrizRepresentacion[1][2] = 1
    matrizRepresentacion[2][2] = 1
    matrizRepresentacion[2][0] = 1
    matrizRepresentacion[2][1] = 1
    matrizRepresentacion[0][0] = 1
    matrizRepresentacion[1][1] = 1

    println("La matriz empieza así:")
    leerContenidoMatriz(matrizRepresentacion)

    var opcion = 0
    do{
        println("Seleccione la opción que desea:")
        opcion = menuJuegoVida()
        when(opcion){
            1 -> {
                matrizCalculos = introducirNuevaCelulaViva(matrizCalculos)
                matrizRepresentacion = actualizarMatrizRepresentacion(matrizRepresentacion, matrizCalculos)
                println("Por ahora, la matriz se ve así:")
                leerContenidoMatriz(matrizRepresentacion)
                println()
            }
            2 -> iniciarSimulacion(matrizCalculos, matrizRepresentacion)
        }
    }while(opcion != 0)
    println("Cerrando programa....")
    Thread.sleep(1_134L)
    println("Adios")
}

/**
 * funcion que produce la simulacion del juego de la vida
 * @param matrizRepresentacion es la matriz de representación con la que mostraré si las celulas de cada casilla de la matriz están vivas o muertas
 * @param matrizCalculos es la matriz de booleanos con la que sabremos si las celulas de cada casilla de la matriz están vivas o muertas
 */
fun iniciarSimulacion(matrizCalculos: Array<BooleanArray>, matrizRepresentacion: Array<IntArray>) {
    var matrizAuxiliar1 = matrizCalculos
    var matrizAuxiliar2 = matrizRepresentacion
    println("Iniciamos la simulación del juego de la vida:")
    var contadorTiempo = 0
    do {
        matrizAuxiliar1 = actualizarMatrizCalculos(matrizAuxiliar1)

        matrizAuxiliar2 = actualizarMatrizRepresentacion(matrizAuxiliar2, matrizAuxiliar1)

        leerContenidoMatriz(matrizAuxiliar2)
        println()

        contadorTiempo = contadorTiempo + pasar1Segundo()
    }while(contadorTiempo < TIEMPO_MAX)
}

/**
 * función que emula que pasa un segundo en la simulación
 * @return 1 ya que es el tiempo que emula pasar entre representación y representación de la matriz con las celulas
 */
fun pasar1Segundo(): Int {
    Thread.sleep(1_000)
    return 1
}

/**
 * funcionq que sirve para actualizar la matriz de booleanos con las celulas en estado vivas o muertas
 * @param matrizCalculos es la matriz de booleanos con la que sabremos si las celulas de cada casilla de la matriz están vivas o muertas
 * @return la matriz auxilar sobre la que hemos hecho todos los cambios con la técnica del doble buffer
 */
fun actualizarMatrizCalculos(matrizCalculos: Array<BooleanArray>): Array<BooleanArray> {
    var matrizAuxiliar = Array(TAMAÑOFC){BooleanArray(TAMAÑOFC)}
    for(i in matrizCalculos.indices){
        for(j in matrizCalculos[i].indices){
            matrizAuxiliar[i][j] = estaVivaLaCelula(matrizCalculos, i, j)
        }
    }
    return matrizAuxiliar
}

/**
 * funcion que sirve para calcular si una celula esta viva o no
 * @param matrizCalculos es la matriz de booleanos con la que sabremos si las celulas de cada casilla de la matriz están vivas o muertas
 * @param fila es valor de posicion de la fila a comprobar
 * @param columna es valor de posicion de la columna a comprobar
 * @return true si la celula está viva y false si está muerta
 */
fun estaVivaLaCelula(matrizCalculos: Array<BooleanArray>, fila: Int, columna: Int): Boolean {
    var contadorCelulasVivas = calcularNumeroDeCelulasVivasAdyacentes(fila, columna, matrizCalculos)
    return if(matrizCalculos[fila][columna] == true) {
        if (contadorCelulasVivas in 2..3) {
            true
        }else{
            false
        }
    }else{
        if (contadorCelulasVivas == 3){
            true
        }else{
            false
        }
    }
}

/**
 * funcion que sirve para calcular si el numero de celulas vivas adyacentes a una coordenada
 * @param matrizCalculos es la matriz de booleanos con la que sabremos si las celulas de cada casilla de la matriz están vivas o muertas
 * @param fila es valor de posicion de la fila a comprobar
 * @param columna es valor de posicion de la columna a comprobar
 * @return el numero de celulas vivas
 */
fun calcularNumeroDeCelulasVivasAdyacentes(
    fila: Int,
    columna: Int,
    matrizCalculos: Array<BooleanArray>
): Int {
    var contadorCelulasVivas = 0
    if(matrizCalculos[fila][columna] == true) {
        contadorCelulasVivas = -1
        for (i in -1..1) {
            for (j in -1..1) {
                if (fila + i < TAMAÑOFC && fila + i >= 0 && columna + j < TAMAÑOFC && columna + j >= 0) {
                    if (matrizCalculos[fila + i][columna + j] == true) {
                        contadorCelulasVivas++
                    }
                }
            }
        }
    }else{
        for (i in -1..1) {
            for (j in -1..1) {
                if (fila + i < TAMAÑOFC && fila + i >= 0 && columna + j < TAMAÑOFC && columna + j >= 0) {
                    if (matrizCalculos[fila + i][columna + j] == true) {
                        contadorCelulasVivas++
                    }
                }
            }
        }
    }
    return contadorCelulasVivas
}

/**
 * función que actualiza el contenido de la matriz de representación en base a los cambios sobre la matriz de booleanos
 * @param matrizRepresentacion es la matriz de representación con la que mostraré si las celulas de cada casilla de la matriz están vivas o muertas
 * @param matrizCalculos es la matriz de booleanos con la que sabremos si las celulas de cada casilla de la matriz están vivas o muertas
 * @return una matriz auxiliar con los cambios realizados sobre la matriz de representación, utilizando la técnica del doble buffer
 */
fun actualizarMatrizRepresentacion(matrizRepresentacion: Array<IntArray>, matrizCalculos: Array<BooleanArray>): Array<IntArray> {
    var matrizAuxiliar = matrizRepresentacion
    for(i in matrizCalculos.indices){
        for(j in matrizCalculos[i].indices){
            if(matrizCalculos[i][j] == true){
                matrizAuxiliar[i][j] = TRUE
            }else{
                matrizAuxiliar[i][j] = FALSE
            }
        }
    }
    return matrizAuxiliar
}

/**
 * función que sirve para crear celulas vivas (representadas como true) en la matriz pasada por parametro
 * @param matrizCalculos es la matriz de booleanos con la que sabremos si las celulas de cada casilla de la matriz están vivas o muertas
 * @return una matriz auxiliar con los cambios realizados, utilizando la técnica del doble buffer
 */
fun introducirNuevaCelulaViva(matrizCalculos: Array<BooleanArray>): Array<BooleanArray> {
    var matrizAuxiliar = matrizCalculos
    println("Por favor, introduzca las coordenadas de la nueva celula viva, debes seguir el patrón: f-c, para introducir las coordenadas, los valores máximos de filas y columnas posibles son: $TAMAÑOFC")
    var coordenadas: Array<String> = introducirCoordenas(matrizCalculos).split("-").toTypedArray()
    val fila: Int = coordenadas[0].toInt() - 1
    val columna: Int = coordenadas[1].toInt() - 1
    matrizAuxiliar[fila][columna] = true
    return matrizAuxiliar
}

/**
 * función que se encarga de leer por teclado las coordenadas y de devolverlas tras comprobar que son válidas
 * @param matrizCalculos es la matriz de booleanos con la que comprobamos si en las coordenadas introducidas ya hay una celula viva o no
 * @return el string con las coordenadas introducidas
 */
fun introducirCoordenas(matrizCalculos: Array<BooleanArray>): String {
    var coordenadas = ""
    do{
        coordenadas = readln()
        try{
            coordenadasValidas(matrizCalculos, coordenadas)
        }catch(e: Exception){
            println(e.message)
            coordenadas = ""
        }
    }while(coordenadas == "")
    return coordenadas
}

/**
 * función que sirve para comprobar si las coordenadas ingresadas son válidas o no
 * @param matrizCalculos es la matriz de booleanos con la que comprobamos si en las coordenadas introducidas ya hay una celula viva o no
 * @param coordenadas es el string de coordenadas del que queremos comprobar su validez
 * @return true en caso de que sea válida, una excepción con un mensaje personalizado en caso de que no sea válida
 */
fun coordenadasValidas(matrizCalculos: Array<BooleanArray>, coordenadas: String): Boolean {
    val regex = Regex("[0-9]+-[0-9]+")
    require(coordenadas.matches(regex)){"No has introducido coordenadas que cumplán con el patrón pedido, vuelve a probar:"}
    var coordenadas: Array<String> = coordenadas.split("-").toTypedArray()
    val fila: Int = coordenadas[0].toInt() - 1
    val columna: Int = coordenadas[1].toInt() - 1
    require(fila in 0..TAMAÑOFC && columna in 0..TAMAÑOFC){"No has introducido valores de fila o de columnas posibles, vuelve a probar:"}
    require(matrizCalculos[fila][columna] != true){"En esas coordenadas ya se encuentra una celula viva, vuelva a probar:"}
    return true
}

/**
 * función que muestra un menu de opciones y pide al usuario la acción ha realizar acorde a su elección
 * @return la opción elegida por el usuario
 */
fun menuJuegoVida(): Int{
    var opcion = 0
    do {
        println("[1] Introducir nueva celula viva")
        println("[2] Iniciar simulación")
        println("[0] Salir")
        try{
            opcion = readln().toInt()
            opcionValidaJuegoVida(opcion)
        }catch(e: Exception){
            if(e is NumberFormatException){
                println("No has introducido un número, vuelve a probar:")
            }else {
                println(e.message)
            }
            opcion = -1
        }
    }while(opcion == -1)
    return opcion
}

/**
 * función que sirve para comprobar si la opción elegida existe y/o si es válida
 * @param opcion es la opción introducida, de la que queremos comprobar su validez
 * @return true en caso de que sea válida, una excepción con un mensaje personalizado en caso de que no sea válida
 */
fun opcionValidaJuegoVida(opcion: Int): Boolean {
    require(opcion in 0..2){"La opción introducida no existe, vuelva a probar"}
    return true
}

private fun leerContenidoMatriz(matrizCalculos: Array<IntArray>) {
    var mensaje = ""
    for (i in matrizCalculos.indices) {
        for (j in matrizCalculos[i].indices) {
            mensaje = "$mensaje ${matrizCalculos[i][j]}"
        }
        println(mensaje)
        mensaje = ""
    }
}