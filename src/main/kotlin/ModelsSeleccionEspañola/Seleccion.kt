package ModelsSeleccionEspañola

import TipoDemarcacion

data class Seleccion(val nombre: String?, val entrenador: Entrenador?) {

    companion object{
        val dorsales: Array<Int?> = Array<Int?>(15) {null}
        private var contador: Int = 0
        private fun nextId(): Int{
            return contador++
        }
        fun resetContadorIdSeleccion(){
            contador = 0
        }
    }

    var jugadores: Array<Jugador?> = Array<Jugador?>(15){null}

    private fun rellenarvectorDorsales(){
        for(i in jugadores.indices){
            if(jugadores[i] != null){
                dorsales[i] = jugadores[i]!!.dorsal
            }
        }
    }

    val idSeleccion: Int = nextId()

    fun informacion(jugadores: Array<Jugador?>) {
        var nJugadores = 0
        for(i in jugadores.indices){
            if(jugadores[i] != null){
                nJugadores++
            }
        }
        println("""
            Equipo: $nombre
            ID_Equipo: $idSeleccion
            Entrenador: ${entrenador?.nombre}
            ID_Entrenador: ${entrenador?.idEntrenador}
            Hay un total de $nJugadores jugadores:
        """.trimIndent())
        for(i in jugadores.indices){
            if(jugadores[i] != null){
                println(jugadores[i])
            }
        }
    }

    fun actualizarInfoJugador(seleccion: Seleccion) {
        println("Introduzca el dorsal del jugador que desea actualizar:")
        var pos = seleccion.introducirDorsal()
        println("Introduzca el nombre del nuevo jugador:")
        val nombre: String = seleccion.introducirNombre()
        println("Introduzca la desmarcacion del nuevo jugador:")
        val desmarcacion: TipoDemarcacion = introducirDesmarcacion()
        seleccion.jugadores[pos] = actualizarJugador(seleccion.jugadores[pos]!!.copy(nombre = nombre, demarcacion = desmarcacion), pos)
    }


    fun actualizarJugador(jugador: Jugador?, dorsal: Int): Jugador? {
        return if (dorsal != -1) {
            jugador
        } else
            null
    }

    fun eliminarJugador(jugadores: Array<Jugador?>, dorsal: Int?): Array<Jugador?>  {

        val pos: Int = encontrarJugadorPorDorsal(jugadores, dorsal)

        if(pos == -1){
            println("No existe ningún jugador con ese dorsal")
        }else{
            println("El jugador: ${jugadores[pos]}, fue eliminado")
            jugadores[pos] = null
        }
        return jugadores
    }

    fun encontrarJugadorPorDorsal(jugadores: Array<Jugador?>, dorsal: Int?): Int {
        var pos = -1
        for(i in jugadores.indices){
            if(jugadores[i]?.dorsal?:-1 == dorsal){
                pos = i
                break
            }
        }
        return pos
    }

    fun añadirJugador(jugadores: Array<Jugador?>): Array<Jugador?>  {
        var pos = primerNull(jugadores)
        if(pos==-1){
            println("No hay espacio disponible para meter a más jugadores")
        }else{
            jugadores[pos] = crearJugador()
        }
        return jugadores
    }

    fun crearJugador(): Jugador? {
        println("Introduzca el número del dorsal del nuevo jugador:")
        val dorsal: Int = introducirDorsal()
        println("Introduzca el nombre del nuevo jugador:")
        val nombre: String = introducirNombre()
        println("Introduzca la desmarcacion del nuevo jugador:")
        val desmarcacion: TipoDemarcacion = introducirDesmarcacion()

        return Jugador(dorsal, nombre, desmarcacion)
    }

    fun primerNull(jugadores: Array<Jugador?>): Int {
        for(i in jugadores.indices){
            if(jugadores[i] == null){
                return i
            }
        }
        return -1
    }

    fun mostrarJugadorPorDorsal(jugadores: Array<Jugador?>) {
        println("Introduce el dorsal del jugador que quieres buscar:")
        var dorsal = introducirDorsal()

        val jugador: Jugador? = buscarJugadorPorDorsal(jugadores, dorsal)

        if(jugador == null){
            println("No se encontró a ningún jugador con ese dorsal")
        }else{
            println("Se encontro al jugador: $jugador")
        }
    }

    fun buscarJugadorPorDorsal(jugadores: Array<Jugador?>, dorsal: Int): Jugador? {
        var jugador : Jugador? = null
        for(i in jugadores){
            if(i?.dorsal?:-1 == dorsal){
                jugador = i
                break
            }
        }
        return jugador
    }

