# JavaSpringWebMovieTrailerApp
¡Bienvenido al Organizador de Películas! Esta aplicación web te permite gestionar tus películas de manera organizada, almacenando la información en una base de datos. Puedes agregar nuevas películas, editar las existentes y eliminar aquellas que ya no desees conservar. Además, puedes ver los detalles de cada película, como su portada, género, sinopsis, fecha de estreno y ver su tráiler.

## Tecnologías utilizadas
La aplicación ha sido desarrollada utilizando las siguientes tecnologías y herramientas:

* Java 17: El lenguaje de programación principal utilizado para desarrollar la aplicación.

* Spring Boot: Un framework de desarrollo de aplicaciones Java que simplifica la configuración y el desarrollo de aplicaciones basadas en Spring.

* Spring MVC: El módulo de Spring que proporciona soporte para el patrón de arquitectura Modelo-Vista-Controlador (MVC) y permite el desarrollo de aplicaciones web.

* Spring Data JPA: Un módulo de Spring que simplifica el acceso y la manipulación de bases de datos utilizando el estándar de mapeo objeto-relacional (ORM) JPA.

* SQL Server: Un sistema de gestión de bases de datos relacional utilizado para almacenar y gestionar los datos de las películas en la aplicación.

* HTML, CSS y Bootstrap: Tecnologías web utilizadas para crear la interfaz de usuario atractiva y receptiva de la aplicación.

* Thymeleaf: Un motor de plantillas utilizado para generar vistas HTML dinámicas en la aplicación.

Estas tecnologías y herramientas se han combinado de manera efectiva para desarrollar una aplicación web robusta y funcional para la gestión de películas.

## Estructura de carpetas y archivos principales
A continuación se describe la estructura de carpetas y los archivos principales utilizados en el proyecto:

* .mvn: Directorio que contiene archivos relacionados con Maven.
  
* .vscode: Directorio que contiene archivos de configuración específicos de Visual Studio Code.
  
* assets: Directorio que contiene las portadas de cada película en formato .jpg.
  
* database_script: Directorio que contiene el script para la creación de la base de datos en SQL Server.
  
* src/main/java/com/proyectoejemplo/ejemplo/controladores: Contiene los archivos controladores que gestionan las diferentes funcionalidades de la aplicación (***AdminControlador.java***, ***AssetsControlador.java***, ***HomeControlador.java***).
  
* src/main/java/com/proyectoejemplo/ejemplo/excepciones: Contiene las clases de excepciones personalizadas para el manejo de errores (***AlmacenExcepcion.java***, ***FileNotFoundExcepcion.java***).

* src/main/java/com/proyectoejemplo/ejemplo/modelo: Contiene las clases de modelo que representan las entidades del dominio (***Genero.java***, ***Pelicula.java***).

* src/main/java/com/proyectoejemplo/ejemplo/repositorio: Contiene las interfaces de repositorio para acceder a los datos de las películas y géneros (***GeneroRepositorio.java***, ***PeliculaRepositorio.java***).

* src/main/java/com/proyectoejemplo/ejemplo/servicio: Contiene las clases de servicio que implementan la lógica de negocio de la aplicación (***AlmacenServicio.java***, ***PostConstruct.java***).

* src/main/java/com/proyectoejemplo/ejemplo/EjemploApplication.java: Archivo principal que permite ejecutar la aplicación web.

* src/main/resources/templates/admin: Contiene las plantillas HTML para las funcionalidades de administración (***editar-pelicula.html***, ***form.html***, ***index.html***, ***nueva-pelicula.html***).

* src/main/resources/templates: Contiene las plantillas HTML para las funcionalidades generales de la aplicación (***index.html***, ***master.html***, ***paginacion.html***, ***pelicula.html***, ***peliculas.html***).

* src/main/resources: Contiene archivos de configuración y recursos adicionales de la aplicación (***application.properties***, ***messages.properties***).
 
