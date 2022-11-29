
fun main(){

    var cantDineroCuenta = ""
    do {
        println("Debes introducir el saldo que tienes en cuenta actualmente, ten encuenta que debe tener de uno a dos decimales:")
        cantDineroCuenta = readln()
    }while(validarSaldo(cantDineroCuenta) == false)
    var saldo = cantDineroCuenta.toDouble()

    var caso = 0
    do {
        caso = menu()
        if (caso == 1) {
            var cantDinero = ""
            do {
                println("Ingrese la cantidad de dinero que quiere enviar:")
                cantDinero = readln()
            }while(validarDinero(cantDinero, saldo) == false)
            var dinero: Double = cantDinero.toDouble()
            saldo = enviar(dinero, saldo)
        } else {
            if (caso == 2) {
                var cantDinero = ""
                do {
                    println("Ingrese la cantidad de dinero que quiere recibir:")
                    cantDinero = readln()
                }while(validarDinero(cantDinero, saldo) == false)
                var dinero: Double = cantDinero.toDouble()
                saldo = recibir(dinero, saldo)
            } else {
                if (caso == 3) {
                    println(verEstado(saldo))
                }
            }
        }
    }while(caso != 0)
    println("Cerrando la aplicacion...")
    Thread.sleep(2_500)
    println("Programa cerrado. Hasta pronto.")

}

fun menu(): Int{
    var opcion = 0
    do {
        println("[1] Enviar")
        println("[2] Recibir")
        println("[3] Ver estado")
        println("[0] Salir")
        opcion = readln().toInt()
    }while(validarOpcion(opcion) == false)
    return opcion
}

fun validarOpcion(opcion: Int): Any {
    return if(opcion in (0..3)){
        true
    }else{
        false
    }
}

fun enviar(dinero: Double, saldo: Double): Double{
    var res = saldo - dinero
    return res
}

fun recibir(dinero: Double, saldo: Double): Double{
    var res = dinero + saldo
    return res
}

fun verEstado(saldo: Double): String{
    return "Tienes en cuenta, un total de $saldo euros."
}

fun validarSaldo(saldo: String): Boolean{
    val regex = """[0-9]+.[0-9]{1,2}""".toRegex()
    if(!regex.matches(saldo)) {
        println("El saldo introducido, no cumple con los parámetros necesarios.")
        return false
    }
    if(saldo.toDouble() < 0.50) {
        println("El saldo introducido no puede ser menor que 0.50 euros.")
        return false
    }
    return true
}

fun validarDinero(dinero: String, saldo: Double): Boolean{
    val regex = """[0-9]+.[0-9]{1,2}""".toRegex()
    if(!regex.matches(dinero)) {
        println("El dinero introducido, no cumple con los parámetros necesarios.")
        return false
    }
    if(dinero.toDouble() > saldo) {
        println("El dinero introducido no puede ser mayor que el saldo, el cual son $saldo euros.")
        return false
    }
    if(dinero.toDouble() < 0.00 || dinero.toDouble() > 500.00) {
        println("El dinero introducido no puede ser menor que 0.00 euros, ni mayor que 500.00 euros.")
        return false
    }
    return true
}