Proyecto 'actividad2jc' - Backend con Reset Password API
====================================

Instrucciones rápidas:

1) proba flujo: prueba en postman ENTIDAD GASTOS
   POST: POST http://localhost:8080/api/gastos
   {
  "fecha": "2025-11-23",
  "valorTotalSinIVA": 200000,
  "ivaTotal": 29000,
  "valorTotalConIVA": 119000,
  "nombreUsuario": "maria",
  "lugar": "Supermercado",
  "descripcion": "Compras del mes"
}

   GET: listar gastos http://localhost:8080/api/gastos
   GET buscar por ID http://localhost:8080/api/gastos/1
   DELETE: eliminar usuario http://localhost:8080/api/gastos/1
3) Probar flujo: proeba en postman 
   - Registrar: POST /api/auth/register  body: {"email":"u@e.com","password":"123456"}
   - Solicitar reset: abrir http://localhost:8080/reset-request.html o POST /api/auth/reset-request with {"email":"u@e.com"}
       -> revisa logs: simulamos envío de email con link: http://localhost:8080/reset-form.html?token=...
   - Abrir link y cambiar contraseña.


Notas:
- El envío de correos está simulado por LoggingEmailSender (logs).
- Se usa H2 in-memory para desarrollo.
- Está organizado con una estructura hexagonal (dominio, puertos, adaptadores).
