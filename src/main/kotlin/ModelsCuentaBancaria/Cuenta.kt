package ModelsCuentaBancaria

data class Cuenta(val jefe: String) {
    companion object{
        private var contador = 0
        private fun nextId(): Int{
            return contador++
        }
    }

    val idCuenta: Int = nextId()

    var titulares: Array<Titular?> = Array<Titular?>(3){null}
    var dineroTitulares: Array<Dinero?> = Array<Dinero?>(3){null}

    /**
     * funcion que sirve para mostrar el menu de opciones y recoger la opcion elegida por el usuario
     * @return la opcion que haya elegido el usuario tras ser validada
     */
    fun menu(): Int{
        var opcion = 0
        do {
            println("[1] Mostrar todos los titulares")
            println("[2] Mostrar titular elegido por dni")
            println("[3] Añadir titular a la cuenta")
            println("[4] Eliminar titular de la cuenta")
            println("[5] Actualizar titular de la cuenta")
            println("[6] Información de la cuenta")
            println("[7] Ingresar dinero en cuenta")
            println("[8] Retirar dinero de la cuenta")
            println("[0] Salir")
            opcion = readln().toInt()
            try {
                opcionValida(opcion)
            } catch (e: Exception) {
                println(e.message)
                opcion = -1
            }
        }while(opcion == -1)
        return opcion
    }

    /**
     * fun que valida si la opcion elegida es correcta o no
     * @param opcion es la opcion elegida por el usuario
     * @return true en el caso de que la opción sea correcta, tira una excepción en caso de que no sea correcta
     */
    fun opcionValida(opcion: Int): Boolean{
        require(opcion in 0..8){"La opcion elegida no existe, vuelve a probar:"}
        return true
    }

    /**
     * función que sirve para mostrar a todos los titulares de la cuenta del banco ordenado por su id
     */
    fun mostrarTodosLosTitulares(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>){
        //Esta parte es opcional, pero lo que hace es defragmentar el vector de alumnos.
        for (i in titulares.indices) {
            if (titulares[i] != null) {
                var j = i;
                if (j - 1 > 0 && titulares[j - 1] == null) {
                    while (j - 1 > 0 && titulares[j - 1] == null) {
                        j--;
                    }
                    titulares[j] = titulares[i];
                    titulares[i] = null;
                }
            }
        }

        ordenarMetodoBurbujaAscendente(titulares)

        for(i in titulares.indices){
            if(titulares[i] != null && dineroTitulares[i] != null){
                println("Titular: ${titulares[i]}")
                println("Dinero del titular: ${dineroTitulares[i]}")
                println()
            }
        }
    }

    /**
     * función que sirve para mostrar la info de solo uno de los titulares
     */
    fun mostrarTitularPorDni(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>){
        println("Introduzca el dni del titular que desea buscar:")
        val titular = buscarTitularPorDni(titulares, introducirDni())
        if(titular != -1){
            println("Titular: ${titulares[titular]}")
            println("Dinero: ${dineroTitulares[titular]}")
            println()
        }else{
            println("No hay ningún titular con ese dni")
        }
    }

    /**
     * funcion que ordena los titulares y las cantidades de dinero ascendenetemente según el id del titular
     */
    fun ordenarMetodoBurbujaAscendente(titulares: Array<Titular?>) {
        for(i in 0..titulares.size - 1){
            for(j in 0..titulares.size - 1 - i){
                if (j + 1 < titulares.size) {
                    if(titulares[j] != null && titulares[j+1] != null) {
                        if (titulares[j]?.idTitular ?: -1 > titulares[j + 1]?.idTitular ?: -1) {
                            var auxiliar = titulares[j]
                            titulares[j] = titulares[j + 1]
                            titulares[j + 1] = auxiliar
                            var auxiliar2 = dineroTitulares[j]
                            dineroTitulares[j] = dineroTitulares[j + 1]
                            dineroTitulares[j + 1] = auxiliar2
                        }
                    }
                }
            }
        }
    }

    /**
     * función que sirve para encontrar la primera posicion vacia en el vecto donde, en caso de haber, meteremos al nuevo titular y su cantidad en sus respectivos vectores
     * @return la posicion del primer nulo, en caso de que no haya devolvera un -1
     */
    fun primerNull(titulares: Array<Titular?>): Int {
        for(i in titulares.indices){
            if(titulares[i] == null){
                return i
            }
        }
        return -1
    }

    /**
     * función que sirve para añadir un nuevo titular a la cuenta bancaria, en caso de que hay espacio para meter a una nuevo titular
     */
    fun añadirTitular(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>){
        var posicion = primerNull(titulares)
        if(posicion != -1){
            println("Introduzca el dni del nuevo titular de cuenta:")
            val dni: String = introducirDni()
            println("Introduzca el nombre del nuevo titular de cuenta:")
            val nombre: String = introducirNombre()
            println("Introduzca el email del nuevo titular de cuenta:")
            val email: String = introducirEmail()
            titulares[posicion] = crearTitular(dni, nombre, email)
            println("Introduzca la nueva cantidad de dinero del titular:")
            dineroTitulares[posicion] = crearDinero(posicion)
        }else{
            println("No hay espacio para añadir a un nuevo titular")
        }
    }

