package ModelsSalaDeCine

data class pelicula(val titulo: String, val añoPublicacion: Int, val director: String, val genero: String) {

    companion object {
        /**
         * función que sirve para crear y devolver una pelicula
         * @param titulo es el título de la peli a crear
         * @param añoPublicacion es el año en que se publicó la peli a crear
         * @param director es el nombre del director que creó la peli
         * @param genero es el genero de la peli a crear
         * @return la pelicula creada según los parametros introducidos
         */
        fun create(titulo: String, añoPublicacion: Int, director: String, genero: String): pelicula {
            return pelicula(titulo, añoPublicacion, director, genero)
        }
    }
}