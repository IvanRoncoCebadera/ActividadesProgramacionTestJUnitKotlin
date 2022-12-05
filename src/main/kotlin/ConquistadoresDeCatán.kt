import ModelsConquistadoresDeCatán.Campo

fun main(){
    var campo: Campo? = Campo(7748259)
    do{
        println("Por ahora el campo se ve así:")
        campo!!.representarMatriz(campo!!.matrizPosicion, campo!!.matrizMateriales)
        campo!!.jugador!!.introducirPosicion(campo!!.matrizPosicion)
        if(campo!!.quedanHuecosLibres(campo!!.matrizPosicion)){
            campo!!.ordenador!!.eleccionAleatoriaDePosicion(campo!!.matrizPosicion)
        }
    }while(campo!!.quedanHuecosLibres(campo!!.matrizPosicion))
    println("Empezamos con la simulación, con el siquiente campo:")
    campo!!.representarMatriz(campo!!.matrizPosicion, campo!!.matrizMateriales)
    println()
    Thread.sleep(3_000L)
    campo!!.simulacion(campo!!.matrizPosicion, campo!!.matrizMateriales)
}