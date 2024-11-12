##   CONCEPTOS | ACCESO A DATOS
—————————————————————————————————————————
###  CONCEPTOS BASICOS | 1
- **MVC (Modelo Vista Controlador):** patron de diseño ce arquitecturas para el desarrollo de aplicaciones software.
- **Componentes MVC:** 
	- ***Vista:*** parte gráfica que interactúa con el usuario.
	- ***Modelo:*** datos de la aplicación y reglas de negocio.
	- ***Controlador:*** coordina la vista y el modelo.
- **Logica de Negocio:** parte del código que implementa las funcionalidades centrales y específicas de la aplicación.
—————————————————————————————————————————
###  EXPLICACION | MODELO VISTA CONTROLADOR
la organizacion del proyecto la tendremos que dividir en 3 partes/carpetas: _modelo_, _vista_ y _controlador_. dentro de cada una de ellas hay que crear una clase general _(actuara de manera generica)_.

cada carpeta ayuda a que el codigo este organizado de manera mas completa, distiguiendo d la clase mas general hasta la mas especefica. la organizacion de las carpetas se diferencia en:
- **Carpeta Modelo:** todos los calculos y operaciones que necesitaremos, incluso algoritmos lo tendremos que meter en esta carpeta, es la que menos contacto tiene con el usuario final y unicamente se puede acceder a ella a traves del controlador.
- **Carpeta Vista:** agregaremos toda la interfaz ya sea grafica o por texto _(terminal)_. de esta manera si tenemos que modificar algo en especifico sabremos que esta en esta carpeta y no debemos recorrernos el codigo entero.
- **Carpeta Controlador:** la carpeta controlador actua de intermediaria entre la vista y el modelo. de manera sencilla, el usuario en la carpeta vista solo envia y recibe datos, la carpeta controlador es la encargada de leer esos datos y enviarlos y recibirlos donde sea necesario.

este tipo de organizacion de carpetas ayuda a que si necesitamos modificar el codigo, por poco que sea no tengamos que esta revisando todo el codigo de una sola tirada, sino que podremos diferenciar entre la parte que queremos modificar y dirigirnos directamente a la carpeta y fichero que buscamos.
—————————————————————————————————————————
