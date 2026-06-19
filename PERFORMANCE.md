# Anexo Técnico de Rendimiento — EcoRide Pro

## 1. Por qué la nueva estructura de búsqueda es más rápida que la búsqueda lineal anterior

En la versión original, los vehículos se almacenaban en un `ArrayList<Vehiculo>`. Para encontrar un vehículo por su patente, el método `buscarVehiculo` recorría la lista elemento por elemento desde el principio hasta encontrar la coincidencia. En el peor caso (vehículo al final o no existente), se debían revisar **todos** los elementos. Este comportamiento se describe como complejidad **O(n)**: si hay 10.000 vehículos registrados, se realizan hasta 10.000 comparaciones por cada búsqueda.

En la versión nueva, los vehículos se almacenan en un `HashMap<String, Vehiculo>` donde la clave es la patente. Un `HashMap` utiliza internamente una función de hash que convierte la clave (la patente) en una posición directa dentro de una tabla. Esto permite ir exactamente a la celda donde está el vehículo sin recorrer nada. La complejidad es **O(1)**: ya sea que haya 10 o 100.000 vehículos, la búsqueda tarda el mismo tiempo constante.

## 2. Cómo el algoritmo de deduplicación de alertas GPS evita los bucles anidados

El enfoque ingenuo para eliminar duplicados consiste en tomar cada alerta de la lista y compararla contra todas las demás para ver si ya aparece. Esto produce dos bucles anidados y una complejidad **O(n²)**: con 10.000 alertas, se realizan hasta 100.000.000 comparaciones, bloqueando la CPU.

El algoritmo implementado en `GpsService.deduplicarAlertas()` realiza una **única pasada** sobre la lista. Se utiliza un `HashSet<String>` como registro de elementos ya vistos. Por cada alerta, se intenta insertar en el `HashSet` con el método `add()`, que devuelve `false` si el elemento ya existía. Si es nueva (devuelve `true`), se agrega al resultado. De este modo se recorre la lista exactamente una vez con complejidad **O(n)**, aprovechando que la búsqueda y la inserción en un `HashSet` son O(1) gracias al mismo mecanismo de hashing del `HashMap`.

## 3. Cómo se resolvió el ordenamiento natural sin interferir con el criterio de tarifa

En Java, una clase puede implementar `Comparable<T>` para definir su **orden natural intrínseco**, y al mismo tiempo puede ser ordenada por cualquier criterio externo mediante un `Comparator<T>` sin que ambos mecanismos se afecten entre sí.

La clase `Vehiculo` implementa `Comparable<Vehiculo>` y en su método `compareTo` compara los vehículos por nivel de batería de menor a mayor. Este es el criterio operativo principal (prioridad de carga) y queda encapsulado dentro del propio vehículo.

Para el criterio comercial (tarifa descendente) se creó la clase `ComparadorPorTarifa`, que implementa `Comparator<Vehiculo>` y compara por `tarifaBase` de mayor a menor. Este componente es completamente externo al vehículo.

Cuando se llama a `Collections.sort(lista)` sin argumentos, Java utiliza el `compareTo` del vehículo (batería). Cuando se llama a `Collections.sort(lista, new ComparadorPorTarifa())`, Java ignora el `compareTo` y usa el `Comparator` externo (tarifa). Ambas operaciones trabajan sobre una copia de la colección en memoria, por lo que ningún ordenamiento altera el estado interno de la flota ni el orden natural del otro. Los dos criterios conviven sin interferencia.
