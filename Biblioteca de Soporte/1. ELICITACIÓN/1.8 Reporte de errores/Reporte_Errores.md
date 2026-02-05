# Reporte de errores

### 1. Errores de Validación de Entrada

#### 1.1 Campos Obligatorios

| Código | Descripción | Ejemplo de Mensaje | Solución Sugerida |
|--------|-------------|-------------------|-------------------|
| VAL-001 | Campo obligatorio vacío | "El campo donante es obligatorio" | Validar en frontend y backend |
| VAL-002 | Múltiples campos vacíos | "Todos los campos son obligatorios" | Verificar todos los campos requeridos |
| VAL-003 | Tipo de dato incorrecto | "La cantidad debe ser un número" | Validar tipos de datos |
| VAL-004 | Formato de fecha inválido | "Formato de fecha incorrecto (YYYY-MM-DD)" | Usar formatos estandarizados |
| VAL-005 | Formato de email inválido | "El email no tiene un formato válido" | Regex de validación de email |
| VAL-006 | Formato de teléfono inválido | "El teléfono debe tener 10 dígitos" | Validar formato telefónico |
| VAL-007 | Valor numérico negativo | "El valor debe ser positivo" | Validar rangos mínimos |
| VAL-008 | Valor fuera de rango | "El valor excede el máximo permitido" | Definir límites apropiados |
| VAL-009 | Longitud mínima no cumplida | "Mínimo 3 caracteres requeridos" | Validar longitud mínima |
| VAL-010 | Longitud máxima excedida | "Máximo 50 caracteres permitidos" | Truncar o rechazar entrada |
| VAL-011 | Caracteres especiales no permitidos | "Caracteres inválidos en el campo" | Sanitizar entrada |
| VAL-012 | Formato de URL inválido | "La URL no es válida" | Validar formato URL |
| VAL-013 | Valor decimal inválido | "Debe ser un número con máximo 2 decimales" | Formatear correctamente |
| VAL-014 | Campo duplicado | "Ya existe un registro con estos datos" | Verificar unicidad |


#### 1.2 Validaciones de Negocio

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| VAL-101 | Fecha futura inválida | "No se pueden registrar fechas futuras" |
| VAL-102 | Fecha anterior muy antigua | "La fecha es muy antigua (mínimo 1 año)" |
| VAL-103 | Monto excede límite diario | "El monto excede el límite diario permitido" |
| VAL-104 | Transacción duplicada | "Esta transacción ya fue registrada" |
| VAL-105 | Saldo insuficiente | "No hay saldo suficiente para esta operación" |
| VAL-106 | Usuario inactivo | "El usuario se encuentra inactivo" |
| VAL-107 | Cuenta bloqueada | "La cuenta ha sido bloqueada" |
| VAL-108 | Período cerrado | "El período ya está cerrado" |
| VAL-109 | Límite de registros alcanzado | "Se alcanzó el límite máximo de registros" |
| VAL-110 | Categoría no válida | "La categoría especificada no existe" |

---

### 2. Errores de Autenticación y Autorización

#### 2.1 Errores de Login

| Código | Descripción | Ejemplo de Mensaje | Severidad |
|--------|-------------|-------------------|-----------|
| AUTH-001 | Usuario no encontrado | "El usuario no existe en el sistema" | Alta |
| AUTH-002 | Contraseña incorrecta | "La contraseña es incorrecta" | Alta |
| AUTH-003 | Cuenta bloqueada por intentos | "Cuenta bloqueada tras múltiples intentos" | Alta |
| AUTH-004 | Cuenta deshabilitada | "La cuenta ha sido deshabilitada" | Alta |
| AUTH-005 | Cuenta eliminada | "La cuenta ha sido eliminada" | Alta |
| AUTH-006 | Contraseña expirada | "La contraseña ha expirado, debe cambiarla" | Media |
| AUTH-007 | Primera vez que inicia sesión | "Debe cambiar su contraseña inicial" | Media |
| AUTH-008 | Credenciales vacías | "Debe ingresar usuario y contraseña" | Baja |
| AUTH-009 | Formato de credenciales inválido | "Formato de credenciales incorrecto" | Baja |
| AUTH-010 | Login desde dispositivo no autorizado | "Dispositivo no autorizado para este usuario" | Alta |

