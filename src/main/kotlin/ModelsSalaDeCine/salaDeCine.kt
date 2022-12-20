package ModelsSalaDeCine

data class salaDeCine(val nombre: String, val fila: Int, val columna: Int, val pelicula: pelicula) {

    var butacas: Array<Array<butaca?>> = Array(fila){Array<butaca?>(columna){null} }

    init {
        for(i in 0..fila-1){
            for(j in 0..columna-1){
                butacas[i][j] = butaca(hallarIdentificadorButaca(i, j) , "libre")
            }
        }
    }

    /**
     * función que sirve para averiguar el identificador de la butaca a la hora de inicializar la matriz
     * @param fila la posición de la fila sobre la que se colocará la butaca
     * @param columna la posición de la columna sobre la que se colocará la butaca
     * @return el indentificador creado a partir de los datos pasados por parametros
     */
    fun hallarIdentificadorButaca(fila: Int, columna: Int): String {
        var identificador = "${(65 + fila).toChar()}${columna+1}"
        return identificador
    }

    /**
     * función que sirve para crear y devolver una sala de cine
     * @param nombre es el nombre de la sala de cine
     * @param fila es el número de filas de butacas en la sala
     * @param coluna es el número de columnas de butacas en la sala
     * @return la sala de cine creada según el parametro introducido
     */
    fun create(nombre: String, fila: Int, columna: Int, pelicula: pelicula): salaDeCine{
        return salaDeCine(nombre, fila, columna, pelicula)
    }

    /**
     * función que sirve para presentar un menu al usuario y conseguir la opción que seleccione
     * @return la opcion seleccionada por el usuario
     */
    fun menu(): Int{
        println("[1] Comprar entrada")
        println("[2] Reservar entrada")
        println("[3] Formalizar reserva de entrada")
        println("[4] Anular reserva o compra de entrada")
        println("[5] Conseguir informe de la sala")
        println("[6] Conseguir recaudación total de la caja")
        println("[0] Salir")
        return introducirOpcion()
    }

    /**
     * función que sirve para comprar 1 o varias entradas
     */
    fun comprarEntrada(){
        println("La sala del cine es la siguiente:")
        representacionInicialDeLaSala()
        var butaca = ""
        var contadorEntradas = 0
        do {
            println("""Introduzca las butacas que quieras comprar, puedes introducir el indentificador de una butaca a comprar ( A1, por ejemplo ) o escribir "stop" para dejar de comprar butacas: """)
            butaca = introducirButaca()
            if(butacaLibre(butaca)) {
                if (butaca != "stop") {
                    val posicionButaca = buscarButacaPorIndentificador(butaca)
                    if (posicionButaca != Pair(-1, -1)) {
                        println("De acuerdo, has comprado la butaca $butaca")
                        butacas[posicionButaca.first][posicionButaca.second]!!.estado = "comprado"
                        contadorEntradas++
                    } else {
                        println("No existe ninguna butaca con esa identificación")
                    }
                }
            }else{
                println("La bucata seleccionada ya está ocupada")
            }
        }while(butaca != "stop")
        println("Has comprado un total de $contadorEntradas butacas")
    }

    /**
     * función que sirve para reservar 1 o varias entradas
     */
    fun reservarEntrada(){
        println("La sala del cine es la siguiente:")
        representacionInicialDeLaSala()
        var butaca = ""
        var contadorEntradas = 0
        do {
            println("""Introduzca las butacas que quieras reservar, puedes introducir el indentificador de una butaca a reservar ( A1, por ejemplo ) o escribir "stop" para dejar de reservar butacas: """)
            butaca = introducirButaca()
            if(butacaLibre(butaca)) {
                if (butaca != "stop") {
                    val posicionButaca = buscarButacaPorIndentificador(butaca)
                    if (posicionButaca != Pair(-1, -1)) {
                        println("De acuerdo, has reservado la butaca $butaca")
                        butacas[posicionButaca.first][posicionButaca.second]!!.estado = "reservado"
                        contadorEntradas++
                    } else {
                        println("No existe ninguna butaca con esa identificación")
                    }
                }
            }else{
                println("La bucata seleccionada ya está ocupada")
            }
        }while(butaca != "stop")
        println("Has reservado un total de $contadorEntradas butacas")
    }

