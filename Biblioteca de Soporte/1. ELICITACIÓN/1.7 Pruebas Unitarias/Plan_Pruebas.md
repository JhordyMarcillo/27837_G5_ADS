# Plan de Pruebas en Postman - Kontably

## Descripción del Documento

Este documento define el plan de pruebas generales para el Sistema de Gestión de Donaciones, diseñado para ser ejecutado en Postman. El plan abarca todas las funcionalidades del sistema y proporciona una guía detallada para las pruebas de API.

**Versión del Documento:** 1.0.0  
**Proyecto:** 27837_G5_ADS - Kontably

---

## Indices de las pruebas

1. **Validar autenticación de los diferentes usuarios (Tesorera y Coordinadora)** 
2. **Verificar operaciones CRUD para donaciones y para los egresos** 
3. **Probar gestión de usuarios**
4. **Validar registro de egresos y de ingresos** 
5. **Confirmar generación de reportes de ingresos, egresos y balances**
6. **Evaluar funcionalidades de exportación** 
7. **Probar gráficos interactivos**

---

## Estructura de Colecciones en Postman

### 1. Autenticación (Auth)

#### 1.1 Login de Usuario
- **Endpoint:** `POST /api/auth/login`
- **Descripción:** Autenticación de usuarios en el sistema
- **Headers:** `Content-Type: application/json`

**Caso de Prueba - AUT-001: Login Exitoso (Tesorera)**
```json
{
    "endpoint": "/api/auth/login",
    "method": "POST",
    "body": {
        "username": "tesorera",
        "password": "12345"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "token": "<jwt_token>",
            "user": {
                "username": "tesorera",
                "role": "Tesorera"
            }
        }
    }
}
```

**Caso de Prueba - AUT-002: Login Exitoso (Coordinadora)**
```json
{
    "endpoint": "/api/auth/login",
    "method": "POST",
    "body": {
        "username": "coordinadora",
        "password": "admin"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "token": "<jwt_token>",
            "user": {
                "username": "coordinadora",
                "role": "Coordinadora"
            }
        }
    }
}
```

**Caso de Prueba - AUT-003: Login con Credenciales Inválidas**
```json
{
    "endpoint": "/api/auth/login",
    "method": "POST",
    "body": {
        "username": "usuario_invalido",
        "password": "password_incorrecto"
    },
    "expected": {
        "status": 401,
        "response": {
            "success": false,
            "message": "Credenciales inválidas"
        }
    }
}
```

**Caso de Prueba - AUT-004: Login con Campos Vacíos**
```json
{
    "endpoint": "/api/auth/login",
    "method": "POST",
    "body": {
        "username": "",
        "password": ""
    },
    "expected": {
        "status": 400,
        "response": {
            "success": false,
            "message": "Los campos username y password son obligatorios"
        }
    }
}
```

#### 1.2 Validación de Token
- **Endpoint:** `GET /api/auth/validate`
- **Descripción:** Validar token JWT vigente

**Caso de Prueba - AUT-005: Token Válido**
```json
{
    "endpoint": "/api/auth/validate",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "valid": true,
            "user": {
                "username": "tesorera",
                "role": "Tesorera"
            }
        }
    }
}
```

**Caso de Prueba - AUT-006: Token Inválido o Expirado**
```json
{
    "endpoint": "/api/auth/validate",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <invalid_token>"
    },
    "expected": {
        "status": 401,
        "response": {
            "valid": false,
            "message": "Token inválido o expirado"
        }
    }
}
```

---

### 2. Donaciones

#### 2.1 Registro de Donaciones
- **Endpoint:** `POST /api/donaciones`
- **Descripción:** Registrar una nueva donación

