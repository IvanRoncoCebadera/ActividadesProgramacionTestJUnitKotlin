package ModelsSalaDeCine

data class butaca(val identificador: String, var estado: String) {

    var esVip: Boolean = false

    init{
        var probabilidad = 0
        /**
         * función que sirve para decidir si una butaca es vip o no, al momento en el que se crea
         */
        fun seraVip(){
            probabilidad = (1..100).random()
            if(probabilidad in 1..40){
                esVip = true
            }
        }
        seraVip()
    }

    /**
     * función que sirve para crear y devolver una butaca
     * @param indentificador es la combinación de fila y columna única de la butaca
     * @param estado es el estado de la butaca, puede ser libre, reservado, o ocupado
     * @return la butaca creada según los parametros introducidos
     */
    fun create(identificador: String, estado: String): butaca{
        return butaca(identificador, estado)
    }
}