package mx.com.ubam.isc.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mx.com.ubam.isc.model.Usuario;
import mx.com.ubam.isc.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Registramos usuario
    public Usuario registrarUsuario(String nombre, String contrasena) {
        String contrasenaEncriptada = encoder.encode(contrasena);
        Usuario usuario = new Usuario(nombre, contrasenaEncriptada);
        return usuarioRepository.save(usuario);
    }

    // mostramos usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Verificamos login
    public boolean verificarUsuario(String nombre, String contrasena) {
        Optional<Usuario> usuario = usuarioRepository.findByNombre(nombre);
        if (usuario.isPresent()) {
            return encoder.matches(contrasena, usuario.get().getContrasena());
        }
        return false;
    }
}
