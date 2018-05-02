# argus

## 1. Estructura de carpetas

> src   
> ├── main  
> │&ensp;&ensp;&ensp;├── java/cl/argus     
> │&ensp;&ensp;&ensp;│&ensp;&ensp;&ensp;├── models _(Modelos)_    
> │&ensp;&ensp;&ensp;│&ensp;&ensp;&ensp;├── repositories _(Repositorios DB)_  
> │&ensp;&ensp;&ensp;│&ensp;&ensp;&ensp;└── services _(REST)_     
> │&ensp;&ensp;&ensp;└── resources     
> │&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── application.properties    
> │&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└── static    
> │&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── css _(Hojas de estilo)_   
> │&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── img _(Imágenes)_  
> │&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└── js _(Vistas / Controladores)_     
> │&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── controllers _(Controladores)_     
> │&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└── views _(Vistas)_  
> └── test/java/argus _(Pruebas unitarias)_ 

## 2. Tecnologías a utilizar
### Back-end
> [PostgreSQL](https://www.openscg.com/bigsql/oscg_download?file=packages/PostgreSQL-10.3-1-win64-bigsql.exe&user=${auth.authName})   
> Java JDK ([JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))   

### Front-end
> AngularJS     

### Herramientas
> IDE:  
> &ensp;- [Netbeans Java EE](https://netbeans.org/)  
> &ensp;- [Eclipse Neon EE](http://www.eclipse.org/downloads/packages/release/Neon/3)   
> &ensp;- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=linux)     
> &ensp;- O algún editor como VS Code, Sublime, etc.  
> [Git](https://git-scm.com/)     
> [Gradle](https://gradle.org/)

## 3.- Configuración de base de datos
> &ensp;- <b>Pass de usuario root: </b> argus<br>
> &ensp;- Crear base de datos usando pgAdmin, nombrarla como "argus"