# 📊 Sistema Kontably - Guía Completa

Bienvenido a la documentación oficial de **Kontably**, el sistema de gestión financiera diseñado para la transparencia y eficiencia contable.

---

## 🛠️ I. Guía de Instalación (Técnica)

Siga estos pasos para desplegar el sistema en un entorno de desarrollo o producción.

### 1. Requisitos Previos
* **Java:** Versión 17 o superior.
* **Maven:** Versión 3.8 o superior.
* **PostgreSQL:** Versión 14 o superior.
* **Docker:** (Opcional) Para despliegue simplificado.

### 2. Configuración de la Base de Datos
1. Acceda a su terminal de PostgreSQL o pgAdmin.
2. Cree una base de datos con el nombre exacto: `analisis`.
   ```sql
   CREATE DATABASE analisis;
   ```
El sistema utiliza el puerto estándar 5432.

### 3. Configuración del Proyecto
Localice el archivo src/main/resources/application.properties y ajuste sus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/analisis
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
```

### 4. Ejecución
Abra una terminal en la raíz del proyecto y ejecute:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: http://localhost:8088

**Nota sobre Docker:** Si prefiere usar contenedores, ejecute `docker-compose up -d` para levantar la base de datos y la aplicación automáticamente.

---

## 📖 II. Manual de Usuario (Operativo)

Esta sección detalla cómo utilizar los módulos del sistema paso a paso.

### Paso 1: Registro de Ingresos y Egresos
- **Ingresos:** Diríjase a "Registrar Ingreso". Ingrese el Monto y la Descripción. Esto se utiliza para donaciones y aportes.
- **Egresos:** Diríjase a "Registrar Egreso". Registre todos los gastos operativos.

**Importante:** El sistema vincula automáticamente su usuario a cada transacción para fines de auditoría.

### Paso 2: Visualización de Datos
- **Dashboard:** En la pantalla principal verá el Balance Mensual (Ingresos - Egresos) y el contador de operaciones del día.
- **Gráficos:** Acceda a "Gráfico Interactivo". Puede alternar entre:
  - **Barras:** Para comparar totales mensuales.
  - **Pastel:** Para ver la distribución porcentual de gastos vs. ingresos.

### Paso 3: Generación de Reportes
1. Haga clic en Generar Reportes.
2. Seleccione la Fecha de Inicio y Fecha de Fin.
3. Presione "Generar".
4. Revise la tabla de resultados y use el botón "Exportar Reporte (PDF)" para obtener un documento limpio y listo para imprimir.

### Paso 4: Gestión de Usuarios
- Solo administradores pueden acceder a este módulo.
- Puede crear nuevos usuarios, editar correos o desactivar accesos.
- Al crear un usuario, el sistema le asignará un Rol predeterminado de forma automática para asegurar la integridad de los datos.

---

## 🆘 Soporte y Ayuda

Si encuentra errores de servidor (Error 500) o problemas de conexión:

1. Verifique que el servicio de PostgreSQL esté activo.
2. Asegúrese de que el rango de fechas en los reportes contenga datos.

