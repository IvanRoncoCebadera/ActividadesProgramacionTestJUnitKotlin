package ModelsConquistadoresDeCatán

data class Jugador(val nombre: String) {

    var almacen: IntArray = IntArray(3)

    /**
     * función que sirve para que el jugador introduzca las coordenadas de la casilla que quiere adquirir
     * @param matrizPosicion la matriz donde comprobamos si las coordenadas elegidas ya están cogidas o no
     * @return la matrizPosicion modificada tras la adición por parte del jugador
     */
    fun introducirPosicion(matrizPosicion: Array<IntArray>): Array<IntArray>{
        var matrizAuxiliar = matrizPosicion
        println("Por favor, introduzca la posición sobre la que deseas ser propietario, debes respetar el patrón: f-c, y el tamaño máx de fila es 3, y el de columna es 4:")
        val coordenadas: Array<String> = introducirCoordenas(matrizPosicion).split("-").toTypedArray()
        val fila: Int = coordenadas[0].toInt() - 1
        val columna: Int = coordenadas[1].toInt() - 1
        matrizAuxiliar[fila][columna] = 9
        return matrizAuxiliar
    }

    /**
     * función que se encarga de leer por teclado las coordenadas y de devolverlas tras comprobar que son válidas
     * @param matrizCalculos es la matriz de enteros con la que comprobamos si en las coordenadas introducidas ya hay un propietario o no
     * @return el string con las coordenadas introducidas
     */
    fun introducirCoordenas(matrizCalculos: Array<IntArray>): String {
        var coordenadas = ""
        do{
            coordenadas = readln().trim()
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
     * @param matrizCalculos es la matriz de enteros con la que comprobamos si en las coordenadas introducidas ya hay un propietario o no
     * @param coordenadas es el string de coordenadas del que queremos comprobar su validez
     * @return true en caso de que sea válida, una excepción con un mensaje personalizado en caso de que no sea válida
     */
    fun coordenadasValidas(matrizCalculos: Array<IntArray>, coordenadas: String): Boolean {
        val regex = Regex("[1-3]+-[1-4]+")
        require(coordenadas.matches(regex)){"No has introducido coordenadas que cumplán con el patrón pedido, vuelve a probar:"}
        var coordenadas: Array<String> = coordenadas.split("-").toTypedArray()
        val fila: Int = coordenadas[0].toInt() - 1
        val columna: Int = coordenadas[1].toInt() - 1
        require(fila in 0..2 && columna in 0..3){"No has introducido valores de fila o de columnas posibles, vuelve a probar:"}
        require(matrizCalculos[fila][columna] != -9){"En esas coordenadas ya se encuentra como propietario el ordenadro, vuelva a probar:"}
        require(matrizCalculos[fila][columna] != 9){"En esas coordenadas ya estás tú de propietario, vuelva a probar con una nueva casilla:"}
        return true
    }
}