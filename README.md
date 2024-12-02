# **Juego de Preguntas Tipo Test - Proyecto Android**

## 📝 **Descripción**

Este proyecto consiste en una aplicación móvil de Android que implementa un juego de preguntas tipo test con temática libre.

La aplicación está formada por 3 *activities* e incluye funcionalidades de interacción, paso de datos entre pantallas y almacenamiento de registros en una base de datos SQLite.

---

## 🚀 **Características**

### Funcionalidades

1. **Pantallas estructuradas:**
   - **Pantalla de bienvenida:** Introducir el nombre del usuario.
   - **1 pantalla de preguntas:**
     - Una pregunta con 3 posibles respuestas (solo una correcta).
     - Una imagen representativa de la pregunta.
   - **Pantalla final:** Mostrará:
     - El nombre del usuario.
     - La puntuación total obtenida (sobre 10).
     - Indicará si es el nuevo récord del jugador.

2. **Flujo de navegación:**
   - Transiciones entre *activities* funcionales.
   - Paso de datos entre pantallas utilizando `putExtra`.

3. **Diseño adaptable:**
   - Diseño cuidado y responsivo para dispositivos móviles en orientación vertical.
   - Indicador del progreso del usuario (por ejemplo, "Pregunta 3/5").

4. **Almacenamiento en base de datos:**
   - Registros en SQLite de los nombres de los jugadores y sus mejores puntuaciones.
  
5. **Pantalla final personalizada:**
   - Diseño dinámico basado en el resultado:
     - Textos, imágenes y colores diferentes según la puntuación.
---

## 🛠️ **Tecnologías Utilizadas**

- **Lenguaje de programación:** Java
- **IDE:** Android Studio
- **Base de datos:** SQLite
---

## 🖥️ **Capturas de Pantalla**

![image](https://github.com/user-attachments/assets/6bd61b29-c3b8-4696-b6a1-09749ca7af0f)
![image](https://github.com/user-attachments/assets/db84cc95-b38c-4036-baf8-ea8532ceb461)
![image](https://github.com/user-attachments/assets/a75d15b1-53e2-4256-9b3e-3290b4793f33)

---

## 🔧 **Instalación**

### Requisitos previos
- **Entorno de desarrollo:** Android Studio.
- **Versión mínima de Android:** API 21 (Android 5.0 Lollipop).
- **Dependencias:**
  - Agrega *Toasty* y cualquier otra biblioteca usada al archivo `build.gradle`.

### Pasos de instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/juego-preguntas.git
   ```
2. Abre el proyecto en Android Studio.
3. Sincroniza el proyecto con *Gradle* para instalar las dependencias.
4. Compila y ejecuta la aplicación en un emulador o dispositivo físico.

---

## 🎮 **Cómo Jugar**

1. Introduce tu nombre en la pantalla de bienvenida y presiona "Comenzar".
2. Responde a las 5 preguntas seleccionando la respuesta correcta.
3. Observa tu puntuación final y verifica si has establecido un nuevo récord.

*(Incluye más detalles o consejos si es necesario.)*

---

## 👤 **Autor**

- **Nombre:** [Tu Nombre Completo]  
- **Correo:** [tuemail@ejemplo.com]  
- **Portafolio:** [Enlace a tu portafolio o GitHub]

---

## 🎯 **Objetivos del Proyecto**

1. Practicar el desarrollo de aplicaciones Android con múltiples *activities*.  
2. Implementar almacenamiento y recuperación de datos con SQLite.  
3. Diseñar una aplicación con experiencia de usuario adaptada a dispositivos móviles.  
4. Incorporar funcionalidades avanzadas como personalización de UI, sonidos y *toasts*.  

---

## 🛡️ **Licencia**

Este proyecto está desarrollado con fines educativos y no tiene una licencia comercial.  

---

Si necesitas más ayuda con el diseño o algún aspecto técnico, ¡házmelo saber! 😊
