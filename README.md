Proyecto 'actividad2jc' - Backend con Reset Password API
====================================

Instrucciones rápidas:

1) Importar en NetBeans:
   - File -> Open Project -> seleccionar carpeta descomprimida (tiene pom.xml)

2) Ejecutar:
   - Run (NetBeans) o `mvn spring-boot:run` desde la carpeta del proyecto.

3) Probar flujo: proeba en postman 
   - Registrar: POST /api/auth/register  body: {"email":"u@e.com","password":"123456"}
   - Solicitar reset: abrir http://localhost:8080/reset-request.html o POST /api/auth/reset-request with {"email":"u@e.com"}
       -> revisa logs: simulamos envío de email con link: http://localhost:8080/reset-form.html?token=...
   - Abrir link y cambiar contraseña.

Notas:
- El envío de correos está simulado por LoggingEmailSender (logs).
- Se usa H2 in-memory para desarrollo.
- Está organizado con una estructura hexagonal (dominio, puertos, adaptadores).
