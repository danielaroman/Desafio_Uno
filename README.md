## API REST PERIODOS PERDIDOS

### Dependencias

Para copilar el proyecto se requiere lo siguiente:

* Instalar Java 8 JDK

* Instalar Maven (Apache Maven 3.3.9)

### Build & Deploy

Ingresar a la carpeta raiz del proyecto y ejecutar el siguiente comando maven


    $ mvn clean package


Nota: Debe estar disponible el puerto 9090 en el PC donde se ejecute esta API

### Desplegar Api

Se debe tomar el war generado en la carpeta target llamado "periodos-perdidos.war"
Y desplegar sobre un servidor tomcat local configurado en el puerto 9090


### Llamada a servicio (GET)

Ejecutar la siguiente url en navegador o postman
http://localhost:9090/periodos-perdidos/api/v1/periodosPerdidos
