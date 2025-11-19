package mx.com.ubam.isc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mx.com.ubam.isc.model.Usuario;
import mx.com.ubam.isc.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Registramos usuario y se publica el servicio
    @PostMapping("/registrar")
    public Map<String, String> registrar(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrarUsuario(usuario.getNombre(), usuario.getContrasena());
        return Map.of(
            "mensaje", "Usuario registrado correctamente",
            "nombre", nuevo.getNombre(),
            "id", nuevo.getId().toString()
        );
    }

    // Iniciamos sesion y se publica el srvicio
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario usuario) {
        boolean valido = usuarioService.verificarUsuario(usuario.getNombre(), usuario.getContrasena());
        if (valido) {
            return Map.of("mensaje", "Inicio de sesión exitoso. ¡Bienvenido " + usuario.getNombre() + "!");
        } else {
            return Map.of("mensaje", "Usuario o contraseña incorrectos.");
        }
    }

    // mostramos usuarios y se publica el servicio
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }
}
