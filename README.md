"# backend-SGClinica" 
Documento de funcionalidades y pruebas de la aplicación
Cómo ejecutar la aplicación localmente
Requisitos previos
Asegúrese de tener instalado lo siguiente en su sistema:

MYSQL
Un IDE, por ejemplo: Visual Studio
Java 11
Spring Boot
Node.js
Angular
Pasos para ejecutar la aplicación
Abra MYSQL y cargue el esquema que se encuentra dentro de la carpeta "esquemas" en el backend.
Inicie sesión con las credenciales proporcionadas (username y admin).
Siga las instrucciones del archivo README para ejecutar el backend.
Una vez que el backend esté en funcionamiento, siga las instrucciones específicas del frontend para instalar las dependencias y ejecutar Angular.
Funcionalidades de la aplicación
La aplicación cuenta con las siguientes funcionalidades:

CRUD básico (crear, leer, actualizar, eliminar) para:
Pacientes
Usuarios
Médicos
CRUD para roles.
Cada una de estas funcionalidades incluye las operaciones de crear, leer, actualizar y eliminar.

Funcionalidades sujeto de pruebas
Se cubrirán las funcionalidades relacionadas con el rol de pacientes, que incluyen las operaciones CRUD básicas.

Pruebas a realizar
Crear paciente:

Probar la creación exitosa de un nuevo paciente con datos válidos.
Probar la validación de datos (por ejemplo, campos obligatorios, formato de correo electrónico, etc.) al crear un nuevo paciente.
Leer paciente:

Probar la lectura exitosa de los detalles de un paciente existente.
Probar la búsqueda y filtrado de pacientes en la lista de pacientes.
Actualizar paciente:

Probar la actualización exitosa de los detalles de un paciente existente con datos válidos.
Probar la validación de datos (por ejemplo, campos obligatorios, formato de correo electrónico, etc.) al actualizar los detalles de un paciente.
Eliminar paciente:

Probar la eliminación exitosa de un paciente existente.
Probar la confirmación antes de eliminar un paciente para evitar eliminaciones accidentales.
Estas pruebas se llevarán a cabo tanto en el backend como en el frontend, para garantizar la correcta funcionalidad y validación de datos en ambos lados.
