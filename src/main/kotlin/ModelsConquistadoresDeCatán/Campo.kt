package ModelsConquistadoresDeCatán

const val TRIGO = 0
const val MADERA = 1
const val CARBON = 2
const val JUGADOR = 9
const val ORDENADOR = -9

data class Campo(val identificador: Int) {

    var jugador: Jugador? = Jugador("Iván")

    var ordenador: Ordenador? = Ordenador("Gerardo")

    var matrizPosicion: Array<IntArray> = Array(3){IntArray(4)}

    var matrizMateriales: Array<Array<String>> = Array(3){Array<String>(4){""} }

    //Inicializamos la matrizMateriales con el metodo init
    init {
        for(i in matrizMateriales.indices){
            for(j in matrizMateriales[i].indices){
                matrizMateriales[i][j] = "${(0..2).random()}-${(1..6).random()}"
            }
        }
    }

    /**
     * función que lleva a cabo la simulación del juego de COnquistadores de Catán, entre solo dos jugadores y en una matriz 3x4
     * @param matrizPosicion la matriz donde comprobamos si las coordenadas elegidas tiene propietario o no y, en caso de que tengan quien es
     * @param matrizMateriales la matriz donde hemos guardado que materiales y valores hay en cada casilla del campo
     */
    fun simulacion(matrizPosicion: Array<IntArray>, matrizMateriales: Array<Array<String>>){
        do{
            var dado = (1..6).random()
            resultadosTirada(dado, matrizPosicion, matrizMateriales)
            println("""El valor ha sido $dado, y los resultados por ahora son:" 
                    ·Jugador:
                    -Trigo: ${jugador!!.almacen[TRIGO]}
                    -Madera: ${jugador!!.almacen[MADERA]}
                    -Carbón: ${jugador!!.almacen[CARBON]}
                    ·Ordenador:
                    -Trigo: ${ordenador!!.almacen[TRIGO]}
                    -Madera: ${ordenador!!.almacen[MADERA]}
                    -Carbón: ${ordenador!!.almacen[CARBON]}
                    """)
            Thread.sleep(2_000L)
        }while(comprobarAlmacen(jugador!!.almacen) == false && comprobarAlmacen(ordenador!!.almacen) == false)
        generarMensajeGanador(comprobarAlmacen(jugador!!.almacen), comprobarAlmacen(ordenador!!.almacen))
    }

    /**
     * función que aumenta la cantidad de materiales de los jugadores segun el valor de dado que haya salido
     * @param dado un valor generado aleatoriamente entre 1 y 6
     * @param matrizPosicion la matriz donde comprobamos quien es el propietario de cada casilla
     * @param matrizMateriales la matriz donde hemos guardado que materiales y valores que hay en cada casilla del campo
     */
    fun resultadosTirada(dado: Int, matrizPosicion: Array<IntArray>, matrizMateriales: Array<Array<String>>) {
        for(i in matrizMateriales.indices){
            for(j in matrizMateriales[i].indices){
                var material = matrizMateriales[i][j].split("-").toTypedArray()[0].toInt()
                var valor = matrizMateriales[i][j].split("-").toTypedArray()[1].toInt()
                var propietario = leerPropietario(matrizPosicion, i, j)
                if(valor == dado){
                    if(propietario == "jugador"){
                        jugador!!.almacen[material]++
                    }else{
                        ordenador!!.almacen[material]++
                    }
                }
            }
        }
    }

    /**
     * función que genera mensajes de victoria o empate dependiendo de quien o quienes hayan ganado
     * @param victoriaJugador es un booleano que indica si el jugador ganó o no
     * @param victoriaOrdenador es un booleano que indica si el ordenador ganó o no
     */
    fun generarMensajeGanador(victoriaJugador: Boolean, victoriaOrdenador: Boolean) {
        if(victoriaJugador == true && victoriaOrdenador == true){
            println("""Han empatado:" 
                    ·Jugador:
                    -Trigo: ${jugador!!.almacen[TRIGO]}
                    -Madera: ${jugador!!.almacen[MADERA]}
                    -Carbón: ${jugador!!.almacen[CARBON]}
                    ·Ordenador:
                    -Trigo: ${ordenador!!.almacen[TRIGO]}
                    -Madera: ${ordenador!!.almacen[MADERA]}
                    -Carbón: ${ordenador!!.almacen[CARBON]}
                    """)
        }else{
            if(victoriaJugador == true){
                println("""El jugador es quien ha conseguido la victoria es, el jugador:
                      -Trigo: ${jugador!!.almacen[TRIGO]}
                      -Madera: ${jugador!!.almacen[MADERA]}
                      -Carbón: ${jugador!!.almacen[CARBON]}
                        """)
            }else{
                println("""El jugador es quien ha conseguido la victoria es, el ordenador:
                      -Trigo: ${ordenador!!.almacen[TRIGO]}
                      -Madera: ${ordenador!!.almacen[MADERA]}
                      -Carbón: ${ordenador!!.almacen[CARBON]}
                        """)
            }
        }
    }

