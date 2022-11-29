package AlumnoClaseMax30

import Models.AlumnosMax30
    fun main(){

        var clase = Array<AlumnosMax30?>(30) { null }

        clase[0] = AlumnosMax30("53986048A", "Iván", 5.89)
        clase[1] = AlumnosMax30("53364648D", "Manuel", 10.0)
        clase[2] = AlumnosMax30("26486048G", "Belén", 2.45)
        clase[3] = AlumnosMax30("52928599F", "Cristobal", 0.34)
        clase[6] = AlumnosMax30("53739520Z", "Mario", 7.6524)

        do {
            var opcion = 0
            do {
                opcion = menu()
            } while (opcion == -1)
            when (opcion) {
                1 -> clase = leerTodos(clase)
                2 -> buscarAlumnoPorDNI(clase)
                3 -> clase = crear(clase)
                4 -> clase = actualizar(clase)
                5 -> clase = borrar(clase)
                6 -> informar(clase)
            }
        }while(opcion != 0)
        println("Cerrando programa....")
        Thread.sleep(2_546)
        println("Hasta la proxima.")
    }

    fun buscarAlumnoPorDNI(clase: Array<AlumnosMax30?>) {
        var caso = leerPorDNI(clase, introducirDNI())-1
        if (caso == -1) {
            println("No se ha encontrado ningun alumno con el DNI proporcionado")
        } else {
            println("Se ha encontrado al alumno: ${clase[caso - 1]}, es el de la posicion $caso de la lista")
        }
        println()
    }

    fun informar(clase: Array<AlumnosMax30?>){
        var nAlumnos = 0
        var aprobados = 0
        var suspendidos = 0
        for(i in clase.indices){
            if(clase[i] != null){
                nAlumnos++
                if(clase[i]!!.estaAprobado){
                    aprobados++
                }else{
                    suspendidos++
                }
            }
        }
        println("Hay un total de $nAlumnos alumnos, $aprobados están aprobados y $suspendidos están suspendidos")
        println()
    }

    fun borrar(clase: Array<AlumnosMax30?>): Array<AlumnosMax30?> {
        var pos = leerPorDNI(clase, introducirDNI())-1
        if(pos == -1){
            println("No existe alumno con ese DNI")
        }else{
            println("Has a borrar al alumno: ${clase[pos]}")
            clase[pos] = null
        }
        println()
        return clase
    }

    fun actualizar(clase: Array<AlumnosMax30?>): Array<AlumnosMax30?> {
        var pos = leerPorDNI(clase, introducirDNI())-1
        if(pos == -1){
            println("No existe alumno con ese DNI")
        }else{
            println("Vas a modificar al alumno: ${clase[pos]}")
            clase[pos] = AlumnosMax30(dni = introducirDNI(), nombre = introducirNombre(), calificacion = introducirCalificacion())
        }
        return clase
    }

    fun crear(clase: Array<AlumnosMax30?>): Array<AlumnosMax30?> {

        var posicionLibre = primerNull(clase)

        if(posicionLibre == -1){
            println("No hay posiciones libres donde meter a más alumnos, primero borre y después vuelva a probar")
        }else {
            clase[posicionLibre] = AlumnosMax30(dni = introducirDNI(), nombre = introducirNombre(), calificacion = introducirCalificacion())
        }
        return clase
    }

    fun primerNull(clase: Array<AlumnosMax30?>): Int {
        for(i in clase.indices){
            if(clase[i] == null){
                return i
            }
        }
        return -1
    }

    fun introducirCalificacion(): Double {
        var calificacion = 0.0
        do {
            try {
                println("Introduzca el calificacion del alumno:")
                calificacion = readln().toDouble()
                calificacionValida(calificacion)
            } catch (e: Exception) {
                println(e.message)
                calificacion = -1.0
            }
        } while (calificacion == -1.0)
        return calificacion
    }

    fun introducirNombre(): String {
        var nombre = ""
        do {
            try {
                println("Introduzca el nombre del alumno:")
                nombre = readln()
                nombreValido(nombre)
            } catch (e: Exception) {
                println(e.message)
                nombre = ""
            }
        } while (nombre == "")
        return nombre
    }

    fun introducirDNI(): String {
        var dni = ""
        do {
            try {
                println("Introduzca el DNI del alumno:")
                dni = readln()
                DNIValido(dni)
            } catch (e: Exception) {
                println(e.message)
                dni = ""
            }
        } while (dni == "")
        return dni
    }

    fun calificacionValida(calificacion: Double): Boolean {
        require(calificacion in (0.0..10.0)){"La calificacion, no puede ser menor que 0, ni mayor que 10"}
        return true
    }

    fun nombreValido(nombre: String): Boolean {
        require(nombre.isNotEmpty()){"El nombre no puede estar vacio"}
        return true
    }

    fun leerPorDNI(clase: Array<AlumnosMax30?>, dni: String): Int {
        for(i in clase.indices){
            if(clase[i] != null){
                if(clase[i]!!.dni == dni){
                    return i+1
                }
            }
        }
        return -1
    }

    fun DNIValido(dni: String): Boolean {
        val regex = Regex("[0-9]{8}[A-Z]")
        require(dni.matches(regex)){"No has introducido un dni válido"}
        return true
    }

    fun leerTodos(clase: Array<AlumnosMax30?>): Array<AlumnosMax30?> {

        //Esta parte es opcional, pero lo que hace es defragmentar el vector de alumnos.
        for(i in clase.indices){
            if(clase[i] != null) {
                var j = i
                if(j - 1 > 0 && clase[j - 1] == null) {
                    while (j - 1 > 0 && clase[j - 1] == null) {
                        j--
                    }
                    clase[j] = clase[i]
                    clase[i] = null
                }
            }
        }

        ordenarMetodoBurbujaDescendente(clase)

        for(i in clase.indices){
            if(clase[i] != null){
                println("En la posicion ${i+1} de la lista, tenemos a: ${clase[i]}")
            }
        }
        println()

        return clase
    }

    fun ordenarMetodoBurbujaDescendente(clase: Array<AlumnosMax30?>): Array<AlumnosMax30?> {
        var auxiliar: AlumnosMax30? = clase[0]
        for (i in (0..clase.size - 1)) {
            for (j in (0..clase.size - i - 1)) {
                if (clase[j] != null && clase[j + 1] != null) {
                    if (clase[j + 1]!!.calificacionRedondeada > clase[j]!!.calificacionRedondeada) {
                        auxiliar = clase[j]
                        clase[j] = clase[j + 1]
                        clase[j + 1] = auxiliar
                    }
                }
            }
        }
        return clase
    }

    fun menu(): Int {
        var opcion = 0
        println("Eliga cual de las siguientes acciones quiere realizar:")
        println()
        println("[1] Leer todos los alumnos disponibles")
        println("[2] Leer un alumno según su DNI")
        println("[3] Crear alumno")
        println("[4] Actualizar datos alumno")
        println("[5] Borrar datos alumno")
        println("[6] Informacion")
        println("[0] Salir")
        return introducirOpcion(opcion)
    }

    fun introducirOpcion(opcion: Int): Int {
        var opcion1 = opcion
        do {
            try {
                opcion1 = readln().toInt()
                opcionValida(opcion1)
            } catch (e: Exception) {
                println(e.message)
                opcion1 = -1
            }
        } while (opcion1 == -1)
        return opcion1
    }

    fun opcionValida(opcion1: Int): Boolean {
        require(opcion1 in (0..6)) { "No has elegido una de las opciones posibles" }
        return true
    }