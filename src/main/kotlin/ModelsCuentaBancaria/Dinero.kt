package ModelsCuentaBancaria

data class Dinero(var cantidad: Double) {
    val cantidadRedondeada: Double get() = ((cantidad * 100).toInt())/100.0
}