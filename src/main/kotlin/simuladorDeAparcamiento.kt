import ModelsAparcamiento.Aparcamiento
import ModelsAparcamiento.Conductor
import ModelsAparcamiento.TipoVehiculo
import ModelsAparcamiento.Vehiculo

fun main() {
    val aparcamiento = Aparcamiento()
    aparcamiento.aparcamiento[0][0] = Vehiculo("7493-AAA", 2020, TipoVehiculo.coche, Conductor("Iván", "53907934M"))
    aparcamiento.contadorTotalCochesAparcados++
    var opcion = 0
    do {
        println("Seleccione la opción que deseé:")
        opcion = aparcamiento.menu()
        when (opcion) {
            1 -> aparcamiento.aparcar()
            2 -> aparcamiento.sacar()
            3 -> aparcamiento.listadoDeVehiculos()
            4 -> aparcamiento.listadoDeVehiculos()
            5 -> aparcamiento.calcularLaRecaudacion()
        }
    } while (opcion != 0)
    println("Adios..")
}