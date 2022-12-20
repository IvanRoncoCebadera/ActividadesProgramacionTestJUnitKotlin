package RehechoModelsCuentaBancaria

import ModelsCuentaBancaria.Dinero
import ModelsCuentaBancaria.Titular

data class RehechoCuenta(val id_cuenta: String, var saldo: Double) {

    var titulares: Array<RehechoTitular?> = Array<RehechoTitular?>(3){null}

    init {
        titulares[0] = RehechoTitular("67785367L", "Iván", "674529574")
    }

    fun menu(): Int{
        println("[1] Mostar todos los titulares")
        println("[2] Mostar titular según dni")
        println("[3] Añadir un nuevo titular")
        println("[4] Borrar un titular según dni")
        println("[5] Actualizar un titular según dni")
        println("[6] Inforación de la cuenta")
        println("[7] Introducir dinero en la cuenta")
        println("[8] Sacar dinero de la cuenta")
        println("[0] Salir")
        var opcion = 0
        do{
            try{
                opcion = readln().toInt()
                opcionValida(opcion)
            }catch(e: Exception){
                println(e.message)
                opcion = -1
            }
        }while(opcion == -1)
        return opcion
    }

    fun opcionValida(opcion: Int): Boolean {
        require(opcion in (0..8)){"La opción introducida no es una de las opciones posibles, vuelva a probar:"}
        return true
    }

    fun mostrarTodosLosTitulares(){
        println("Los titulares de la cuenta son:")
        for(i in titulares){
            if(i != null){
                println(i)
            }
        }
        println()
    }

    fun mostrarTitularSegunDni(){
        println("Introduzca el dni del titular cuya información desea comprobar:")
        val dni = introducirDni()
        val posicion = buscarTitularPorDni(dni)
        if(posicion == -1){
            println("No hay ningún titular con ese dni")
        }else{
            println("Se ha encontrado al titular:")
            mostrarTitularPorDni(posicion)
        }
        println()
    }

    fun mostrarTitularPorDni(posicion: Int) {
        println(titulares[posicion])
    }

    fun añadirNuevoTitular(){
        var posicionLibre = buscarHuecoLibre()
        if(posicionLibre == -1){
            println("No hay espacio libre para añadir a un nuevo titular")
        }else{
            println("Introduce el dni del nuevo titular:")
            val dni = introducirDni()
            println("Introduce el nombre del nuevo titular:")
            val nombre = introducirNombre()
            println("Introduce el telefono del nuevo titular:")
            val telefono = introducirTelefono()
            titulares[posicionLibre] = crearTitular(dni, nombre, telefono)
        }
        println()
    }

    fun crearTitular(dni: String, nombre: String, telefono: String): RehechoTitular? {
        return RehechoTitular(dni, nombre, telefono)
    }

    fun eliminarTitularDeLaCuenta(){
        println("Introduzca el dni del titular que quieres eliminar:")
        val dni = introducirDni()
        val posicion = buscarTitularPorDni(dni)
        if(posicion == -1){
            println("No hay ningún titular con ese dni")
        }else{
            println("Se ha eliminado al titular:")
            println(borrarTitular(posicion).toString())
        }
        println()
    }

    fun borrarTitular(posicion: Int): Any {
        val titularEliminado = titulares[posicion]!!.copy()
        titulares[posicion] = null
        return titularEliminado
    }

    fun cambiarInformacionTitularDeLaCuenta(){
        println("Introduzca el dni del titular que quieres actualizar de la cuenta:")
        val dni = introducirDni()
        val posicion = buscarTitularPorDni(dni)
        if(posicion == -1){
            println("No hay ningún titular con ese dni")
        }else{
            println("Se ha encontrado al titular:")
            mostrarTitularPorDni(posicion)
            println("Introduce el nuevo nombre del titular:")
            val nombre = introducirNombre()
            println("Introduce el nuevo telefono del titular:")
            val telefono = introducirTelefono()
            titulares[posicion] = actualizarTitular(nombre, telefono, titulares[posicion])
        }
        println()
    }

