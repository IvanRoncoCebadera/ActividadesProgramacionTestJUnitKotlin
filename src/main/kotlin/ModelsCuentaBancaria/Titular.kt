package ModelsCuentaBancaria

data class Titular(val dni: String, val nombre: String, val email: String) {

    companion object{
        private var contador = 0
        private fun nextId(): Int{
            return contador++
        }
    }

    val idTitular: Int = nextId()
}