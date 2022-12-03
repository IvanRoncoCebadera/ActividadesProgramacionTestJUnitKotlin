package CuentaBancaria

import ModelsCuentaBancaria.Cuenta
import ModelsCuentaBancaria.Dinero
import ModelsCuentaBancaria.Titular

fun main(){

    var cuenta: Cuenta? = Cuenta("Iván")

    cuenta!!.titulares[0] = Titular("53907934M", "Iván", "ivanrc2004@gmail.com")
    cuenta!!.dineroTitulares[0] = Dinero(250.0)

    println("Introduzca la opción que deseas realizar:")
    var opcion = 0
    do{
        opcion = cuenta!!.menu()
        when(opcion){
            1 -> cuenta!!.mostrarTodosLosTitulares(cuenta.titulares, cuenta.dineroTitulares)
            2 -> cuenta!!.mostrarTitularPorDni(cuenta.titulares, cuenta.dineroTitulares)
            3 -> cuenta!!.añadirTitular(cuenta.titulares, cuenta.dineroTitulares)
            4 -> cuenta!!.eliminarTitular(cuenta.titulares, cuenta.dineroTitulares)
            5 -> cuenta!!.cambiarInfoTitular(cuenta.titulares, cuenta.dineroTitulares)
            6 -> cuenta!!.informacion(cuenta.titulares, cuenta.dineroTitulares)
            7 -> cuenta!!.ingresarDineroTitular(cuenta.titulares, cuenta.dineroTitulares)
            8 -> cuenta!!.retirarDineroTitular(cuenta.titulares, cuenta.dineroTitulares)
        }
    }while(opcion != 0)
    println("Adios..")
}