package ModelsSeleccionEspañola

import TipoDemarcacion

data class Jugador(
    val dorsal: Int?,
    val nombre: String,
    val demarcacion: TipoDemarcacion)
{
    //Así es como podemos crear un autogenerador de id que vaya sumando 1 al crear un nuevo objeto
    companion object{
        private var contador = 0
        private fun nextId(): Int{
            return contador++
        }
        fun resetContadorIdJugador(){
            contador = 0
        }
        private val nombres: Array<String> = arrayOf("Iván","Gustavo","Jesus","Manuel","Illojuan","Pablo")
        fun random(): Jugador?{
            return Jugador((1..40).random(),nombres[(0..3).random()], TipoDemarcacion.values().random())
        }
        fun create(dorsal: Int, nombre: String, demarcacion: TipoDemarcacion) = Jugador(dorsal, nombre, demarcacion)
    }

    val idJugador: Int = nextId()//ponerle el "get()" no es apropiado porque lo que hace es rescribirlo cuando es leido, eso quiere significar añadir el "get()"

    override fun toString(): String {
        return "Jugador(id='$idJugador', dorsal='$dorsal', nombre='$nombre', demarcacion='$demarcacion')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Jugador

        if (dorsal != other.dorsal) return false
        if (nombre != other.nombre) return false
        if (demarcacion != other.demarcacion) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dorsal.hashCode()
        result = 31 * result + nombre.hashCode()
        result = 31 * result + demarcacion.hashCode()
        return result
    }
}