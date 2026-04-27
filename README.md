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

El script del Servidor Central permite:

- Generar el archivo de configuración (`.config`)
- Ejecutar el Servidor Central
- Preparar el despliegue del Servidor Web

---
Para ejecutar el script, abrir una terminal en la carpeta raíz del proyecto clonado y ejecutar el siguiente comando:

    ./DeployServidorCentral.sh

Al ejecutar aparecera un Menu de opciones 

![image alt](https://github.com/17Franco/Culturarte/blob/98b423e42943e5d571794a840c0eda2489b3ffc0/img-Script/MenuPricipal.png)

1. **Crear Archivo de Configuración**
2. **Deploy Project**
3. **Help**
4. **Salir**

⚠️ Antes de ejecutar el deploy, primero se debe generar el archivo de configuración usando la opción **1**.

### Crear archivo de configuración

![image alt](https://github.com/17Franco/Culturarte/blob/98b423e42943e5d571794a840c0eda2489b3ffc0/img-Script/MenuOpcion1.png)

La opción **1** permite crear el archivo `.config` de dos formas:

- **Modo local**
  - Configura todo en `localhost`
  - Ideal para ejecutar solo el Servidor Central

- **Modo personalizado**
  - Permite definir:
    - IP del Servidor Central (debe ser esta misma PC)
    - Puertos SOAP y REST
    - IP del Servidor Web

  - Si se ingresa una IP remota válida, el script solicitará:
    - Usuario de la máquina remota (para conexión SSH)

---
## 🚀 Ejecución del sistema

### ▶️ Caso 1: Solo Servidor Central

1. Ejecutar el script  
2. Seleccionar opción **1 → Modo local**  
3. Luego ejecutar opción **2 (Deploy Project)**  

Esto levanta únicamente el Servidor Central en la máquina local.

---

### 🌐 Caso 2: Servidor Central + Servidor Web

1. Ejecutar el script  
2. Seleccionar opción **1 → Modo personalizado**  
3. Configurar:
   - IP del Servidor Central (esta PC)
   - IP del Servidor Web (puede ser otra PC)
   - Puertos necesarios  
4. Completar usuario de la máquina remota (si aplica)  
5. Ejecutar opción **2 (Deploy Project)**  

El archivo `.config` generado será utilizado posteriormente por el script del Servidor Web.

---

## ⚠️ Consideraciones importantes

- El archivo `.config` es obligatorio antes de ejecutar el deploy  
- Ambos scripts deben ejecutarse inicialmente en la misma máquina  
- Para despliegue remoto:
  - La máquina destino debe tener **SSH habilitado**
  - Debe contar con **Tomcat 10** instalado  