    private fun crearDinero(posicion: Int): Dinero? {
        val cantidad = introducirCantidad()
        return Dinero(cantidad)
    }

    /**
     * funcion que crea un nuevo titular acorde a los datos proporcianado para ello
     * @param dni es el dni introducido por teclado y validado
     * @param nombre es el nombre introducido por teclado y validado
     * @param email es el email introducido por teclado y validado
     * @return el titular recien creado
     */
    fun crearTitular(dni: String, nombre: String, email: String): Titular? {
        return Titular(dni, nombre, email)
    }

    /**
     * funcion que permite cambiar la info asociada a un titular y su dinero, siempre y cuando encuentre al titular por el dni que se introduzca
     */
    fun cambiarInfoTitular(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>){
        println("Introduzca el dni del titular que desea actualizar:")
        var titular = buscarTitularPorDni(titulares, introducirDni())
        if(titular != -1){
            println("Introduzca el dni del nuevo titular de cuenta:")
            val dni: String = introducirDni()
            println("Introduzca el nombre del nuevo titular de cuenta:")
            val nombre: String = introducirNombre()
            println("Introduzca el email del nuevo titular de cuenta:")
            val email: String = introducirEmail()
            titulares[titular] = actualizarTitular(titular, dni, nombre, email)
            println("Introduzca la nueva cantidad de dinero del titular:")
            dineroTitulares[titular] = actualizarDinero(titular)
        }else{
            println("No se encontró a ningún titular con el dni introducido")
        }
    }

    /**
     * función que sirve para actualizar la info del dinero asociado a un titular
     * @param titular es la posicion del titular en el vector que los contiene, es equivalente a la posicion del vector que contiene los dineros para cada titular
     */
    fun actualizarDinero(titular: Int): Dinero? = dineroTitulares[titular]!!.copy(cantidad = introducirCantidad())

    /**
     * función que sirve para actualizar la info de un titular
     * @param titular es la posicion del titular en el vector que los contiene
     * @param dni es el dni introducido por teclado y validado
     * @param nombre es el nombre introducido por teclado y validado
     * @param email es el email introducido por teclado y validado
     * @return el titular recien actualizado
     */
    fun actualizarTitular(
        titular: Int,
        dni: String,
        nombre: String,
        email: String
    ) = crearTitular(dni, nombre, email).also { titulares[titular] = it }

    /**
     * función que sirve para borrar por completo los contenidos de un titular y su dinero
     */
    fun eliminarTitular(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>){
        println("Introduzca el dni del titular que desea borrar:")
        var titular = buscarTitularPorDni(titulares, introducirDni())
        if(titular != -1){
            println("El titular borrado fue: ${borrarTitular(titular)}")
        }else{
            println("No se encontró a ningún titular con el dni introducido")
        }
    }

    /**
     * funcion que sirve para borrar todo el contenido del titular seleccionado
     * @return
     */
    fun borrarTitular(pos: Int): Titular? {
        var titular = titulares[pos]
        titulares[pos] = null
        dineroTitulares[pos] = null
        return titular
    }

    /**
     * función que genera un pequeño texto informativo
     */
    fun informacion(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>) {
        var nTitulares = 0
        for(i in titulares.indices){
            if(titulares[i] != null){
                nTitulares++
            }
        }
        println("""
            Cuenta: $idCuenta
            Hay un total de $nTitulares titulares:
        """.trimIndent())
        for(i in titulares.indices){
            if(titulares[i] != null){
                println(titulares[i])
                println(dineroTitulares[i])
                println()
            }
        }
    }

    /**
     * funcion que sirve para ingresar dinero a un titular, en caso de que se encuentre via el dni que tendras que introducir
     * @return la cantidad final de dinero que queda de ese titular
     */
    fun ingresarDineroTitular(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>){
        println("introduzca el dni del titular al que desea ingresar dinero:")
        val titular = buscarTitularPorDni(titulares, introducirDni())
        if(titular != -1){
            println("Introduzca la cantidad que va ha introducir:")
            dineroTitulares[titular] = Dinero(cantidad = dineroTitulares[titular]!!.cantidad + introducirCantidad())
        }else{
            println("No existe un titular con ese dni")
        }
    }