#### 2.2 Errores de Token JWT

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| AUTH-101 | Token no proporcionado | "Token de autorización requerido" |
| AUTH-102 | Token malformado | "El token tiene un formato inválido" |
| AUTH-103 | Token expirado | "El token ha expirado, inicie sesión nuevamente" |
| AUTH-104 | Token revoked | "El token ha sido revocado" |
| AUTH-105 | Token con firma inválida | "Firma del token inválida" |
| AUTH-106 | Token Audience inválido | "Audience del token no válido" |
| AUTH-107 | Token Issuer inválido | "Issuer del token no válido" |
| AUTH-108 | Token con payload corrupto | "No se puede decodificar el token" |

#### 2.3 Errores de Permisos (RBAC)

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| AUTH-201 | Rol no autorizado | "No tiene permisos para esta acción" |
| AUTH-202 | Permiso específico faltante | "Se requiere permiso: [permiso]" |
| AUTH-203 | Acceso a recurso ajeno | "No puede acceder a este recurso" |
| AUTH-204 | Modificación no autorizada | "No tiene permisos para modificar este registro" |
| AUTH-205 | Eliminación no autorizada | "No tiene permisos para eliminar este registro" |
| AUTH-206 | Acción deshabilitada para rol | "Esta acción no está disponible para su rol" |
| AUTH-207 | Acceso a módulo restringido | "No tiene acceso a este módulo" |
| AUTH-208 | Límite de sesiones alcanzado | "Máximo de sesiones simultáneas alcanzado" |

---

### 3. Errores de Recursos No Encontrados

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| NOT-001 | ID de donación no existe | "No se encontró la donación con ID: [id]" |
| NOT-002 | ID de usuario no existe | "Usuario no encontrado" |
| NOT-003 | ID de egreso no existe | "Egreso no encontrado" |
| NOT-004 | ID de reporte no existe | "Reporte no encontrado" |
| NOT-005 | Archivo no encontrado | "El archivo solicitado no existe" |
| NOT-006 | Recurso eliminado previamente | "El recurso ya fue eliminado" |
| NOT-007 | Categoría no existe | "La categoría no existe" |
| NOT-008 | Configuración no encontrada | "Configuración no encontrada" |
| NOT-009 | Plantilla no encontrada | "La plantilla no existe" |
| NOT-010 | Registro no encontrado | "No se encontró ningún registro" |

---

### 4. Errores de Servidor y Base de Datos

#### 4.1 Errores de Servidor (5xx)

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| SRV-001 | Error interno genérico | "Ha ocurrido un error interno" |
| SRV-002 | Error de base de datos | "Error de conexión con la base de datos" |
| SRV-003 | Timeout de servidor | "La solicitud tardó demasiado" |
| SRV-004 | Memoria insuficiente | "Error de memoria insuficiente" |
| SRV-005 | Disco lleno | "Espacio de almacenamiento insuficiente" |
| SRV-006 | Servicio no disponible | "El servicio está temporalmente no disponible" |
| SRV-007 | Mantenimiento programado | "Sistema en mantenimiento" |
| SRV-008 | Error de configuración | "Error de configuración del servidor" |
| SRV-009 | Error de dependencias | "Error en servicio externo" |
| SRV-010 | Carga excesiva | "Sistema sobrecargado, intente más tarde" |