**Caso de Prueba - DON-001: Registrar Donación Exitosa**
```json
{
    "endpoint": "/api/donaciones",
    "method": "POST",
    "headers": {
        "Authorization": "Bearer <valid_token>",
        "Content-Type": "application/json"
    },
    "body": {
        "donante": "Empresa XYZ",
        "tipo": "Dinero",
        "cantidad": 100,
        "valorUnitario": 50.00
    },
    "expected": {
        "status": 201,
        "response": {
            "success": true,
            "message": "Donación registrada exitosamente",
            "data": {
                "id": "<generated_id>",
                "donante": "Empresa XYZ",
                "tipo": "Dinero",
                "cantidad": 100,
                "valorUnitario": 50.00,
                "subtotal": 5000.00
            }
        }
    }
}
```

**Caso de Prueba - DON-002: Registrar Donación con Campos Vacíos**
```json
{
    "endpoint": "/api/donaciones",
    "method": "POST",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "body": {
        "donante": "",
        "tipo": "",
        "cantidad": null,
        "valorUnitario": null
    },
    "expected": {
        "status": 400,
        "response": {
            "success": false,
            "message": "Todos los campos son obligatorios"
        }
    }
}
```

**Caso de Prueba - DON-003: Registrar Donación con Valores Inválidos**
```json
{
    "endpoint": "/api/donaciones",
    "method": "POST",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "body": {
        "donante": "Donante Test",
        "tipo": "Alimentos",
        "cantidad": -5,
        "valorUnitario": -10.00
    },
    "expected": {
        "status": 400,
        "response": {
            "success": false,
            "message": "Cantidad y valor unitario deben ser números positivos"
        }
    }
}
```

#### 2.2 Listar Donaciones
- **Endpoint:** `GET /api/donaciones`
- **Descripción:** Obtener todas las donaciones registradas

**Caso de Prueba - DON-004: Listar Todas las Donaciones**
```json
{
    "endpoint": "/api/donaciones",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": [
                {
                    "id": "<id_1>",
                    "donante": "Empresa XYZ",
                    "tipo": "Dinero",
                    "cantidad": 100,
                    "valorUnitario": 50.00,
                    "subtotal": 5000.00
                }
            ],
            "total": 1
        }
    }
}
```

**Caso de Prueba - DON-005: Listar Donaciones sin Auth**
```json
{
    "endpoint": "/api/donaciones",
    "method": "GET",
    "expected": {
        "status": 401,
        "response": {
            "success": false,
            "message": "Token de autenticación requerido"
        }
    }
}
```

#### 2.3 Obtener Donación por ID
- **Endpoint:** `GET /api/donaciones/{id}`
- **Descripción:** Obtener detalles de una donación específica

**Caso de Prueba - DON-006: Obtener Donación Existente**
```json
{
    "endpoint": "/api/donaciones/<existing_id>",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": {
                "id": "<existing_id>",
                "donante": "Empresa XYZ",
                "tipo": "Dinero",
                "cantidad": 100,
                "valorUnitario": 50.00,
                "subtotal": 5000.00
            }
        }
    }
}
```

**Caso de Prueba - DON-007: Obtener Donación No Existente**
```json
{
    "endpoint": "/api/donaciones/non_existent_id",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 404,
        "response": {
            "success": false,
            "message": "Donación no encontrada"
        }
    }
}
```

#### 2.4 Actualizar Donación
- **Endpoint:** `PUT /api/donaciones/{id}`
- **Descripción:** Actualizar información de una donación

**Caso de Prueba - DON-008: Actualizar Donación Exitosa**
```json
{
    "endpoint": "/api/donaciones/<existing_id>",
    "method": "PUT",
    "headers": {
        "Authorization": "Bearer <valid_token>",
        "Content-Type": "application/json"
    },
    "body": {
        "donante": "Empresa XYZ Actualizada",
        "tipo": "Dinero",
        "cantidad": 150,
        "valorUnitario": 60.00
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "message": "Donación actualizada exitosamente",
            "data": {
                "id": "<existing_id>",
                "donante": "Empresa XYZ Actualizada",
                "tipo": "Dinero",
                "cantidad": 150,
                "valorUnitario": 60.00,
                "subtotal": 9000.00
            }
        }
    }
}
```

