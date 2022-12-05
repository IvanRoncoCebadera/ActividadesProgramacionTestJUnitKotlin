package ModelsConquistadoresDeCatán

data class Ordenador(val nombre: String) {

    val almacen: IntArray = IntArray(3)

    /**
     * función que sirve para que el ordenador eliga al azar las coordenadas de la casilla que quiere adquirir
     * @param matrizPosicion la matriz donde comprobamos si las coordenadas elegidas ya están cogidas o no
     * @return la matrizPosicion modificada tras la adición por parte del ordenador
     */
    fun eleccionAleatoriaDePosicion(matrizPosicion: Array<IntArray>): Array<IntArray>{
        var matrizAuxiliar = matrizPosicion
        val coordenadas: Array<String> = generarCoordenadasAleatorias(matrizPosicion).split("-").toTypedArray()
        val fila: Int = coordenadas[0].toInt()
        val columna: Int = coordenadas[1].toInt()
        matrizAuxiliar[fila][columna] = -9
        return matrizAuxiliar
    }

    /**
     * función que genera un mensaje con las coordenadas válidas de una casilla sobre la que el ordenador se volvera propietario
     * @param matrizPosicion es la matriz de enteros con la que comprobamos si en las coordenadas introducidas ya hay un propietario o no
     * @return el string con las coordenadas introducidas
     */
    fun generarCoordenadasAleatorias(matrizPosicion: Array<IntArray>): String {
        var coordenadas: String = ""
        do{
            var fila = (0..2).random()
            var columna = (0..3).random()
            coordenadas = "$fila-$columna"
            if(matrizPosicion[fila][columna] == 9 || matrizPosicion[fila][columna] == -9){
                coordenadas = ""
            }
        }while(coordenadas == "")
        return coordenadas
    }
}