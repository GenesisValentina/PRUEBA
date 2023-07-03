Clases y componentes importantes:
UserRegistrationApp: La clase principal que inicia la aplicación Spring Boot.
UserController: Controlador que maneja los endpoints relacionados con los usuarios.
UserRepository: Repositorio JPA para interactuar con la base de datos.
UserService: Interfaz del servicio para la lógica de negocio de usuarios.
UserServiceImpl: Implementación del servicio para la lógica de negocio de usuarios.
User: Entidad JPA que representa un usuario.
Phone: Entidad JPA que representa un teléfono.
UserRegistrationRequest: DTO para la solicitud de registro de usuario.
ErrorMessage: DTO para mensajes de error.
CustomErrorController: Controlador personalizado para manejar errores y retornar respuestas JSON.
ValidationUtils: Clase utilitaria para validaciones personalizadas.
Configuración y dependencias:
La aplicación utiliza una base de datos en memoria H2.
Se utiliza JPA con Hibernate como proveedor de persistencia.
Se configura Swagger para generar la documentación de la API.
Cómo probar la aplicación:
Clona el repositorio desde GitHub: User Registration App Repository
Asegúrate de tener Java 8+ y Gradle instalados en tu máquina.
Importa el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse).
La aplicación usa una base de datos H2 en memoria, por lo que no es necesario configurar una base de datos externa.
Ejecuta la aplicación desde tu IDE o utiliza el siguiente comando en la línea de comandos en el directorio raíz del proyecto:
bash
Copy code
./gradlew bootRun
La aplicación se ejecutará en http://localhost:8080.
Puedes utilizar herramientas como Postman o cURL para enviar solicitudes a los endpoints y probar la funcionalidad.
La documentación de la API generada por Swagger se encuentra en http://localhost:8080/swagger-ui.html.