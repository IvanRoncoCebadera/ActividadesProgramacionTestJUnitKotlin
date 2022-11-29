package Models

class AlumnosMax30 (var dni: String = "",
                    var nombre: String,
                    var calificacion: Double) {

    override fun toString(): String {
        return "Alumnos(id=$dni, nombre='$nombre', calificacion=$calificacionRedondeada)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AlumnosMax30

        if (dni != other.dni) return false
        if (nombre != other.nombre) return false
        if (calificacion != other.calificacion) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dni.hashCode()
        result = 31 * result + nombre.hashCode()
        result = 31 * result + calificacion.hashCode()
        return result
    }

    val estaAprobado: Boolean get() = (this.calificacion >= 5)
    val calificacionRedondeada: Double get() = (calificacion*100).toInt()/100.0


}