* tests: Directorio para pruebas (tests).
  
* target: Directorio que contiene los archivos generados por Maven.

## Gestión de la base de datos
Para la gestión de películas en la aplicación, se ha diseñado una base de datos utilizando SQL Server. El diagrama de la base de datos representa la estructura y las relaciones entre las tablas que almacenan la información de las películas y sus géneros. El diagrama incluye las siguientes entidades:

* Tabla "pelicula": Almacena los datos de cada película, como el título, la sinopsis, la fecha de estreno, la ruta de la portada y el ID del tráiler de YouTube.

* Tabla "genero_pelicula": Establece una relación muchos a muchos entre las tablas "pelicula" y "genero", permitiendo asignar uno o varios géneros a cada película.

* Tabla "genero": Contiene los diferentes géneros de películas que se pueden asignar a cada película.

A continuación se muestra el diagrama de la base de datos utilizado en la aplicación:
![image](https://github.com/DannyRMoreno28/JavaSpringWebMovieTrailerApp/assets/108888740/5f34fc6e-da17-4bf5-9fd6-7860349582f2)

## Funcionalidades del aplicativo
* Inicio: Muestra las últimas películas agregadas.
  ![image](https://github.com/DannyRMoreno28/JavaSpringWebMovieTrailerApp/assets/108888740/7d46803b-e485-471a-82cd-986b9137dbda)

* Películas: Permite ver los detalles de cada película, incluyendo su portada, género, sinopsis, fecha de estreno y ver su tráiler. Además, es posible editar o eliminar las películas almacenadas en la base de datos.
  ![image](https://github.com/DannyRMoreno28/JavaSpringWebMovieTrailerApp/assets/108888740/addd3c7b-5b2b-468e-84c0-9a1c4ee6e89d)

Cuando se accede a una película, es posible ver los distintos datos que componen su registro en la aplicación. Esto incluye detalles como el título de la película, la sinopsis, la fecha de estreno, la ruta de la portada de la película, el ID del tráiler de YouTube, entre otros. Estos datos se muestran en la interfaz de usuario para que el usuario pueda tener una visión completa de la información relacionada con la película en cuestión.
![image](https://github.com/DannyRMoreno28/JavaSpringWebMovieTrailerApp/assets/108888740/f6667927-ac4e-4617-b258-45cafe3f9dba)

* Agregar Películas: Permite agregar nuevas películas a la base de datos. Se solicitan varios datos, como título, sinopsis, fecha de estreno, link del tráiler y portada de la película. Después de proporcionar todos los datos requeridos, se pueden guardar los cambios.
![image](https://github.com/DannyRMoreno28/JavaSpringWebMovieTrailerApp/assets/108888740/9f55caaa-9e89-4b9a-8646-9cb9addfcd27)

Después de presionar el botón "Agregar película", se accede a una nueva sesión de la página donde se solicitan los diferentes datos que componen un registro de película. En esta sesión, se presentan campos o formularios para ingresar información como el título de la película, la sinopsis, la fecha de estreno, la ruta de la portada de la película, el enlace del tráiler de YouTube y otros detalles relevantes. El usuario debe completar estos campos con la información correspondiente antes de guardar la película en la base de datos. De esta manera, se permite la creación de nuevos registros de películas en la aplicación.![image](https://github.com/DannyRMoreno28/JavaSpringWebMovieTrailerApp/assets/108888740/a25a5a31-c201-462e-a499-610672231516)

## Instrucciones para configurar y ejecutar el proyecto localmente
1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE preferido.
3. Asegúrate de tener Java 17 instalado.
4. Ejecuta la clase EjemploApplication.java para iniciar la aplicación.
5. Abre tu navegador web y visita http://localhost:8080/ para acceder a la aplicación.
6. Recuerda que para ejecutar correctamente la aplicación, debes tener configurada una base de datos SQL Server y asegurarte de que la configuración en el archivo application.properties sea correcta.