#### 2.5 Eliminar Donación
- **Endpoint:** `DELETE /api/donaciones/{id}`
- **Descripción:** Eliminar una donación del sistema

**Caso de Prueba - DON-009: Eliminar Donación Exitosa**
```json
{
    "endpoint": "/api/donaciones/<existing_id>",
    "method": "DELETE",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "message": "Donación eliminada exitosamente"
        }
    }
}
```

**Caso de Prueba - DON-010: Eliminar Donación No Existente**
```json
{
    "endpoint": "/api/donaciones/non_existent_id",
    "method": "DELETE",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 404,
        "response": {
            "success": false,
            "message": "Donación no encontrada"
        }
    }
}
```

---

### 3. Gestión de Usuarios

#### 3.1 Listar Usuarios
- **Endpoint:** `GET /api/usuarios`
- **Descripción:** Obtener lista de usuarios del sistema

**Caso de Prueba - USR-001: Listar Usuarios (Coordinadora)**
```json
{
    "endpoint": "/api/usuarios",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <coordinadora_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": [
                {
                    "id": "<id_1>",
                    "username": "tesorera",
                    "role": "Tesorera"
                },
                {
                    "id": "<id_2>",
                    "username": "coordinadora",
                    "role": "Coordinadora"
                }
            ]
        }
    }
}
```

**Caso de Prueba - USR-002: Listar Usuarios (Tesorera - Sin Permiso)**
```json
{
    "endpoint": "/api/usuarios",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <tesorera_token>"
    },
    "expected": {
        "status": 403,
        "response": {
            "success": false,
            "message": "No tiene permisos para realizar esta acción"
        }
    }
}
```

#### 3.2 Crear Usuario
- **Endpoint:** `POST /api/usuarios`
- **Descripción:** Registrar un nuevo usuario en el sistema

**Caso de Prueba - USR-003: Crear Usuario Exitoso**
```json
{
    "endpoint": "/api/usuarios",
    "method": "POST",
    "headers": {
        "Authorization": "Bearer <coordinadora_token>",
        "Content-Type": "application/json"
    },
    "body": {
        "username": "nuevo_usuario",
        "password": "password123",
        "role": "Tesorera"
    },
    "expected": {
        "status": 201,
        "response": {
            "success": true,
            "message": "Usuario creado exitosamente",
            "data": {
                "id": "<generated_id>",
                "username": "nuevo_usuario",
                "role": "Tesorera"
            }
        }
    }
}
```

**Caso de Prueba - USR-004: Crear Usuario con Username Existente**
```json
{
    "endpoint": "/api/usuarios",
    "method": "POST",
    "headers": {
        "Authorization": "Bearer <coordinadora_token>"
    },
    "body": {
        "username": "tesorera",
        "password": "password123",
        "role": "Tesorera"
    },
    "expected": {
        "status": 400,
        "response": {
            "success": false,
            "message": "El nombre de usuario ya existe"
        }
    }
}
```

#### 3.3 Actualizar Usuario
- **Endpoint:** `PUT /api/usuarios/{id}`
- **Descripción:** Actualizar información de un usuario

**Caso de Prueba - USR-005: Actualizar Usuario Exitoso**
```json
{
    "endpoint": "/api/usuarios/<existing_user_id>",
    "method": "PUT",
    "headers": {
        "Authorization": "Bearer <coordinadora_token>",
        "Content-Type": "application/json"
    },
    "body": {
        "username": "usuario_actualizado",
        "role": "Coordinadora"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "message": "Usuario actualizado exitosamente"
        }
    }
}
```

#### 3.4 Eliminar Usuario
- **Endpoint:** `DELETE /api/usuarios/{id}`
- **Descripción:** Eliminar un usuario del sistema

