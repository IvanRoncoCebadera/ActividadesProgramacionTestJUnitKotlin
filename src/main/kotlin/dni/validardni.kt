package dni

fun main(){
    val dni = "53907934M".uppercase()
    val longitud = 9
    val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
    when(comprobarPatronDNI(dni)){
        true -> println("El dni cumple con el patron adecuado")
        false -> println("El dni no cumplecon el patron adecuado")
    }
    when(comprobarLongitud(dni, longitud)){
        true -> println("El dni cumple con la longitud adecuada")
        false -> println("El dni no cumplecon la longitud adecuada")
    }
    when(comprobarLetra(dni, letras)){
        true -> println("El dni tiene una letra adecuada")
        false -> println("El dni no no tiene una letra adecuada")
    }
}

fun comprobarLetra(dni: String, letras: String): Any {
    var numero = substring(0, 7, dni)
    var resultado = restasSucesivas(numero)
    return if(dni[8] == letras[resultado]){
        true
    }else{
        false
    }
}

fun comprobarLongitud(dni: String, longitud: Int): Any {
    return if(dni.length == longitud){
        true
    }else{
        false
    }
}

fun comprobarPatronDNI(dni: String): Any {
    val regex = Regex("[0-9]{8}[A-Z]")
    return if(regex.matches(dni)){
        true
    }else{
        false
    }
}

fun substring(i1: Int, i2: Int, dni: String): Int {
    var mensaje = StringBuilder()
    for(i in i1..i2){
        mensaje.append("${dni[i]}")
    }
    return mensaje.toString().toInt()
}

fun restasSucesivas(dni: Int): Int {
    var resultado = dni
    while(resultado >= 23){
        resultado -= 23
    }
    return resultado
}
