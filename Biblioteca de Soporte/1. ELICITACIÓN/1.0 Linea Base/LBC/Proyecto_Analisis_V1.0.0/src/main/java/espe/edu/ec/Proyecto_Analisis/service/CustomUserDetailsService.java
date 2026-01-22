package espe.edu.ec.Proyecto_Analisis.service;

import espe.edu.ec.Proyecto_Analisis.entity.Usuario;
import espe.edu.ec.Proyecto_Analisis.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String rol = (usuario.getRol() == null || usuario.getRol().isEmpty()) ? "USER" : usuario.getRol();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + rol);

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                Collections.singletonList(authority)
        );
    }
}