**Caso de Prueba - USR-006: Eliminar Usuario Exitoso**
```json
{
    "endpoint": "/api/usuarios/<existing_user_id>",
    "method": "DELETE",
    "headers": {
        "Authorization": "Bearer <coordinadora_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "message": "Usuario eliminado exitosamente"
        }
    }
}
```

**Caso de Prueba - USR-007: Eliminar Último Administrador**
```json
{
    "endpoint": "/api/usuarios/<last_admin_id>",
    "method": "DELETE",
    "headers": {
        "Authorization": "Bearer <coordinadora_token>"
    },
    "expected": {
        "status": 400,
        "response": {
            "success": false,
            "message": "No se puede eliminar el último administrador"
        }
    }
}
```

---

### 4. Egresos

#### 4.1 Registrar Egreso
- **Endpoint:** `POST /api/egresos`
- **Descripción:** Registrar un nuevo egreso

**Caso de Prueba - EGR-001: Registrar Egreso Exitoso**
```json
{
    "endpoint": "/api/egresos",
    "method": "POST",
    "headers": {
        "Authorization": "Bearer <tesorera_token>",
        "Content-Type": "application/json"
    },
    "body": {
        "descripcion": "Compra de suministros",
        "tipo": "Operativo",
        "monto": 150.00,
        "fecha": "2024-01-15"
    },
    "expected": {
        "status": 201,
        "response": {
            "success": true,
            "message": "Egreso registrado exitosamente",
            "data": {
                "id": "<generated_id>",
                "descripcion": "Compra de suministros",
                "tipo": "Operativo",
                "monto": 150.00,
                "fecha": "2024-01-15"
            }
        }
    }
}
```

**Caso de Prueba - EGR-002: Registrar Egreso con Monto Negativo**
```json
{
    "endpoint": "/api/egresos",
    "method": "POST",
    "headers": {
        "Authorization": "Bearer <tesorera_token>"
    },
    "body": {
        "descripcion": "Test",
        "tipo": "Operativo",
        "monto": -100.00,
        "fecha": "2024-01-15"
    },
    "expected": {
        "status": 400,
        "response": {
            "success": false,
            "message": "El monto debe ser un valor positivo"
        }
    }
}
```

#### 4.2 Listar Egresos
- **Endpoint:** `GET /api/egresos`
- **Descripción:** Obtener lista de egresos

**Caso de Prueba - EGR-003: Listar Egresos con Filtros**
```json
{
    "endpoint": "/api/egresos?fecha_inicio=2024-01-01&fecha_fin=2024-01-31",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <tesorera_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": [],
            "total": 0
        }
    }
}
```

#### 4.3 Categorías de Egreso
- **Endpoint:** `GET /api/egresos/categorias`
- **Descripción:** Obtener categorías disponibles de egresos

**Caso de Prueba - EGR-004: Listar Categorías**
```json
{
    "endpoint": "/api/egresos/categorias",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": [
                "Operativo",
                "Administrativo",
        "Emergencia",
                "Otro"
            ]
        }
    }
}
```

---

### 5. Reportes

#### 5.1 Reporte de Ingresos
- **Endpoint:** `GET /api/reportes/ingresos`
- **Descripción:** Generar reporte de ingresos por período

**Caso de Prueba - REP-001: Generar Reporte de Ingresos**
```json
{
    "endpoint": "/api/reportes/ingresos?fecha_inicio=2024-01-01&fecha_fin=2024-12-31",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": {
                "periodo": {
                    "inicio": "2024-01-01",
                    "fin": "2024-12-31"
                },
                "totalDonaciones": 0,
                "totalMonto": 0.00,
                "desglosePorTipo": []
            }
        }
    }
}
```

#### 5.2 Reporte de Balances
- **Endpoint:** `GET /api/reportes/balances`
- **Descripción:** Generar reporte de balance general

