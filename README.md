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

**ShippingLineImpl**

Los métodos addShip() y addRoute() simplemente crear el objeto y llamar al método put() para guardar el objeto mediante clave (id) y valor (objeto).

Los métodos addClient() y addVoyage() es crear el objeto y añadirlo al final de la lista encadenada con el método insertEnd().