    /**
     * función que sirve para formalizar de reservado a comprado 1 o varias butacas
     */
    fun formalizarReserva(){
        println("La sala del cine es la siguiente:")
        representacionInicialDeLaSala()
        var butaca = ""
        var contadorEntradas = 0
        do {
            println("""Introduzca las butacas que quieras formalizar de reservas a compras, puedes introducir el indentificador de una butaca ( A1, por ejemplo ) o escribir "stop" para dejarlo: """)
            butaca = introducirButaca()
            if(butaca != "stop"){
                val posicionButaca = buscarButacaPorIndentificador(butaca)
                if(posicionButaca != Pair(-1, -1) && butacas[posicionButaca.first][posicionButaca.second]!!.estado == "reservado") {
                    println("De acuerdo, has formalizado la butaca $butaca")
                    butacas[posicionButaca.first][posicionButaca.second]!!.estado = "comprado"
                    contadorEntradas++
                }else{
                    if(butacas[posicionButaca.first][posicionButaca.second]!!.estado == "reservado") {
                        println("No existe ninguna butaca con esa identificación")
                    }else{
                        println("Esa butaca no estaba reservada, por lo que no puedes formalizarla")
                    }
                }
            }
        }while(butaca != "stop")
        println("Has formalizado un total de $contadorEntradas butacas")
    }

    /**
     * función que sirve para formalizar de reservado a comprado 1 o varias butacas
     */
    fun anularReservaCompra(){
        println("La sala del cine es la siguiente:")
        representacionInicialDeLaSala()
        var butaca = ""
        var contadorEntradas = 0
        do {
            println("""Introduzca las butacas cuya reserva/compra quieras anular, puedes introducir el indentificador de una butaca ( A1, por ejemplo ) o escribir "stop" para dejar de anular reservas/compras: """)
            butaca = introducirButaca()
            if(butaca != "stop"){
                val posicionButaca = buscarButacaPorIndentificador(butaca)
                if(posicionButaca != Pair(-1, -1)) {
                    if(butacas[posicionButaca.first][posicionButaca.second]!!.estado == "reservado" || butacas[posicionButaca.first][posicionButaca.second]!!.estado == "comprado"){
                        println("De acuerdo, has cancelado la reserva/compra de la butaca $butaca")
                        butacas[posicionButaca.first][posicionButaca.second]!!.estado = "libre"
                        contadorEntradas++
                    }
                }else{
                    if(butacas[posicionButaca.first][posicionButaca.second]!!.estado == "reservado" || butacas[posicionButaca.first][posicionButaca.second]!!.estado == "comprado") {
                        println("No existe ninguna butaca con esa identificación")
                    }else{
                        println("Esa butaca está libre, por lo que no puedes cancelar ninguna reserva/compra")
                    }
                }
            }
        }while(butaca != "stop")
        println("Has cancelado la reserva/compra de un total de $contadorEntradas butacas")
    }

    /**
     * función que proporciona un informe de la sala del cine
     */
    fun informeDeLaSalaDeCine(){
        representarButacas()
        var tiposDeButacas = contarNumeroDeButacasPorTipo()
        println("Hay un total de ${tiposDeButacas.first} butacas libres")
        println("Hay un total de ${tiposDeButacas.second} butacas reservadas")
        println("Hay un total de ${tiposDeButacas.third} butacas compradas")
    }

    /**
     * función que sirve para calcular el dinero total que hay en caja para la pelicula
     */
    fun dineroTotalEnCaja(){
        println("El dinero total en caja en la actualidad es de: ${calcularDineroEnCaja()}")
    }

    /**
     * función que sirve para calcular el dinero en caja actual de la sala de cine
     * @return el dinero en caja actual en la sala de cine
     */
    fun calcularDineroEnCaja(): Double {
        var resultado = 0.0
        for(i in 0..butacas.size-1){
            for(j in 0..butacas[i].size-1){
                if(butacas[i][j] != null){
                    if(butacas[i][j]!!.estado == "comprado"){
                        when(butacas[i][j]!!.esVip){
                            true -> resultado += 5.25
                            false -> resultado += 8.5
                        }
                    }
                }
            }
        }
        return resultado
    }

    /**
     * función que sirve para calcula el número de butacas libres, reservadas y compradas que hay
     * @return un triple con el número de butacas libres, reservadas y compradas
     */
    fun contarNumeroDeButacasPorTipo(): Triple<Int, Int, Int> {
        var libres = 0
        var reservadas = 0
        var compradas = 0
        for(i in 0..butacas.size-1){
            for(j in 0..butacas[i].size-1){
                if(butacas[i][j] != null){
                    when(butacas[i][j]!!.estado){
                        "libre" -> libres++
                        "reservado" -> reservadas++
                        "comprado" -> compradas++
                    }
                }
            }
        }
        return Triple(libres, reservadas, compradas)
    }

    /**
     * función que sirve para representar las butacas de la sala de cine como libre, reservadas o compradas
     */
    fun representarButacas() {
        var mensaje = ""
        for(i in 0..butacas.size-1){
            for(j in 0..butacas[i].size-1){
                if(butacas[i][j] != null){
                    when(butacas[i][j]!!.estado){
                        "libre" -> mensaje = "$mensaje l"
                        "reservado" -> mensaje = "$mensaje r"
                        "comprado" -> mensaje = "$mensaje c"
                    }
                }
            }
            println(mensaje)
            mensaje = ""
        }
    }