    /**
     * función que comprueba la cantidad de material de cada almacen(el del jugador y el del ordenador)
     * @param almacen es el vector almacen del jugador u ordenador, sobre el se comprobará si alguno a ganado o no
     * @return true en caso de que alguién gane, false si es que no han llegado a 20 materiales en todos las posiciones del almacen
     */
    fun comprobarAlmacen(almacen: IntArray): Boolean {
        var contador = 0
        for(i in almacen){
            if(i >= 20){
                contador++
            }
        }
        return if(contador ==3){
            true
        }else{
            false
        }
    }

    /**
     * función que sirve para comprobar si aun quedán espacios donde aun no se han asignado propietarios
     * @param matrizPosicion la matriz donde comprobamos si las coordenadas elegidas tiene propietario o no
     * @return true en caso de que queden casillas sin propietarios, false en caso contrario
     */
    fun quedanHuecosLibres(matrizPosicion: Array<IntArray>): Boolean{
        var respuesta = false
        for(i in matrizPosicion.indices){
            for(j in matrizPosicion[i].indices){
                if(matrizPosicion[i][j] == 0){
                    respuesta = true
                    break
                }
            }
            if(respuesta == true){
                break
            }
        }
        return respuesta
    }

    /**
     * función que sirve para hacer una representación de los contenidos y el propietario de cada casilla según la info que se encuentra en cada una de las matrices
     * @param matrizPosicion la matriz donde comprobamos si las coordenadas elegidas tiene propietario o no y, en caso de que tengan quien es
     * @param matrizMateriales la matriz donde hemos guardado que materiales y valores que hay en cada casilla del campo
     */
    fun representarMatriz(matrizPosicion: Array<IntArray>, matrizMateriales: Array<Array<String>>){
        var mensaje = "|| "
        println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
        for(i in matrizMateriales.indices){
            for(j in matrizMateriales[i].indices){
                var material = leerMaterial(matrizMateriales, i, j)
                var valor = matrizMateriales[i][j].split("-").toTypedArray()[1]
                var propietario = leerPropietario(matrizPosicion, i, j)
                mensaje = "$mensaje Material: $material, valor: $valor y propietario: $propietario || "
            }
            println(mensaje)
            println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
            mensaje = "|| "
        }
    }

    /**
     * funcion que devuelve el propietario segun la casilla en la que nos encontremos
     * @param matrizPosicion es la matriz que contiene la info del propietario en cada casilla
     * @param fila valor de la fila
     * @param columna valor de la columna
     * @return el propietario que hay en la casilla
     */
    fun leerPropietario(
        matrizPosicion: Array<IntArray>,
        fila: Int,
        columna: Int,
    ): String {
        var propietario = ""
        when (matrizPosicion[fila][columna]) {
            JUGADOR -> propietario = "jugador"
            ORDENADOR -> propietario = "ordenador"
            0 -> propietario = "nadie"
        }
        return propietario
    }

    /**
     * funcion que devuelve el material segun la casilla en la que nos encontremos
     * @param matrizMateriales es la matriz que contiene la info del material a encontrar en cada casilla
     * @param fila valor de la fila
     * @param columna valor de la columna
     * @return el material que hay en la casilla
     */
    fun leerMaterial(
        matrizMateriales: Array<Array<String>>,
        fila: Int,
        columna: Int,
    ): String {
        var material = ""
        when (matrizMateriales[fila][columna].split("-").toTypedArray()[0].toInt()) {
            TRIGO -> material = "trigo"
            MADERA -> material = "madera"
            CARBON -> material = "carbón"
        }
        return material
    }

}