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

        /**
         * función que sirve para introducir un titulo válido
         * @return el título introducido por teclado
         */
        fun introducirTitulo(): String {
            var titulo = ""
            do {
                try {
                    titulo = readln().trim()
                    tituloValido(titulo)
                } catch (e: Exception) {
                    println(e.message)
                    titulo = ""
                }
            } while (titulo == "")
            return titulo
        }

        /**
         * función que sirve para validar el título introducido por teclado
         * @param titulo lo que queremos validar
         * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
         * @return true en caso de que sea válido
         */
        fun tituloValido(titulo: String): Boolean {
            require(titulo != null) { ("El título no puede ser nulo, vuelve a probar:") }
            require(titulo.isNotEmpty()) { "El título de la peli no puede estar vacio, vuelve a probar:" }
            return true
        }

        /**
         * función que sirve para introducir un año de publicacion válido
         * @return el año de publicacion introducido por teclado
         */
        fun introducirAñoPublicación(): Int {
            var añoPublicacion = 0
            do {
                try {
                    añoPublicacion = readln().toInt()
                    añoPublicacionValido(añoPublicacion)
                } catch (e: Exception) {
                    println(e.message)
                    añoPublicacion = -1
                }
            } while (añoPublicacion == -1)
            return añoPublicacion
        }

        /**
         * función que sirve para validar el año de publicación introducido por teclado
         * @param añoPublicacion lo que queremos validar
         * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
         * @return true en caso de que sea válido
         */
        fun añoPublicacionValido(añoPublicacion: Int): Boolean {
            require(añoPublicacion != null) { ("El año de publicación no puede ser nulo, vuelve a probar:") }
            require(añoPublicacion > 0) { "El año de publicación no puede ser negativo, vuelve a probar:" }
            require(añoPublicacion in 1950..2022) { "El año de publicación de la peli debe ser entre 1950 y 2022, vuelve a probar:" }
            return true
        }

        /**
         * función que sirve para introducir un director válido
         * @return el director introducido por teclado
         */
        fun introducirDirector(): String {
            var director = ""
            do {
                try {
                    director = readln().trim()
                    directorValido(director)
                } catch (e: Exception) {
                    println(e.message)
                    director = ""
                }
            } while (director == "")
            return director
        }

        /**
         * función que sirve para validar el director introducido por teclado
         * @param director lo que queremos validar
         * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
         * @return true en caso de que sea válido
         */
        fun directorValido(director: String): Boolean {
            require(director != null) { ("El director no puede ser nulo, vuelve a probar:") }
            require(director.isNotEmpty()) { "El director de la peli no puede estar vacio, vuelve a probar:" }
            return true
        }

        /**
         * función que sirve para introducir un género válido
         * @return el género introducido por teclado
         */
        fun introducirGenero(): String {
            var genero = ""
            do {
                try {
                    genero = readln().trim()
                    generoValido(genero)
                } catch (e: Exception) {
                    println(e.message)
                    genero = ""
                }
            } while (genero == "")
            return genero
        }

        /**
         * función que sirve para validar el género introducido por teclado
         * @param genero lo que queremos validar
         * @throws IllegalArgumentException un mensaje de error en caso de que sea inválido
         * @return true en caso de que sea válido
         */
        fun generoValido(genero: String): Boolean {
            require(genero != null) { ("El género no puede ser nulo, vuelve a probar:") }
            require(genero.isNotEmpty()) { "El género de la peli no puede estar vacio, vuelve a probar:" }
            return true
        }
    }
}