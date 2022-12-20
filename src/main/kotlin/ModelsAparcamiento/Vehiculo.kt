package ModelsAparcamiento

import TipoDemarcacion

data class Vehiculo(val matricula: String, val añoFabricacion: Int, var tipo: TipoVehiculo, val conductor: Conductor) {

    private companion object{
        var contador = 0
        fun nextid(): Int{
            return contador++
        }
    }

    val id = nextid()

    fun create(matricula: String, añoFabricacion: Int, tipo: String, conductor: Conductor): Vehiculo{
        when(tipo){
            "coche" -> this.tipo = TipoVehiculo.coche
            "moto" -> this.tipo = TipoVehiculo.moto
            "patinete" -> this.tipo = TipoVehiculo.patinete
        }
        return Vehiculo(matricula, añoFabricacion, this.tipo,  conductor)
    }

    fun introducirMatricula(): String {
        var matricula = ""
        do{
            try{
                matricula = readln().trim()
                matriculaValida(matricula)
            }catch(e: Exception){
                println(e.message)
                matricula = ""
            }
        }while(matricula == "")
        return matricula
    }

    private fun matriculaValida(matricula: String): Boolean {
        require(matricula != null){"La matricula introducido no puede ser nula, vuelve a probar:"}
        require(matricula.isNotEmpty()){"La matricula introducido no puede estar vacia, vuelve a probar:"}
        val regex = Regex("[0-9]{4}-[A-Z]{3}")
        require(matricula.matches(regex)){"La matricula introducido no cumple con los patrones esperados ( 0000-AAA ), vuelve a probar:"}
        return true
    }

    fun introducirAñoFabricacion(): Int {
        var añoFabricacion = 0
        do{
            try{
                añoFabricacion = readln().toInt()
                añoFabricacionValido(añoFabricacion)
            }catch(e: Exception){
                println(e.message)
                añoFabricacion = -1
            }
        }while(añoFabricacion == -1)
        return añoFabricacion
    }

    private fun añoFabricacionValido(añoFabricacion: Int): Boolean {
        require(añoFabricacion != null){"El añoFabricacion introducido no puede ser nulo, vuelve a probar:"}
        require(añoFabricacion >= 1950 && añoFabricacion <= 2022){"El año de fabricación no puede ser menor de 1950, ni mayor de 2022, vuelve a probar:"}
        return true
    }

    fun introducirTipo(): String {
        var tipo = ""
        do{
            try{
                tipo = readln().trim().lowercase()
                tipoValido(tipo)
            }catch(e: Exception){
                println(e.message)
                tipo = ""
            }
        }while(tipo == "")
        return tipo
    }

    private fun tipoValido(tipo: String): Boolean {
        require(tipo != null){"La tipo introducido no puede ser nulo, vuelve a probar:"}
        require(tipo.isNotEmpty()){"La tipo introducido no puede estar vacio, vuelve a probar:"}
        require(tipo == "coche" || tipo == "moto" || tipo == "patinete"){"La tipo introducido no es una opción posible, vuelve a probar:"}
        return true
    }
}