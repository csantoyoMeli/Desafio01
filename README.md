# Primer desafío del IT Bootcamp

Desarrollado por Cristian David Santoyo Parra para el IT Bootcamp

## Aclaraciones para hacer el testeo del bonus

En mi caso, la clase StatusCode posee 3 atributos: code, title y message. En esencia es lo mismo pero decidí agregarle el title en virtud aprovechar esa clase para retornar respuestas cuando salta una excepción.
- Punto 8: Simplemente se realiza una petición como en el punto 7 y se verifica si el archivo dbProductos.csv en la carpeta target se ha modificado. Posteriormente, se puede comprobar si la cantidad pedida es mayor a la disponible en stock y no permitirá la compra.
- Punto 9: Para esta parte, se puede consultar el carrito de compras a través de una petición GET como la siguiente: "http://localhost:8080/api/v1/market-car". Cada vez q se realiza una compra, se registra un nuevo ticket en un archivo llamado dbTickets.csv. El carrito de compra consulta esos datos desde ese archivo.
- Punto 10: Se realiza la petición POST con la siguiente dirección "http://localhost:8080/client/addClient", se añadirá un nuevo cliente en el archivo dbClients.csv con las respectivas restricciones en caso tal de que algún dato o parametro sea incorrecto. El idCLient no es obligatorio, en caso tal de no especificar un cleinte, se creará un id aleatorio pero se verificará si el nombre y edad son distintos a los ya registrados; en cuyo caso, no permitirá ahcer el registro.
- Punto 11: Se realiza a través de una petición GET con la siguiente dirección "http://localhost:8080/client/clients".
- Punto 12: Se realiza a través de una petición GET con la siguiente dirección "http://localhost:8080/client/clients?province=nameProvince&age=ageInt&name=clientName". Se puede realizar el filtro por provincia  y los demás atributos sin limite alguno. Si se especifica un parametro erronéo, debería retornar un error.