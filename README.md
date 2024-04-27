## PR1

## Author
- #name
- #email

## Introducción

## Desarrollo
**Modelos**

Lo primero que he hecho en este proyecto es crear los modelos en la carpeta **src/main/java/uoc/ds/pr/model**.
Los modelos que he implementado son:
+ Client
+ Ship
+ Route
+ Voyage
+ Reservation
+ ParkingReservation

Cada modelo tiene sus atributos, constructor, getters y setters.

**TADs**

Después he creado las estructuras de datos DSArray, DSLinkedList y OrderedVector en la carpeta **src/main/java/uoc/ds/pr/util**.

Para la clase de DSArray me he basado en la clase DictionaryArrayImpl de la librería.
He utilizado DSArray para guardar los buques (*Ship*) y trayectos (*Route*).

Para la clase DSLinkedList he extendido de la clase LinkedList de la librería.
He utilizado DSLinkedList para guardar los clientes (*Client*) y las travesías (*Voyage*).
También he añadido estas listas encadenadas en la clase *Voyage* para guardar las reservas (*Reservation*) de una travesía, 
en la clase...

Para la clase OrderedVector he mirado el test OrderedVectorTest y he implementado los métodos de la interfaz Fininte Container.
He utilizado OrderedVector para...

Para la clase FiniteLinkedList he mirado el test FiniteLinkedListTest y he extendido de la clase LinkedList y he implementado solo el método isFull() de la interfaz FiniteContainer, ya que el resto de métodos ya están en la superclase.

**ShippingLineImpl**

Los métodos addShip() y addRoute() consiste en simplemente crear el objeto y llamar al método put() para guardar el objeto mediante clave (id) y valor (objeto).

El método addClient() consiste en crear el objeto y añadirlo al final de la lista encadenada con el método insertEnd().

El método addVoyage() consiste en crear el objeto, buscar el buque y la ruta, y añadirlo al final de la lista encadenada con el método insertEnd().

El método reserve() consiste en crear el objeto, buscar los clientes y travesía por los IDs, y añadirlo a las listas encadenadas de las reservas de travesías y clientes, y también a la FiniteLinkedList de la acomodación que toque.

El método getClientReservations() primero obtiene el cliente por el id, después llama al método getReservations() del cliente y por último a values() para obtener el iterador que es lo que se devuelve.

El nétodo getVoyageReservations() primero obtiene la travesía por el id, después llama al método getReservations() de la travesía y por último a values() para obtener el iterador que el lo que se devuelve.


Los métodos getShip() y getRoutes() llaman al método get() de DSArray para obtener el objeto con ese id. Si no se encuentra devuelve null.

Los métodos getClient() y getVoyage() utilizan un bucle while para buscar en el iterador el objeto con ese id. Si no se encuentra devuelve null.

Los métodos numShips(), numRoutes(), numClients(), numVoyages() obtienen el número total de estos llamando al método size() de DSArray.
