package ModelsAparcamiento

data class Conductor(val nombre: String, val dni: String) {

    private companion object{
        var contador = 0
        fun nextid(): Int{
            return contador++
        }
    }

    val id = nextid()

    fun create(nombre: String, dni: String): Conductor{
        return Conductor(nombre, dni)
    }

    fun introducirNombre(): String {
        var nombre = ""
        do{
            try{
                nombre = readln().trim()
                nombreValido(nombre)
            }catch(e: Exception){
                println(e.message)
                nombre = ""
            }
        }while(nombre == "")
        return nombre
    }

    private fun nombreValido(nombre: String): Boolean {
        require(nombre != null){"El nombre introducido no puede ser nulo, vuelve a probar:"}
        require(nombre.isNotEmpty()){"El nombre introducido no puede estar vacio, vuelve a probar:"}
        val regex = Regex("[a-zA-ZáúíóéÁÉÚÍÓ]+")
        require(nombre.matches(regex)){"El nombre introducido solo puede tener letras, vuelve a probar:"}
        return true
    }

    fun introducirDni(): String {
        var dni = ""
        do{
            try{
                dni = readln().trim()
                dniValido(dni)
            }catch(e: Exception){
                println(e.message)
                dni = ""
            }
        }while(dni == "")
        return dni
    }

    private fun dniValido(dni: String): Boolean {
        require(dni != null){"El dni introducido no puede ser nulo, vuelve a probar:"}
        require(dni.isNotEmpty()){"El dni introducido no puede estar vacio, vuelve a probar:"}
        val regex = Regex("[0-9]{8}[A-Z]")
        require(dni.matches(regex)){"El dni introducido no cumple con el patrón necesario (8 números y 1 letra mayúscula ), vuelve a probar:"}
        return true
    }
}