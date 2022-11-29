const val LONGITUD = 4

fun main(){

    var matriz: Array<IntArray> = Array(LONGITUD){IntArray(LONGITUD)}
    generarMatrizAleatoria(matriz)
    representacionMatriz(matriz)

    var array: IntArray = generarArrayDeMatriz(matriz)
    println(contentToStrings(array))

    array = ordenarBurbujaAscendente(array)
    println("Ordenado ascendentemente con el método burbuja hace que quede así:")
    println(contentToStrings(array))

    array = ordenarBurbujaDescendente(array)
    println("Ordenado descendentemente con el método burbuja hace que quede así:")
    println(contentToStrings(array))

    var array2 = reverse(array)
    println("El orden anterior reverseado se ve así:")
    println(contentToStrings(array2))

    println(buscarElemento(array, 15))

    println(palindromo(array, array2))
}

fun palindromo(array: IntArray, array2: IntArray): Boolean{
    return if(array == array2){
        true
    }else{
        false
    }
}

fun buscarElemento(array: IntArray, i: Int): String {
    for(j in array.indices){
        if(array[j] == i){
            return "El elemento que buscabas si existe, y se encuentra en la posicion ${j + 1}"
        }
    }
    return "El elemento que buscabas no existe, FFFFFFFFFFFFFFFF"
}


fun reverse(array: IntArray): IntArray{
    var vector = array
    var cont = 0
    for(i in array.size - 1 downTo 0){
        vector[cont] = array[i]
        cont++
    }
    return vector
}

fun ordenarBurbujaDescendente(array1: IntArray): IntArray {
    var array = array1
    for(i in array.indices){
        for(j in (0 until array.size - 1)){
            if(array[j] < array[j + 1]){
                var auxiliar = array[j]
                array[j] = array[j + 1]
                array[j + 1] = auxiliar
            }
        }
    }
    return array
}

fun ordenarBurbujaAscendente(array1: IntArray): IntArray {
    var array = array1
    for(i in array.indices){
        for(j in (0 until array.size - 1)){
            if(array[j] > array[j + 1]){
                var auxiliar = array[j]
                array[j] = array[j + 1]
                array[j + 1] = auxiliar
            }
        }
    }
    return array
}

fun contentToStrings(array: IntArray): String {
    var mensaje = StringBuilder()
    for(i in array.indices){
        when(i){
            0 -> mensaje.append("[${array[i]}, ")
            array.size - 1 -> mensaje.append("${array[i]}]")
            else -> mensaje.append("${array[i]}, ")
        }
    }
    return mensaje.toString()
}

fun generarArrayDeMatriz(matriz: Array<IntArray>): IntArray {
    var array: IntArray = IntArray(LONGITUD*LONGITUD)
    var cont = 0
    for(i in matriz.indices){
        for(j in matriz[i].indices){
            array[cont] = matriz[i][j]
            cont++
        }
    }
    return array
}

private fun generarMatrizAleatoria(matriz: Array<IntArray>): Array<IntArray>{
    for(i in matriz.indices){
        for(j in matriz[i].indices){
            matriz[i][j] = (1..25).random()
        }
    }
    return matriz
}

fun representacionMatriz(matriz: Array<IntArray>){
    var mensaje = ""
    for(i in matriz.indices){
        for(j in matriz[i].indices){
            mensaje = "$mensaje ${matriz[i][j]}"
        }
        println("$mensaje")
        mensaje = ""
    }
}