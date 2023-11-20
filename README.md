# EvaluacionGL
Repositorio para evaluacion empresa Global Logic

# Proyecto de Microservicio de Creación de Usuarios

Este proyecto de microservicio fue desarrollado para aplicar a una vacante de desarrollador para la empresa Global Logic.

El proyecto base fue creado en el sitio https://start.spring.io/

## Funcionalidad
Proporciona funcionalidades para la creación y consulta de usuarios, con validación de datos y manejo de excepciones. 
El servicio persiste los datos en una base de datos H2 y utiliza tokens JWT para la posterior autenticación.

## Requisitos

- Java 8
- Gradle 7.4
- Springboot 3.1.5
- Dependencias de Spring Boot (Spring Web, Spring Data JPA, Spring Security lombok, jaxb, jackson, etc)

## Configuración

1. Clona el repositorio desde [GitHub](https://github.com/tu-usuario/tu-repo) o descarga el código fuente.

2. Asegúrate de tener Java 8 instalado en tu sistema.

3. Edita el archivo `application.properties` para configurar la base de datos y otras propiedades según tus necesidades.

## Ejecución

Puedes ejecutar el proyecto utilizando Gradle. Abre una terminal en el directorio raíz del proyecto y ejecuta el siguiente comando:

Terminal cmd: 
gradlew bootRun

Terminal bash:
./gradlew bootRun

** Si el comando se ejecuta correctamente debe mostrar un los similar al siguiente:
Hibernate: select u1_0.id,u1_0.created,u1_0.email,u1_0.is_active,u1_0.last_login,u1_0.name,u1_0.password,u1_0.token from apiuser u1_0
Hibernate: select p1_0.user_id,p1_1.id,p1_1.citycode,p1_1.countrycode,p1_1.number from apiuser_phones p1_0 join phone p1_1 on p1_1.id=p1_0.phones_id where p1_0.user_id=?
<==========---> 80% EXECUTING [3m 27s]
> :bootRun

La aplicación se ejecutará en http://localhost:8080.

## Endpoints
/sign-up: Permite la creación de un usuario con un contrato de entrada en formato JSON. 
Validará el formato del correo electrónico y la contraseña. 
Retorna un usuario con los campos id, created, lastLogin, token y isActive.

Uso
Puedes utilizar herramientas como Postman para probar los endpoints del servicio.

Ejemplo de solicitud POST para crear un usuario:

POST http://localhost:8080/sign-up
{
  "name": "Lina Gomez",
  "email": "lgomez@gmail.com",
  "password": "Qwerty12",
  "phones": [
    {
      "number": 123456789,
      "citycode": 8320000,
      "countrycode": "SCL"
    }
  ]
}
** La respuesta de este servisio es un Httpstatus + un json con la siguiente estructura
{"id":"c12cb258-d9e2-4d99-864c-f817b3f9c014","created":[2023,11,11],"lastLogin":[2023,11,11],"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjMTJjYjI1OC1kOWUyLTRkOTktODY0Yy1mODE3YjNmOWMwMTQiLCJuYW1lIjoiTWF1cm8gR29tZXoiLCJleHAiOjE2OTk2NzE2MDAsImlhdCI6MTY5OTY3MTYwMCwiZW1haWwiOiJtZ29tZXpAZ21haWwuY29tIn0.3HfUrO94V-Jx2-Pmg-cXcKJt97doMRlA1SGR_G_B60Y","active":true}

** En caso de error el json de respuesta es el siguiente:
{"timestamp":"2023-11-11T22:29:12.046+00:00","code":409,"detail":"Usuario ya existe"}

Ejemplo de solicitud GET para crear un usuario:

GET http://localhost:8080/listar
Retorna un objeto json con la lista de usuarios existentes.

## Diagramas
Los diagramas de componentes y secuencia se encuentran en la carpeta diagramas de este repositorio.

## Contribución
Si deseas contribuir a este proyecto, por favor abre un problema o envía una solicitud de extracción.

## Licencia
N/A

## "Pruebas unitarias" 
Se desarrollaron 2 tes unitarios y se utilizo junit y mockito
- EvaluacionControlletTests.java
- UserServiceImplTest.java





