Instrucciones para la ejecución con netbeans, xampp y docker.

1. Descarga el repositorio con git clone con el link 
	https://github.com/Jhopon94/RegistroTecnoYomuBackend.git

2. crea una carpeta "resources" dentro de la 
	ruta src/main/ y añade el archivo application.properties con la 
	información para la conexión a tu base de datos.
	no olvides poner las siguientes líneas para el
	correcto funcionamiento.
	
	spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
	spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect


3. Una vez realizados los pasos anteriores, proceda a compilar el programa con el
	editor que esté utilizando.

4. Asegúrese de tener docker instalado y funcionando en su máquina.

5. Asegúrese de tener xampp funcionando correctamente en localhost con el puerto
	mysql por defecto con configuración de suaurio y contraseña
	acorde a la agergada en el archivo properties.

6. En la carpeta raiz del proyecto abra un terminal de comandos o gitbash,
	y proceda a ejecutar el siguiente comando para crear la imagen docker
	(el archivo dockerfile ya se encuentra incluido en el repositorio):

	docker build -t nombreImagen .

	(No olvide el punto al final, es importante!)

7. Ahora, ejecuta el siguiente comando para ejecutar la imagen docker dentro de un 
	contenedor:

	docker run -p 8080:8080 --name nombreParaContenedor nombreImagen

8. Verificar que se obtenga un "Whitelabel" en el navegador de su preferencia al
	ingresar a la dirección localhost:8080/

9. Si necesita hacer pruebas, debe crear la base de datos llamada con el nombre
	"tecnoyomureparaciones", y las tablas basadas en las entidades
	que se aprecian en el repositorio.
