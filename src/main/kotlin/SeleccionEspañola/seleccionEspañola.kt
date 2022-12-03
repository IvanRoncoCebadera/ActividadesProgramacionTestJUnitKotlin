package SeleccionEspañola

import ModelsSeleccionEspañola.Entrenador
import ModelsSeleccionEspañola.Jugador
import ModelsSeleccionEspañola.Seleccion
import TipoDemarcacion

fun main(){

    var seleccion: Seleccion = Seleccion("España", Entrenador("Iván"))

    seleccion.jugadores[0] = Jugador(1,"Marianito",TipoDemarcacion.DEFENSA)
    seleccion.jugadores[1] = Jugador(5,"Marianito",TipoDemarcacion.DEFENSA)
    seleccion.jugadores[2] = Jugador(3,"Marianito",TipoDemarcacion.PORTERO)
    seleccion.jugadores[3] = Jugador(12,"Marianito",TipoDemarcacion.CENTROCAMPISTA)
    seleccion.jugadores[4] = Jugador(7,"Marianito",TipoDemarcacion.DELANTERO)

    var opcion = 0
    do {
        println("Seleccione el proceso que desea realizar:")
        opcion = seleccion.menuJugadores()
        when (opcion) {
            1 -> seleccion.mostrarTodos(seleccion.jugadores)
            2 -> seleccion.mostrarJugadorPorDorsal(seleccion.jugadores)
            3 -> seleccion.jugadores = seleccion.añadirJugador(seleccion.jugadores)
            4 -> {
                println("Introduzca el dorsal del jugador que desea eliminar:")
                seleccion.jugadores = seleccion.eliminarJugador(seleccion.jugadores, seleccion.introducirDorsal())
            }
            5 -> seleccion.actualizarInfoJugador(seleccion)
            6 -> seleccion.informacion(seleccion.jugadores)
        }
    }while(opcion != 0)
    println("Adios")
}

