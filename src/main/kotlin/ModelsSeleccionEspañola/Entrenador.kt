package ModelsSeleccionEspañola

data class Entrenador(val nombre: String?) {

    companion object{
        private var contador = 0
        private fun nextId(): Int{
            return contador++
        }
        fun resetContadorIdEntrenador(){
            contador = 0
        }
    }

    val idEntrenador: Int = nextId()//ponerle el "get()" no es apropiado porque lo que hace es rescribirlo cuando es leido, eso quiere significar añadir el "get()"

    override fun toString(): String {
        return "Entrenador(nombre='$nombre')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Entrenador

        if (nombre != other.nombre) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nombre.hashCode()
        return result
    }
}