    fun introducirDorsal(): Int {
        var dorsal = 0
        do {
            dorsal = readln().toInt()
            try {
                dorsalValido(dorsal)
            } catch (e: Exception) {
                println(e.message)
                dorsal = -1
            }
        }while(dorsal == -1)
        return dorsal
    }

    fun dorsalValido(dorsal: Int): Boolean {
        require(dorsal >= 0){"El dorsal no puede ser negativo, vuelve a probar:"}
        for(i in dorsales.indices){
            require(dorsal != dorsales[i]?:-1){"El dorsal que has seleccionado ya estaba elegido, vuelve a probar"}
        }
        return true
    }

    fun introducirNombre(): String {
        var nombre = ""
        do {
            nombre = readln()
            try {
                nombreValido(nombre)
            } catch (e: Exception) {
                println(e.message)
                nombre = ""
            }
        }while(nombre == "")
        return nombre
    }

    fun nombreValido(nombre: String):Boolean {
        require(nombre.isNotEmpty()){"No puedes introducir un nombre vacio, vuelve a probar:"}
        val regex = Regex("[a-zA-ZáéíóúÁÉÍÓÚ]+")
        require(nombre.matches(regex)){"No has introducido un nombre válido, vuelve a probar:"}
        return true
    }

    fun introducirDesmarcacion(): TipoDemarcacion {
        var desmarcacion: TipoDemarcacion = TipoDemarcacion.DEFENSA
        when(menuDemarcaciones()){
            1 -> desmarcacion = TipoDemarcacion.DELANTERO
            2 -> desmarcacion = TipoDemarcacion.PORTERO
            3 -> desmarcacion = TipoDemarcacion.CENTROCAMPISTA
            4 -> desmarcacion = TipoDemarcacion.DEFENSA
        }
        return desmarcacion
    }

    fun menuDemarcaciones(): Int {
        var opcion = 0
        do {
            println("[1] delantero")
            println("[2] portero")
            println("[3] centrocampista")
            println("[4] defensa")
            opcion = readln().toInt()
            try {
                opcionValidaDemarcacion(opcion)
            }catch(e: Exception){
                println(e.message)
                opcion = -1
            }
        }while(opcion == -1)
        return opcion
    }

    fun opcionValidaDemarcacion(opcion: Int): Boolean {
        require(opcion in 1..4){"No has elegido una de las opciones permitidas, vuelva a probar:"}
        return true
    }

    fun mostrarTodos(jugadores: Array<Jugador?>) {
        //Esta parte es opcional, pero lo que hace es defragmentar el vector de alumnos.
        for (i in jugadores.indices) {
            if (jugadores[i] != null) {
                var j = i;
                if (j - 1 > 0 && jugadores[j - 1] == null) {
                    while (j - 1 > 0 && jugadores[j - 1] == null) {
                        j--;
                    }
                    jugadores[j] = jugadores[i];
                    jugadores[i] = null;
                }
            }
        }

        ordenarMetodoBurbujaAscendente(jugadores)

        for(i in jugadores.indices){
            if(jugadores[i] != null){
                println("En la posicion ${i+1} de la lista, tenemos a: ${jugadores[i]}")
            }
        }
        println()
    }

    fun ordenarMetodoBurbujaAscendente(jugadores: Array<Jugador?>): Array<Jugador?> {
        for(i in 0..jugadores.size - 1){
            for(j in 0..jugadores.size - 1 - i){
                if (j + 1 < jugadores.size) {
                    if(jugadores[j] != null && jugadores[j+1] != null) {
                        if (jugadores[j]?.dorsal ?: -1 > jugadores[j + 1]?.dorsal ?: -1) {
                            var auxiliar = jugadores[j]
                            jugadores[j] = jugadores[j + 1]
                            jugadores[j + 1] = auxiliar
                        }
                    }
                }
            }
        }
        return jugadores
    }

    fun menuJugadores(): Int {
        var opcion = 0
        do {
            println("[1] Mostrar todos los jugadores")
            println("[2] Mostrar jugador por número de dorsal")
            println("[3] Añadir un nuevo jugador a la plantilla")
            println("[4] Eliminar jugador del equipo")
            println("[5] Actualizar información del jugador")
            println("[6] Información equipo")
            println("[0] Salir")
            opcion = readln().toInt()
            try {
                opcionValidaJugadores(opcion)
            }catch(e: Exception){
                println(e.message)
                opcion = -1
            }
        }while(opcion == -1)
        return opcion
    }

    fun opcionValidaJugadores(opcion: Int): Boolean {
        require(opcion in 0..6){"No has elegido una de las opciones permitidas, vuelva a probar:"}
        return true
    }
}