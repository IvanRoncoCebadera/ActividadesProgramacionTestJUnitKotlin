package carrito_compra

const val NOMBRE = 0
const val PRECIO_UNI = 1
const val CANT = 2
const val PRECIO_FINAL = 3
const val TIPO_IVA = 4
const val PRECIO_FINAL_IVA = 5


fun main(){
    var carritoCompra: Array<Array<String>> = arrayOf(
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00"),
        arrayOf("", "0.00", "0", "0.00", "0", "0.00")
    )

    carritoCompra = llenarCarritoCompra(carritoCompra)
    println()
    calculosFinales(carritoCompra)

}

fun calculosFinales(carritoCompra: Array<Array<String>>) {
    var totalSinIVA: String = calcularTotalSinIVA(carritoCompra)
    println("El total de los productos comprados, sin incluir el IVA es de: ${totalSinIVA} euros.")
    var totalConIVA: String = calcularTotalConIVA(carritoCompra)
    println("El total de los productos comprados, incluyendo el IVA es de: ${totalConIVA} euros.")
}

fun calcularTotalConIVA(carritoCompra: Array<Array<String>>): String {
    var res = 0.0
    for(i in carritoCompra.indices){
        res = res + carritoCompra[i][PRECIO_FINAL_IVA].toDouble()
    }
    return res.toString()
}

fun calcularTotalSinIVA(carritoCompra: Array<Array<String>>): String {
    var res = 0.0
    for(i in carritoCompra.indices){
        res = res + carritoCompra[i][PRECIO_FINAL].toDouble()
    }
    return res.toString()
}

private fun llenarCarritoCompra(
    carritoCompra: Array<Array<String>>,
): Array<Array<String>> {
    var numeroProductos = 0
    var tamañoCarro = 10
    do {
        insertarNombre(carritoCompra, numeroProductos)

        insertarPrecioUnitario(carritoCompra, numeroProductos)

        tamañoCarro = insertarCantidad(tamañoCarro, carritoCompra, numeroProductos)

        carritoCompra[numeroProductos][PRECIO_FINAL] = calcularPrecioFinal(carritoCompra, numeroProductos)

        insertarTipoIVA(carritoCompra, numeroProductos)

        carritoCompra[numeroProductos][PRECIO_FINAL_IVA] = calcularPrecioFinalIVA(carritoCompra, numeroProductos)

        numeroProductos++
        println("Por ahora su carrito de la compra se ve asi:")
        representarMatriz(carritoCompra)
        println("Te queda un total de $tamañoCarro espacios para añadir objetos.")
    } while (tamañoCarro > 0)
    println("Ya no te queda espacio para añadir objetos al carrito.")

    return carritoCompra
}

private fun insertarTipoIVA(carritoCompra: Array<Array<String>>, numeroProductos: Int) {
    do {
        var tipoIVA: String = menuTipoIVA()
        try {
            esValidoTipoIVA(tipoIVA)
            carritoCompra[numeroProductos][TIPO_IVA] = tipoIVA
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }while(tipoIVA.toInt() !in (1..3))
}

private fun insertarCantidad(
    tamañoCarro: Int,
    carritoCompra: Array<Array<String>>,
    numeroProductos: Int
): Int {
        var tamañoCarro1 = tamañoCarro
    var cantidad = 0
    do {
        try {
            println("Inserte la cantidad de producto, que metera en el carrito:")
            cantidad = readln().toInt()
            require(
                esValidoCantidad(
                    cantidad,
                    tamañoCarro1
                )
            ) { "La cantidad debe ser mayor o igual que 1 y menor o igual que el tamaño de carro restante que es: $tamañoCarro1" }
            tamañoCarro1 -= cantidad
            carritoCompra[numeroProductos][CANT] = cantidad.toString()
        } catch (e: java.lang.Exception) {
            println("Error: ${e.message}")
        }
    }while(cantidad !in (1..tamañoCarro))
    return tamañoCarro1
}

private fun insertarPrecioUnitario(carritoCompra: Array<Array<String>>, numeroProductos1: Int) {
    var precioUni = 0.0
    do {
        try {
            println("Inserte el precio unitario del producto:")
            precioUni = readln().toDouble()
            require(esValidoPrecioUnitario(precioUni)) { "El precio unitario debe ser mayor que 0.1 euros." }
            carritoCompra[numeroProductos1][PRECIO_UNI] = precioUni.toString()
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }while(precioUni < 0.1)
}

private fun insertarNombre(carritoCompra: Array<Array<String>>, numeroProductos1: Int) {
    try {
        println("Inserte el nombre del producto:")
        var nombre = readln()
        require(esValidoNombre(nombre))
        carritoCompra[numeroProductos1][NOMBRE] = nombre
    }catch (e:Exception){
        println("Error: ${e.message}")
    }
}

fun representarMatriz(carritoCompra: Array<Array<String>>) {
    var mensaje = ""
    for(i in carritoCompra.indices){
        for(j in carritoCompra[i].indices){
            mensaje = "$mensaje    ${carritoCompra[i][j]}"
        }
        println(mensaje)
        mensaje = ""
    }
}

fun calcularPrecioFinalIVA(carritoCompra: Array<Array<String>>, numeroProductos: Int): String{
    var cant = carritoCompra[numeroProductos][CANT].toDouble()
    var precio = carritoCompra[numeroProductos][PRECIO_UNI].toDouble()
    var tipoIVA = carritoCompra[numeroProductos][TIPO_IVA]
    var res = 0.00
    if(tipoIVA == "1"){
        res = (cant * precio) + (cant * precio * 0.21)
    }else{
        if(tipoIVA == "2"){
            res = (cant * precio) + (cant * precio * 0.1)
        }else{
            res = (cant * precio)
        }
    }
    var res2 = (res * 100).toInt()
    res = (res2.toDouble() / 100)
    return res.toString()
}

fun calcularPrecioFinal(carritoCompra: Array<Array<String>>, numeroProductos: Int): String{
    var cant = carritoCompra[numeroProductos][CANT].toDouble()
    var precio = carritoCompra[numeroProductos][PRECIO_UNI].toDouble()
    var res = cant * precio
    var res2 = (res * 100).toInt()
    res = (res2.toDouble() / 100)
    return res.toString()
}

fun esValidoTipoIVA(tipoIVA: String): Boolean {
    var IVA = tipoIVA.toInt()
    require(IVA in 1..3){"Esa opcion de IVA no existe."}
    return true
}

fun menuTipoIVA(): String{
    println("Seleccione el tipo de IVA del producto:")
    println()
    println("[1] IVA completo")
    println("[2] IVA reducido")
    println("[3] sin IVA")
    var tipoIVA = readln()
    return tipoIVA
}


fun esValidoCantidad(cantidad: Int, tamañoCarro: Int): Boolean {
    require(cantidad in 1..10){"La cantidad no se encuentra entre 1 y 10"}
    require(cantidad <= tamañoCarro){"La cantidad no puede ser mayor que la cantidad de productos restantes que podemos meter al carro"}
    return true
}

fun esValidoPrecioUnitario(precioUni: Double): Boolean {
    val regex = Regex("[0-9]+.[0-9]{1,2}")
    require(regex.matches(precioUni.toString())){"Ese precio unitario no cumple con el patrón necesario, debe tener como mínimo un decimal y como máximo 2 decimales"}
    require(precioUni >= 0.1){"No puede existir un precio unitario menor a 0.1 euros"}
    return true
}

fun esValidoNombre(nombre: String): Boolean {
    val regex = Regex("([a-zA-Z]+)")
    require(regex.matches(nombre)){"El nombre no puede tener más que letras y números"}
    return true
}