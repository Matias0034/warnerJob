PRUEBA TÉCNICA

PASOS PARA EJECUTAR EL PROJECTO


Compilar e instalar dependencias
1 ./gradlew build 

Ejecutar el projecto, asegurarse que el puerto usado esté disponible 8080
2 ./gradlew bootRun


Una vez el projecto andando deberán irse a la documentación de swagger o bien si es de su preferencia probarlo en postman
http://localhost:8080/swagger-ui/index.html

El projecto tiene 3 endpoint

Crear usuario
Obtener Usuario
Actualizar usuario

Cuando creas un usuario recibirás un token jwt, será necesario para poder consultar el resto de endpoints
