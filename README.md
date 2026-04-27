# Servidor Central (Projecto distribuido)

Este proyecto forma parte del Laboratorio de Programación de Aplicaciones del Tecnólogo en Informática.
El Servidor Central expone servicios web de tipo SOAP que seran consumidos por El servidor Web https://github.com/17Franco/CulturarteWeb.
Además, incluye una interfaz gráfica desarrollada con Java Swing, destinada al administrador, desde la cual se pueden gestionar las funcionalidades principales del sistema.





## Responsables

- Franco Echaide
- Mateo Navarro
- Gerardo Navarro
- Martin Fernandez


## Stack Tecnológico

- Java 21 – Lenguaje de desarrollo principal del proyecto.

- Maven – Utilizado para la gestión de dependencias y construcción del proyecto.

- Swing – Framework usado para la interfaz gráfica del administrador.

- MySQL – Sistema de gestión de base de datos utilizado para almacenar y manejar la información.

- JPA – Proporciona el mapeo entre objetos Java y tablas de la base de datos, permitiendo persistencia.

- Hibernate – Implementación de JPA encargada de gestionar las operaciones ORM.

- Mockito – Framework para crear mocks y realizar pruebas unitarias aisladas.

- JUnit – Framework principal para ejecutar las pruebas unitarias del proyecto.


## Creacion Base de datos

Antes de usar el script de compilación y deploy, se debe crear la base de datos y el usuario correspondiente.

    CREATE DATABASE Culturarte;

    CREATE USER 'equipo7'@'localhost' IDENTIFIED BY 'equipo7';

    GRANT ALL PRIVILEGES ON culturarte.* TO 'equipo7'@'localhost';

    FLUSH PRIVILEGES;

## Uso de Scritp de compilacion y deploy
Para ejecutar el script, abrir una terminal en la carpeta raíz del proyecto clonado y ejecutar el siguiente comando:

    ./DeployServidorCentral.sh

Al ejecutar aparecera un Menu de opciones 

