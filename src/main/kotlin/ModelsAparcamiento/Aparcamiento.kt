package ModelsAparcamiento

class Aparcamiento {

    var contadorTotalCochesAparcados = 0

    var aparcamiento: Array<Array<Vehiculo?>> = Array(4){Array<Vehiculo?>(4){null}}

    private var vectorVehiculos: Array<Vehiculo?> = Array<Vehiculo?>(16){null}

    fun aparcar(){
        val posicionLibre: Pair<Int, Int> = buscarHuecoLibreDeAparcamiento()
        if(posicionLibre != Pair(-1, -1 )){
            println("Te toca introducir los datos del vehiculo a aparcar:")
            println("Introduce la matricula del vehiculo:")
            val matricula = aparcamiento[0][0]!!.introducirMatricula()
            println("Introduce el año de fabricación del vehiculo:")
            val añoFabricacion = aparcamiento[0][0]!!.introducirAñoFabricacion()
            println("Introduce el tipo del vehiculo:")
            val tipo = aparcamiento[0][0]!!.introducirTipo()
            println("Ahora introduzca la info del conductor:")
            println("Introduce el nombre del conductor:")
            val nombre = aparcamiento[0][0]!!.conductor.introducirNombre()
            println("Introduce el dni del conductor:")
            val dni = aparcamiento[0][0]!!.conductor.introducirDni()

            val nuevoVehiculo = aparcamiento[posicionLibre.first][posicionLibre.second]!!.create(matricula, añoFabricacion, tipo, aparcamiento[posicionLibre.first][posicionLibre.second]!!.conductor.create(nombre, dni))
            aparcamiento[posicionLibre.first][posicionLibre.second] = nuevoVehiculo
            vectorVehiculos[posicionLibre.first*4+posicionLibre.second] = nuevoVehiculo

            contadorTotalCochesAparcados++
        }else{
            println("No quedán espacios libres de aparcamiento")
        }
    }

    fun sacar(){
        println("Introduce la matricula del vehículo que desea sacar del aparcamiento:")
        val posicionVehiculo: Pair<Int, Int> = buscarVehiculoPorMatricula(aparcamiento[0][0]!!.introducirMatricula())
        if(posicionVehiculo != Pair(-1, -1)){
            println("Se ha sacado al vehiculo: ${eliminarVehiculo(posicionVehiculo)}, del aparcamiento.")
        }else{
            println("No hay ningún vehículo con esa matricula.")
        }
    }

    fun listadoDeVehiculos(){
        ordenardenarDescendentemente()

        println("Hay un total de ${cunatosVehiculosHay()} vehículos:")
        for(i in vectorVehiculos){
            if(i != null){
                println(i)
            }
        }
    }

    fun vehiculosDeUnConductor(){
        println("Introduzca el dni del conductor cuyos coches quiere buscar:")
        val dni = aparcamiento[0][0]!!.conductor.introducirDni()
        var contador = 0
        for(i in vectorVehiculos){
            if(i != null){
                if(i.conductor.dni == dni){
                    contador++
                }
            }
        }
        println("Ese conductor tiene un total de $contador vehículos aparcados en el aparcamiento")
    }

    fun calcularLaRecaudacion(){
        println("La recaudación total actual es de: ${String.format("%.2", contadorTotalCochesAparcados*3.75)}")
    }

    private fun ordenardenarDescendentemente() {
        for(i in 0..vectorVehiculos.size - 1){
            for(j in 0..vectorVehiculos.size - 1 - i){
                if(vectorVehiculos[j] != null && vectorVehiculos[j + 1] != null){
                    if(vectorVehiculos[j]!!.añoFabricacion <  vectorVehiculos[j + 1]!!.añoFabricacion){
                        val vehiculoAuxiliar = vectorVehiculos[j + 1]
                        vectorVehiculos[j + 1] = vectorVehiculos[j]
                        vectorVehiculos[j] = vehiculoAuxiliar
                    }
                }
            }
        }
    }

    private fun eliminarVehiculo(posicionVehiculo: Pair<Int, Int>): Vehiculo? {
        val vehiculoAuxiliar = aparcamiento[posicionVehiculo.first][posicionVehiculo.second]
        aparcamiento[posicionVehiculo.first][posicionVehiculo.second] = null
        vectorVehiculos[posicionVehiculo.first*4+posicionVehiculo.second] = null
        return vehiculoAuxiliar
    }

    private fun buscarVehiculoPorMatricula(matricula: String): Pair<Int, Int> {
        var posicion = Pair(-1,-1)
        for(i in aparcamiento.indices){
            for(j in aparcamiento[i].indices){
                if(aparcamiento[i][j] != null) {
                    if (aparcamiento[i][j]!!.matricula == matricula) {
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

    private fun buscarHuecoLibreDeAparcamiento(): Pair<Int, Int> {
        var posicion = Pair(-1,-1)
        for(i in aparcamiento.indices){
            for(j in aparcamiento[i].indices){
                if(aparcamiento[i][j] == null){
                    posicion = Pair(i, j)
                    break
                }
            }
            if(posicion != Pair(-1, -1)){
                break
            }
        }
        return posicion
    }

    fun cunatosVehiculosHay(): Int{
        var contadorVehiculos = 0
        for(i in aparcamiento.indices){
            for(j in aparcamiento[i].indices){
                if(aparcamiento[i][j] != null) {
                    contadorVehiculos++
                }
            }
        }
        return contadorVehiculos
    }

    fun menu(): Int {
        println("[1] aparcar un nuevo vehículo")
        println("[2] sacar un vehículo del aparcamiento")
        println("[3] mostrar un listado de vehiculos")
        println("[4] cuantos vehículos aparcados tiene un conductor")
        println("[5] calcular recaudación total")
        println("[0] salir")
        var opcion = -1
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

    private fun opcionValida(opcion: Int) {
        require(opcion in 0..5) { "La opción seleccionada es inválida, vuelva a probar:" }
    }

}