**Caso de Prueba - REP-002: Generar Reporte de Balances**
```json
{
    "endpoint": "/api/reportes/balances?fecha_inicio=2024-01-01&fecha_fin=2024-12-31",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": {
                "periodo": {
                    "inicio": "2024-01-01",
                    "fin": "2024-12-31"
                },
                "totalIngresos": 0.00,
                "totalEgresos": 0.00,
                "balance": 0.00
            }
        }
    }
}
```

#### 5.3 Gráficos Interactivos
- **Endpoint:** `GET /api/reportes/graficos`
- **Descripción:** Obtener datos para gráficos interactivos

**Caso de Prueba - REP-003: Datos para Gráfico de Donaciones por Tipo**
```json
{
    "endpoint": "/api/reportes/graficos/donaciones-tipo",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": {
                "labels": ["Dinero", "Alimentos", "Medicamentos", "Ropa"],
                "datasets": [{
                    "label": "Donaciones por Tipo",
                    "data": [0, 0, 0, 0]
                }]
            }
        }
    }
}
```

**Caso de Prueba - REP-004: Datos para Gráfico de Ingresos vs Egresos**
```json
{
    "endpoint": "/api/reportes/graficos/ingresos-egresos",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response": {
            "success": true,
            "data": {
                "labels": ["Enero", "Febrero", "Marzo"],
                "datasets": [
                    {
                        "label": "Ingresos",
                        "data": [0, 0, 0]
                    },
                    {
                        "label": "Egresos",
                        "data": [0, 0, 0]
                    }
                ]
            }
        }
    }
}
```

---

### 6. Exportación

#### 6.1 Exportar Reporte a Excel
- **Endpoint:** `GET /api/exportar/excel`
- **Descripción:** Exportar reporte en formato Excel

**Caso de Prueba - EXP-001: Exportar Reporte a Excel**
```json
{
    "endpoint": "/api/exportar/excel?tipo=ingresos&fecha_inicio=2024-01-01&fecha_fin=2024-12-31",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response_type": "file",
        "content_type": "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    }
}
```

#### 6.2 Exportar Reporte a PDF
- **Endpoint:** `GET /api/exportar/pdf`
- **Descripción:** Exportar reporte en formato PDF

**Caso de Prueba - EXP-002: Exportar Reporte a PDF**
```json
{
    "endpoint": "/api/exportar/pdf?tipo=balance&fecha_inicio=2024-01-01&fecha_fin=2024-12-31",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response_type": "file",
        "content_type": "application/pdf"
    }
}
```

#### 6.3 Exportar Donaciones a CSV
- **Endpoint:** `GET /api/exportar/csv`
- **Descripción:** Exportar donaciones en formato CSV

**Caso de Prueba - EXP-003: Exportar Donaciones a CSV**
```json
{
    "endpoint": "/api/exportar/csv",
    "method": "GET",
    "headers": {
        "Authorization": "Bearer <valid_token>"
    },
    "expected": {
        "status": 200,
        "response_type": "file",
        "content_type": "text/csv"
    }
}
```

---

## Matriz de Pruebas Resumida