    fun actualizarTitular(nombre: String, telefono: String, titular: RehechoTitular?): RehechoTitular? {
        return titular!!.copy(nombre = nombre, telefono = telefono)
    }

    fun informacion() {
        var nTitulares = 0
        for(i in titulares.indices){
            if(titulares[i] != null){
                nTitulares++
            }
        }
        println("""
            Cuenta: ${this.id_cuenta}
            Hay un total de $nTitulares titulares:
        """.trimIndent())
        for(i in titulares.indices){
            if(titulares[i] != null){
                println(titulares[i])
            }
        }
        println("Hay un total de ${this.saldo} euros en la cuenta")
        println()
    }

    fun introducirDineroEnCuenta(){
        println("Introduce la cantidad de dinero que vas ha ingresar en la cuenta:")
        val cantidad = introducirCantidad()
        ingresarDinero(cantidad)
        println("Tras la operación, tienes ${this.saldo} euros")
        println()
    }

    fun ingresarDinero(cantidad: Double) {
        this.saldo = this.saldo + cantidad
    }

    fun sacarDineroDeCuenta(){
        println("Introduce la cantidad de dinero que vas ha retirar de la cuenta:")
        val cantidad = introducirCantidad()
        if(cantidad > this.saldo){
            println("No puedes sacar más dinero de lo que tienes en la cuenta, actualmente tienes ${this.saldo} euros")
        }else{
            sacarDinero(cantidad)
            println("Tras la operación, te quedan ${this.saldo} euros")
        }
        println()
    }

    fun sacarDinero(cantidad: Double) {
        this.saldo = this.saldo - cantidad
    }

    fun buscarHuecoLibre(): Int {
        var posicion = -1
        for(i in titulares.indices){
            if(titulares[i] == null){
                posicion = i
                break
            }
        }
        return posicion
    }

    fun buscarTitularPorDni(dni: String): Int {
        var posicion = -1
        for(i in titulares.indices){
            if(titulares[i] != null){
                if(titulares[i]!!.dni == dni){
                    posicion = i
                }
            }
        }
        return posicion
    }

    fun introducirDni(): String{
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

    fun dniValido(dni: String): Boolean {
        require(dni.isNotEmpty()){"El dni no puede estar vacio, vuelve a probar:"}
        val regex = Regex("[0-9]{8}[A-Z]")
        require(dni.matches(regex)){"El dni introducido no cumple con el patrón esperado, vuelve a probar:"}
        return true
    }

    fun introducirNombre(): String{
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

    fun nombreValido(nombre: String): Boolean {
        require(nombre.isNotEmpty()){"El nombre no puede estar vacio, vuelve a probar:"}
        val regex = Regex("[A-Za-záéíúóÁÉÍÚÓ]+")
        require(nombre.matches(regex)){"El nombre introducido no puede tener mas que letras, vuelve a probar:"}
        return true
    }

    fun introducirCantidad(): Double{
        var cantidad = 0.0
        do{
            try{
                cantidad = readln().toDouble()
                cantidadValida(cantidad)
            }catch(e: Exception){
                println(e.message)
                cantidad = 0.0
            }
        }while(cantidad == 0.0)
        return cantidad
    }

    fun cantidadValida(cantidad: Double): Boolean {
        require(cantidad in (0.1..500.0)){"La cantidad debe estar entre 0.1 y 500.0, vuelve a probar:"}
        return true
    }

    fun introducirTelefono(): String{
        var telefono = ""
        do{
            try{
                telefono = readln().trim()
                telefonoValido(telefono)
            }catch(e: Exception){
                println(e.message)
                telefono = ""
            }
        }while(telefono == "")
        return telefono
    }

    fun telefonoValido(telefono: String): Boolean {
        require(telefono.isNotEmpty()){"El telefono no puede estar vacio, vuelve a probar:"}
        val regex = Regex("[0-9]{9}")
        require(telefono.matches(regex)){"El telefono introducido no cumple con el patrón esperado, vuelve a probar:"}
        return true
    }
}