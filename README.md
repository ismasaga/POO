# POO
El repositorio oficial de POO. Hay dos paquetes:
* Juego: incluye el *main* (en la clase **main**) del juego, así como las clases **Enemigo**, **Mapa**, **Personaje**, **Celda** y **Mochila**.
* Objetos: incluye los objetos del juego, las clases

En caso de que el *Javadoc* sea insuficiente, ver **Referencia**.

-------------------------------------------------------------------------------
Referencia
---------------------------------------------

En el paquete **Juego** se detallan las siguientes clases:
* **Enemigo**: instancia un enemigo del juego.
  * Atributos:
    * puntosVida: puntos de vida del enemigo
    * ataque: ataque del enemigo
    * armadura: armadura del enemigo
  * Métodos:
    * atacar(Personaje): ataca al personaje especificado (sin crítico).   
* **Mapa**: instancia un mapa del juego con sus correspondientes celdas.
  * Atributos:
    * ancho: ancho del mapa
    * alto: alto del mapa
    * celda[]: arraylist de celdas
  * Métodos:
    * null
* **Personaje**: instancia un nuevo personaje protagonista.
  * Atributos:
    * MAXIMO_VIDA: maximo de vida del personaje (constante)
    * puntosVida: puntos de vida del personaje.
    * armadura: armadura del personaje
    * Celda: celda de la posición actual del personaje.
    * Mochila: mochila del personaje
    * rangoVisión: máximo de casillas observables por el personaje.
    * ataque: ataque del personaje.
    * energía: energía del personaje (cuando se agota no se pueden realizar más acciones).
  * Métodos:
    * atacar(Enemigo): ataca al enemigo especificado
    * tirarBinocular(Binoculares): tira el binocular especificado en la celda actual
    * cogerBinocular(Binoculares): coge el binocular especificado de la celda actual
    * tirarBotiquin(Botiquin): tira el binocular especificado en la celda actual
    * cogerBotiquin(Botiquin): coge el botiquin especificado de la celda actual
    * ojearInventario(): imprime todos los objetos de la mochila.
    * mover(Celda): cambia la celda actual del personaje por la especificada en el parámetro.
    * mirar(): imprime los objetos de la celda actual.

* **Celda**: instancia una celda que pertenece a un mapa.
  * Atributos:
    * arrayBinoculares[]: arraylist de los binoculares presentes en la celda.
    * arrayBotiquin[]: arraylist de los botiquines presentes en la celda.
    * transitable: indica si una celda es transitable (*true*) o no (*false*).
  * Métodos:
    * eliminarBinocular(Binoculares): elimina el binocular especificado de la celda.

* **Mochila**: instancia una nueva mochila del personaje.
  * Atributos:
    * pesoMaximo: peso máximo de la mochila (constante).
    * objetosMaximos: número máximo de objetos que puede tener la mochila (constante).
    * arrayBinoculares[]: arrayList de todos los binoculares de la mochila.
    * arrayBotiquin[]: arrayList de todos los botiquines de la mochila
    * pesoActual: peso de la mochila en un momento determinado.
    * objetosActuales: objetos de la mochila en un momento dado.

  * Métodos:
    * anadirBinocular(Binoculares): añade un binocular a la mochila.
    * anadirBotiquin(Botiquin): añade un botiquín a la mochila.
    * quitarBinocular(Binoculares): elimina un binocular de la mochila.
    * quitarBotiquin(Botiquin): elimina un botiquin de la mochila.
-------------------------------------------------------------------------------

En el paquete **Objetos** se detallan las siguientes clases:
* **Binoculares**: instancia un nuevo binocular; aumenta las celdas que puede ver el personaje.
  * Atributos:
    * vision: aumento de visión que proporciona el binocular (constante).
    * peso: peso del binocular (relevante para la mochila) (constante).
    * espacio: espacio del binocular (relevante para la mochila) (constante).
  * Métodos:
    * usar(Personaje): usa el binocular en el personaje, aumentando su rango de visión en *vision*.
    * disipar(Personaje): restaura el rango de visión original del personaje (se llamaría a este método al final de cada turno si se hubiese llamado a usar).

* **Botiquin**: instancia un nuevo botiquin; cura a un personaje.
  * Atributos:
    * curacion: puntos de vida que restaura al personaje
    * peso: peso del botiquín
    * espacio: espacio que ocupa el botiquín
  * Métodos:
    * usar(Personaje): cura una determinada cantidad de puntos de vida al Personaje especificado.
