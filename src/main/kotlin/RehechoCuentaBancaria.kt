import RehechoModelsCuentaBancaria.RehechoCuenta

fun main(){

    var cuenta = RehechoCuenta("674683vjnhF4", 200.00)

    println("Introduzca la opción que deseas realizar:")
    var opcion = 0
    do{
        opcion = cuenta!!.menu()
        when(opcion){
            1 -> cuenta.mostrarTodosLosTitulares()
            2 -> cuenta.mostrarTitularSegunDni()
            3 -> cuenta.añadirNuevoTitular()
            4 -> cuenta.eliminarTitularDeLaCuenta()
            5 -> cuenta.cambiarInformacionTitularDeLaCuenta()
            6 -> cuenta.informacion()
            7 -> cuenta.introducirDineroEnCuenta()
            8 -> cuenta.sacarDineroDeCuenta()
        }
    }while(opcion != 0)
    println("Adios..")
}