    /**
     * función que sirve para hallar la posición de una butaca según su identificador
     * @param identificador el dato de la butaca usado para buscar la posición de la butaca
     * @return un par con la fila y columna de la butaca
     */
    fun buscarButacaPorIndentificador(identificador: String): Pair<Int, Int> {
        var posicion = Pair(-1, -1)
        for(i in 0..butacas.size-1){
            for(j in 0..butacas[i].size-1){
                if(butacas[i][j] != null) {
                    if (butacas[i][j]!!.identificador == identificador){
                        posicion = Pair(i, j)
                        break
                    }
                }
            }
            if(posicion != Pair(-1, -1)){
                break
            }
        }
        return posicion
    }

    /**
     * función que sirve para introducir un identificador de butaca válido o "stop"
     * @return la butaca o el "stop" introducido por teclado
     */
    fun introducirButaca(): String{
        var butaca = ""
        do{
            try{
                butaca = readln().trim()
                butacaValida(butaca)
            }catch(e: Exception){
                println(e.message)
                butaca = ""
            }
        }while(butaca == "")
        return butaca
    }

    /**
     * función que sirve para validar la butaca o el "stop" introducido por teclado
     * @param butaca lo que queremos validar
     * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
     * @return true en caso de que sea válido
     */
    fun butacaValida(butaca: String): Boolean {
        require(butaca != null){("El mensaje no puede ser nulo, vuelve a probar:")}
        require(butaca.isNotEmpty()){"El mensaje no puede estar vacio, vuelve a probar:"}
        val regex = Regex("[A-Z][0-9]+")
        require(butaca == "stop" || butaca.matches(regex)){"EL menaje introducido no es válido, vuelve a probar:"}
        return true
    }

    /**
     * función que sirve para comprobar si la butaca seleccionada está libre o no
     * @param butaca lo que queremos validar
     * @return true si está libre, false si está ocupada
     */
    fun butacaLibre(butaca: String): Boolean {
        for(i in 0..butacas.size-1){
            for(j in 0..butacas[i].size-1){
                if(butacas[i][j] != null){
                    if(butacas[i][j]!!.identificador == butaca){
                        if(butacas[i][j]!!.estado != "libre"){
                            return false
                        }
                    }
                }
            }
        }
        return true
    }

    /**
     * función que sirve para introducir una opción válida
     * @return la opcion válida
     */
    fun introducirOpcion(): Int {
        var opcion = 0
        do {
            try {
                opcion = readln().toInt()
                opcionValida(opcion)
            } catch (e: Exception) {
                println(e.message)
                opcion = -1
            }
        } while (opcion == -1)
        return opcion
    }

    /**
     * función que sirve para validar la opción introducida por teclado
     * @param opcion lo que queremos validar
     * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
     * @return true en caso de que sea válido
     */
    fun opcionValida(opcion: Int): Boolean {
        require(opcion != null){"La opción no puede ser nula, vuelve a probar:"}
        require(opcion in 0..6){"No has elegido una de las opciones posibles, vuelve a probar:"}
        return true
    }

    /**
     * función que sirve para representar las butacas de la sala de cine con su identificador
     */
    fun representacionInicialDeLaSala() {
        var mensaje = ""
        for(i in 0..butacas.size-1){
            for(j in 0..butacas[i].size-1){
                if(butacas[i][j] != null){
                    mensaje = "$mensaje ${butacas[i][j]!!.identificador}"
                }
            }
            println(mensaje)
            mensaje = ""
        }
    }

    companion object {
        /**
         * función que sirve para introducir una fila o columna válida
         * @return la fila o columna introducida por teclado
         */
        fun introducirFilaColumna(): Int{
            var filaColumna = 0
            do{
                try{
                    filaColumna = readln().toInt()
                    filaColumnaValida(filaColumna)
                }catch(e: Exception){
                    println(e.message)
                    filaColumna = -1
                }
            }while(filaColumna == -1)
            return filaColumna
        }

        /**
         * función que sirve para validar el año de publicación introducido por teclado
         * @param filaColumna lo que queremos validar
         * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
         * @return true en caso de que sea válido
         */
        fun filaColumnaValida(filaColumna: Int): Boolean {
            require(filaColumna != null){("La fila/columna no puede ser nulo, vuelve a probar:")}
            require(filaColumna > 0){"La fila/columna no puede ser negativo, vuelve a probar:"}
            require(filaColumna < 26){"La fila/columna sobrepasar el tamaño 26, vuelve a probar:"}
            return true
        }
        /**
         * función que sirve para introducir un nombre válido
         * @return el nombre introducido por teclado
         */
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

        /**
         * función que sirve para validar el nombre introducido por teclado
         * @param nombre lo que queremos validar
         * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
         * @return true en caso de que sea válido
         */
        fun nombreValido(nombre: String): Boolean {
            require(nombre != null){("El nombre no puede ser nulo, vuelve a probar:")}
            require(nombre.isNotEmpty()){"El nombre no puede estar vacio, vuelve a probar:"}
            return true
        }
    }
}