    /**
     * funcion que sirve para retirar dinero a un titular, en caso de que se encuentre via el dni que tendras que introducir
     * @return la cantidad final de dinero que queda de ese titular
     */
    fun retirarDineroTitular(titulares: Array<Titular?>, dineroTitulares: Array<Dinero?>){
        println("introduzca el dni del titular al que desea ingresar dinero:")
        val titular = buscarTitularPorDni(titulares, introducirDni())
        if(titular != -1){
            println("Introduzca la cantidad que va ha retirar:")
            var cantidad = 0.0
            do {
                cantidad = introducirCantidad()
                try {
                    require(cantidad <= dineroTitulares[titular]!!.cantidad) { "No puedes retirar más dinero del que tienes en la cuenta, y tienes un total de: ${dineroTitulares[titular]!!.cantidad}" }
                } catch (e: Exception) {
                    println(e.message)
                    cantidad = -1.0
                }
            }while(cantidad == -1.0)
            dineroTitulares[titular] = Dinero(cantidad = dineroTitulares[titular]!!.cantidad - cantidad)
        }else{
            println("No existe un titular con ese dni")
        }
    }

    /**
     * funcion que busca a un titular según el dni que se introduzca
     * @return la posicion del titular en caso de que se encuentre uno con el dni que se introduzca, -1 en caso de no encontrar un titular con el dni que se introduzca
     */
    fun buscarTitularPorDni(titulares: Array<Titular?>, dni: String): Int {
        var titular = -1
        for(i in titulares.indices){
            if(titulares[i] != null) {
                if (titulares[i]!!.dni == dni){
                    titular = i
                    break
                }
            }
        }
        return titular
    }

    /**
     * funcion que sirve para devolver el cantidad introducido por teclado, tras haber comprobado que es correcto
     * @return el cantidad introducido por teclado
     */
    fun introducirCantidad(): Double {
        var cantidad = 0.0
        do{
            cantidad = readln().toDouble()
            try{
                cantidadValida(cantidad)
            }catch(e: Exception){
                println(e.message)
                cantidad = -1.0
            }
        }while(cantidad == -1.0)
        return cantidad
    }

    /**
     * funcion que sirve para comprobar si un cantidad cumple con las condiciones para ser correcto
     * @param cantidad es el cantidad introducido por teclado
     * @return true en caso de estar correcto, una excepcion en caso de estar incorrecto
     */
    fun cantidadValida(cantidad: Double): Boolean {
        require(cantidad <= 500.0){"La cantidad no puede ser mayor que 500.0, vuelve a probar:"}
        require(cantidad != 0.0){"La cantidad no puede ser 0.0, vuelve a probar:"}
        require(cantidad > 0.0){"La cantidad no puede ser negativa, vuelve a probar:"}
        return true
    }

    /**
     * funcion que sirve para devolver el dni introducido por teclado, tras haber comprobado que es correcto
     * @return el dni introducido por teclado
     */
    fun introducirDni(): String {
        var dni = ""
        do{
            dni = readln()
            try{
                dniValido(dni)
            }catch(e: Exception){
                println(e.message)
                dni = ""
            }
        }while(dni == "")
        return dni
    }

    /**
     * funcion que sirve para comprobar si un dni cumple con las condiciones para ser correcto
     * @param dni es el dni introducido por teclado
     * @return true en caso de estar correcto, una excepcion en caso de estar incorrecto
     */
    fun dniValido(dni: String): Boolean {
        val regex = Regex("[0-9]{8}[A-Z]")
        require(dni.matches(regex)){"El dni introducido no es acepable, vuelve a probar:"}
        return true
    }

    /**
     * funcion que sirve para devolver el nombre introducido por teclado, tras haber comprobado que es correcto
     * @return el nombre introducido por teclado
     */
    fun introducirNombre(): String {
        var nombre = ""
        do{
            nombre = readln()
            try{
                nombreValido(nombre)
            }catch(e: Exception){
                println(e.message)
                nombre = ""
            }
        }while(nombre == "")
        return nombre
    }

    /**
     * funcion que sirve para comprobar si un nombre cumple con las condiciones para ser correcto
     * @param nombre es el nombre introducido por teclado
     * @return true en caso de estar correcto, una excepcion en caso de estar incorrecto
     */
    fun nombreValido(nombre: String): Boolean {
        val regex = Regex("[a-zA-ZáéíúóÁÉÚÍÓ]+")
        require(nombre.isNotEmpty()){"El nombre no puede estar vacio, vuelve a probar:"}
        require(nombre.matches(regex)){"El nombre no puede contener números, vuelve a probar:"}
        return true
    }

    /**
     * funcion que sirve para devolver el email introducido por teclado, tras haber comprobado que es correcto
     * @return el email introducido por teclado
     */
    fun introducirEmail(): String {
        var email = ""
        do{
            email = readln()
            try{
                emailValido(email)
            }catch(e: Exception){
                println(e.message)
                email = ""
            }
        }while(email == "")
        return email
    }

    /**
     * funcion que sirve para comprobar si un email cumple con las condiciones para ser correcto
     * @param email es el email introducido por teclado
     * @return true en caso de estar correcto, una excepcion en caso de estar incorrecto
     */
    fun emailValido(email: String): Boolean {
        val regex = Regex("[a-zA-ZáéíúóÁÉÚÍÓ1234567890]+@gmail.com")
        require(email.matches(regex)){"El email introducido no es acepable, vuelve a probar:"}
        return true
    }
}