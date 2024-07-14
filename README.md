<h1 align="center" style="font-weight: 900; font-size: 40px; letter-spacing: 2px; color: cadetblue;"> Spring Boot Literatura</h1>

<img src="" alt="">

<p style="font-weight: 500; font-family: system-ui;">Este proyecto es una aplicación Java utilizando Spring Boot que permite gestionar libros y autores. 
    Incluye funcionalidades para buscar libros, listar autores y libros, y realizar búsquedas específicas por idioma.</p>


<p  align="center" ><img  src="https://github.com/user-attachments/assets/1cfffbd2-a0c0-4a37-b17f-c8bcc164bc0b" alt="badge literalura"></p>

<h2 style="font-weight: 900;">Características</h2>
<ul style="font-weight: 500; font-family: system-ui;">
    <li>Buscar Libro: Permite buscar libros a través de una API externa y guardarlos en la base de datos.</li>
    <li>Listar Libros Registrados: Muestra una lista de todos los libros registrados en la base de datos.</li>
    <li>Listar Autores Registrados: Muestra una lista de todos los autores registrados en la base de datos.</li>
    <li>Listar Autores Vivos en un Determinado Año: Permite buscar autores vivos en un rango de años especificado.</li>
    <li>Listar Libros por Idioma: Permite buscar y listar libros por un idioma específico.</li>
</ul>

<h2 style="font-weight: 900;">Requisitos</h2>

<ul style="font-weight: 500; font-family: system-ui;">
    <li>Java 11 o superior</li>
    <li>Maven 3.6.3 o superior</li>
    <li>Spring Boot 2.5.4 o superior</li>
    <li>MySQL 8.0 o superior</li>
    <li>pgAdmin 4 (opcional para PostgreSQL)</li>
</ul>


<h2 style="font-weight: 900;">Uso</h2>

<p style="font-weight: 500; font-family: system-ui;">Al ejecutar la aplicación, podrás interactuar con ella a través del menú en consola:</p>

<p style="font-weight: 500; font-family: system-ui;">1 -> Buscar libro.</p>
<p style="font-weight: 500; font-family: system-ui;">2 -> Mostrar Lista de los Libros registrados.</p>
<p style="font-weight: 500; font-family: system-ui;">3 -> Mostrar Lista de autores registrados.</p>
<p style="font-weight: 500; font-family: system-ui;">4 -> Listar autores vivos en un determinado año.</p>
<p style="font-weight: 500; font-family: system-ui;">5 -> Listar libros por Idioma.</p>
<p style="font-weight: 500; font-family: system-ui;">0 -> Salir.</p>

<h2 style="font-weight: 900;">Estructura del Proyecto</h2>
<ul style="font-weight: 500; font-family: system-ui;">
    <li><span style="font-weight: 900;">Principal:</span> Clase principal que contiene el menú y maneja la interacción del usuario.</li>
    <li><span style="font-weight: 900;">DataLibros:</span> Entidad JPA que representa los libros en la base de datos.</li>
    <li><span style="font-weight: 900;">DataAutor:</span> Entidad JPA que representa los autores en la base de datos.</li>
    <li><span style="font-weight: 900;">LibrosRepository:</span> Repositorio JPA para realizar operaciones CRUD sobre la entidad DataLibros.</li>
    <li><span style="font-weight: 900;">AutoresRepository:</span> Repositorio JPA para realizar operaciones CRUD sobre la entidad DataAutor.</li>
</ul>
</ul>