#### 4.2 Errores de Base de Datos

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| DB-001 | Error de conexión | "No se puede conectar a la base de datos" |
| DB-002 | Error de consulta | "Error al ejecutar la consulta" |
| DB-003 | Violación de restricción unique | "Ya existe un registro con estos datos" |
| DB-004 | Violación de clave foránea | "Referencia a registro inexistente" |
| DB-005 | Violación de restricción check | "El valor no cumple la restricción" |
| DB-006 | Transacción fallida | "No se pudo completar la transacción" |
| DB-007 | Deadlock detectado | "Conflicto de transacciones, intente de nuevo" |
| DB-008 | Timeout de consulta | "La consulta excedió el tiempo límite" |
| DB-009 | Datos corruptos | "Se detectaron datos corruptos" |
| DB-010 | Backup fallido | "No se pudo completar el backup" |

---

### 5. Errores Específicos por Módulo

#### 5.1 Errores de Donaciones (DON)

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| DON-001 | Donante no especificado | "El nombre del donante es obligatorio" |
| DON-002 | Tipo de donación inválido | "Tipo de donación no válido" |
| DON-003 | Cantidad inválida | "La cantidad debe ser mayor a 0" |
| DON-004 | Valor unitario inválido | "El valor unitario debe ser numérico" |
| DON-005 | Subtotal mal calculado | "Error en cálculo de subtotal" |
| DON-006 | Donación no modificable | "No se puede modificar una donación procesada" |
| DON-007 | Donación no eliminable | "No se puede eliminar, tiene movimientos asociados" |
| DON-008 | Fecha de donación inválida | "La fecha no puede ser futura" |
| DON-009 | Donante bloqueado | "El donante se encuentra bloqueado" |
| DON-010 | Límite de donación excedido | "Excede el límite máximo de donación" |
| DON-011 | Moneda no soportada | "Moneda no válida para donaciones" |
| DON-012 | Archivo de evidencia corrupto | "El archivo de evidencia no es válido" |

#### 5.2 Errores de Usuarios (USR)

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| USR-001 | Username ya existe | "El nombre de usuario ya está en uso" |
| USR-002 | Email ya registrado | "El email ya está registrado" |
| USR-003 | Username muy corto | "El username debe tener al menos 4 caracteres" |
| USR-004 | Contraseña débil | "La contraseña no cumple los requisitos de seguridad" |
| USR-005 | Contraseña anterior incorrecta | "La contraseña actual es incorrecta" |
| USR-006 | Usuario ya existe | "El usuario ya existe en el sistema" |
| USR-007 | Rol no existe | "El rol especificado no existe" |
| USR-008 | No se puede eliminar último admin | "No se puede eliminar el último administrador" |
| USR-009 | Usuario inactivo no puede login | "El usuario está inactivo" |
| USR-010 | Password igual al anterior | "No puede usar la misma contraseña anterior" |
| USR-011 | Sesión activa no permite cambios | "Cierre sesión para realizar este cambio" |
| USR-012 | Username contiene caracteres inválidos | "El username solo puede contener letras y números" |

#### 5.3 Errores de Egresos (EGR)

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| EGR-001 | Descripción vacía | "La descripción es obligatoria" |
| EGR-002 | Tipo de egreso inválido | "El tipo de egreso no es válido" |
| EGR-003 | Monto inválido | "El monto debe ser mayor a 0" |
| EGR-004 | Fecha inválida | "La fecha no puede ser futura" |
| EGR-005 | Egreso sin categoría | "Debe seleccionar una categoría" |
| EGR-006 | Fondos insuficientes | "No hay fondos suficientes" |
| EGR-007 | Egreso ya procesado | "El egreso ya fue aprobado" |
| EGR-008 | No tiene permiso para aprobar | "No tiene permisos para aprobar egresos" |
| EGR-009 | Egreso requiere aprobación | "El egreso requiere aprobación" |
| EGR-010 | Archivo de comprobante inválido | "El archivo de comprobante no es válido" |
| EGR-011 | Egreso duplicado | "Ya existe un egreso con estos datos" |
| EGR-012 | Presupuesto excedido | "Se excedió el presupuesto del período" |