| Módulo | Caso de Prueba | Método | Endpoint | Prioridad |
|--------|----------------|--------|----------|-----------|
| Auth | AUT-001: Login Tesorera | POST | /api/auth/login | Alta |
| Auth | AUT-002: Login Coordinadora | POST | /api/auth/login | Alta |
| Auth | AUT-003: Credenciales Inválidas | POST | /api/auth/login | Alta |
| Auth | AUT-004: Campos Vacíos | POST | /api/auth/login | Media |
| Auth | AUT-005: Token Válido | GET | /api/auth/validate | Alta |
| Auth | AUT-006: Token Inválido | GET | /api/auth/validate | Alta |
| Donaciones | DON-001: Registrar Donación | POST | /api/donaciones | Alta |
| Donaciones | DON-002: Campos Vacíos | POST | /api/donaciones | Alta |
| Donaciones | DON-003: Valores Inválidos | POST | /api/donaciones | Media |
| Donaciones | DON-004: Listar Donaciones | GET | /api/donaciones | Alta |
| Donaciones | DON-005: Sin Auth | GET | /api/donaciones | Alta |
| Donaciones | DON-006: Obtener por ID | GET | /api/donaciones/{id} | Media |
| Donaciones | DON-007: ID No Existente | GET | /api/donaciones/{id} | Media |
| Donaciones | DON-008: Actualizar | PUT | /api/donaciones/{id} | Media |
| Donaciones | DON-009: Eliminar | DELETE | /api/donaciones/{id} | Alta |
| Donaciones | DON-010: Eliminar No Existente | DELETE | /api/donaciones/{id} | Media |
| Usuarios | USR-001: Listar (Coordinadora) | GET | /api/usuarios | Alta |
| Usuarios | USR-002: Listar (Tesorera) | GET | /api/usuarios | Media |
| Usuarios | USR-003: Crear Usuario | POST | /api/usuarios | Alta |
| Usuarios | USR-004: Usuario Existente | POST | /api/usuarios | Media |
| Usuarios | USR-005: Actualizar Usuario | PUT | /api/usuarios/{id} | Media |
| Usuarios | USR-006: Eliminar Usuario | DELETE | /api/usuarios/{id} | Alta |
| Usuarios | USR-007: Eliminar Admin | DELETE | /api/usuarios/{id} | Media |
| Egresos | EGR-001: Registrar Egreso | POST | /api/egresos | Alta |
| Egresos | EGR-002: Monto Negativo | POST | /api/egresos | Media |
| Egresos | EGR-003: Listar con Filtros | GET | /api/egresos | Media |
| Egresos | EGR-004: Categorías | GET | /api/egresos/categorias | Media |
| Reportes | REP-001: Ingresos | GET | /api/reportes/ingresos | Alta |
| Reportes | REP-002: Balances | GET | /api/reportes/balances | Alta |
| Reportes | REP-003: Gráfico Tipo | GET | /api/reportes/graficos/donaciones-tipo | Media |
| Reportes | REP-004: Gráfico In/Eg | GET | /api/reportes/graficos/ingresos-egresos | Media |
| Exportar | EXP-001: Excel | GET | /api/exportar/excel | Media |
| Exportar | EXP-002: PDF | GET | /api/exportar/pdf | Media |
| Exportar | EXP-003: CSV | GET | /api/exportar/csv | Media |

---

## Variables de Entorno Postman

Crear las siguientes variables de entorno en Postman:

```json
{
    "base_url": "http://localhost:8080/api",
    "tesorera_token": "",
    "coordinadora_token": "",
    "donacion_id": "",
    "usuario_id": "",
    "egreso_id": ""
}
```

---

##  Flujos de Prueba Recomendados

### Flujo 1: Login y Autenticación
1. AUT-001 → AUT-002 → AUT-003 → AUT-004 → AUT-005 → AUT-006

### Flujo 2: Gestión Completa de Donaciones
1. AUT-001 (obtener token)
2. DON-001 (crear donación)
3. DON-004 (listar donaciones)
4. DON-006 (obtener por ID)
5. DON-008 (actualizar)
6. DON-009 (eliminar)

### Flujo 3: Gestión de Usuarios (Coordinadora)
1. AUT-002 (login coordinadora)
2. USR-001 (listar usuarios)
3. USR-003 (crear usuario)
4. USR-005 (actualizar usuario)
5. USR-006 (eliminar usuario)

### Flujo 4: Generación de Reportes
1. AUT-001 (obtener token)
2. REP-001 (reporte ingresos)
3. REP-002 (reporte balances)
4. REP-003 (gráfico donaciones)
5. REP-004 (gráfico ingresos/egresos)

### Flujo 5: Exportación de Datos
1. AUT-001 (obtener token)
2. EXP-001 (exportar Excel)
3. EXP-002 (exportar PDF)
4. EXP-003 (exportar CSV)

---

*Este documento será actualizado conforme evolucione el proyecto y se añadan nuevas funcionalidades.*