#### 5.4 Errores de Reportes (REP)

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| REP-001 | Período inválido | "La fecha de inicio debe ser anterior a la final" |
| REP-002 | Período muy extenso | "El período máximo permitido es 1 año" |
| REP-003 | Sin datos para el período | "No hay datos en el período seleccionado" |
| REP-004 | Tipo de reporte inválido | "El tipo de reporte no existe" |
| REP-005 | Error al generar reporte | "Error al generar el reporte" |
| REP-006 | Formato no soportado | "El formato de reporte no está soportado" |
| REP-007 | Datos incompletos para reporte | "Faltan datos para generar el reporte" |
| REP-008 | Gráfico no disponible | "El gráfico solicitado no está disponible" |
| REP-009 | Exportación fallida | "Error al exportar el reporte" |
| REP-010 | Límite de registros excedido | "Demasiados registros para el reporte" |

#### 5.5 Errores de Exportación (EXP)

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| EXP-001 | Formato de exportación inválido | "El formato no es válido" |
| EXP-002 | Error al generar archivo | "Error al generar el archivo" |
| EXP-003 | Archivo muy grande | "El archivo excede el tamaño máximo" |
| EXP-004 | Timeout de exportación | "La exportación tardó demasiado" |
| EXP-005 | Error de permisos de archivo | "No hay permisos para crear el archivo" |
| EXP-006 | Plantilla no encontrada | "La plantilla de exportación no existe" |
| EXP-007 | Sin datos para exportar | "No hay datos para exportar" |
| EXP-008 | Formato corrupto | "El archivo generado está corrupto" |
| EXP-009 | Encoding inválido | "Error de codificación de caracteres" |
| EXP-010 | Disco lleno | "No hay espacio para guardar el archivo" |

---

### 6. Errores de Integración y API

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| API-001 | Endpoint no encontrado | "El endpoint solicitado no existe" |
| API-002 | Método HTTP no permitido | "Método no permitido para este endpoint" |
| API-003 | Rate limit excedido | "Demasiadas solicitudes, intente más tarde" |
| API-004 | Versión de API no soportada | "Versión de API no soportada" |
| API-005 | Content-Type no soportado | "Content-Type no soportado" |
| API-006 | Payload muy grande | "El cuerpo de la solicitud es demasiado grande" |
| API-007 | Header requerido faltante | "Falta header requerido: [header]" |
| API-008 | Servicio externo no disponible | "Servicio externo no disponible" |
| API-009 | Timeout de servicio externo | "Tiempo de espera agotado" |
| API-010 | Error de comunicación con API | "Error de comunicación" |

---

### 7. Errores de Rendimiento

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| PERF-001 | Consulta lenta | "La consulta excede el tiempo límite" |
| PERF-002 | Carga lenta | "El sistema está experimentando alta carga" |
| PERF-003 | Memoria insuficiente | "Memoria insuficiente para la operación" |
| PERF-004 | Too many connections | "Máximo de conexiones alcanzado" |
| PERF-005 | Timeout de operación | "La operación excedió el tiempo límite" |

---

### 8. Errores de Seguridad

| Código | Descripción | Ejemplo de Mensaje |
|--------|-------------|-------------------|
| SEC-001 | SQL Injection detectada | "Entrada no válida detectada" |
| SEC-002 | XSS detectada | "Posible ataque XSS detectado" |
| SEC-003 | CSRF token inválido | "Token de seguridad inválido" |
| SEC-004 | Acceso no autorizado | "Acceso denegado" |
| SEC-005 | IP bloqueada | "Dirección IP bloqueada" |
| SEC-006 | Usuario bloqueado por seguridad | "Usuario bloqueado por seguridad" |
| SEC-007 | Actividad sospechosa | "Actividad sospechosa detectada" |
| SEC-008 | Password comprometido | "Contraseña encontrada en filtraciones" |

---

