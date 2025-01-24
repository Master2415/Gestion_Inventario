package co.douglas.proyecto.servicios.implementaciones;

import co.douglas.proyecto.dto.usuario.DetalleUsuarioDTO;
import co.douglas.proyecto.dto.usuario.GuardarUsuarioDTO;
import co.douglas.proyecto.dto.usuario.ListarUsuarioDTO;
import co.douglas.proyecto.modelo.entidades.Usuario;
import co.douglas.proyecto.modelo.entidades.Empleado;
import co.douglas.proyecto.repositorios.UsuarioRepo;
import co.douglas.proyecto.repositorios.EmpleadoRepo;
import co.douglas.proyecto.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final EmpleadoRepo empleadoRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, EmpleadoRepo empleadoRepo, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.empleadoRepo = empleadoRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public DetalleUsuarioDTO crearUsuario(GuardarUsuarioDTO usuarioDTO) throws Exception {
        Optional<Empleado> empleado = empleadoRepo.findById(usuarioDTO.idEmpleado());
        if (empleado.isEmpty()) {
            throw new Exception("Empleado no encontrado con ID: " + usuarioDTO.idEmpleado());
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCorreo(usuarioDTO.correo());
        nuevoUsuario.setContrasena(passwordEncoder.encode(usuarioDTO.contrasena()));
        nuevoUsuario.setEstado(usuarioDTO.estado());
        nuevoUsuario.setEmpleado(empleado.get());

        Usuario usuarioGuardado = usuarioRepo.save(nuevoUsuario);

        return new DetalleUsuarioDTO(
                usuarioGuardado.getIdUsuario(),
                usuarioGuardado.getCorreo(),
                usuarioGuardado.getEstado(),
                usuarioGuardado.getEmpleado().getIdEmpleado()
        );
    }

    @Override
    public DetalleUsuarioDTO modificarUsuario(int idUsuario, GuardarUsuarioDTO usuarioDTO) throws Exception {
        Optional<Usuario> usuarioExistente = usuarioRepo.findById(idUsuario);
        if (usuarioExistente.isEmpty()) {
            throw new Exception("Usuario no encontrado con ID: " + idUsuario);
        }

        Usuario usuario = usuarioExistente.get();
        usuario.setCorreo(usuarioDTO.correo());
        if (usuarioDTO.contrasena() != null && !usuarioDTO.contrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(usuarioDTO.contrasena()));
        }
        usuario.setEstado(usuarioDTO.estado());

        Usuario usuarioActualizado = usuarioRepo.save(usuario);

        return new DetalleUsuarioDTO(
                usuarioActualizado.getIdUsuario(),
                usuarioActualizado.getCorreo(),
                usuarioActualizado.getEstado(),
                usuarioActualizado.getEmpleado().getIdEmpleado()
        );
    }

    @Override
    public void eliminarUsuario(int idUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);
        if (usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado con ID: " + idUsuario);
        }
        usuarioRepo.delete(usuario.get());
    }

    @Override
    public DetalleUsuarioDTO obtenerUsuario(int idUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);
        if (usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado con ID: " + idUsuario);
        }

        Usuario usuarioEncontrado = usuario.get();
        return new DetalleUsuarioDTO(
                usuarioEncontrado.getIdUsuario(),
                usuarioEncontrado.getCorreo(),
                usuarioEncontrado.getEstado(),
                usuarioEncontrado.getEmpleado().getIdEmpleado()
        );
    }

    @Override
    public List<ListarUsuarioDTO> listarUsuarios() throws Exception {
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<ListarUsuarioDTO> listaUsuarios = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            listaUsuarios.add(new ListarUsuarioDTO(
                    usuario.getIdUsuario(),
                    usuario.getCorreo(),
                    usuario.getEstado()
            ));
        }

        return listaUsuarios;
    }

    @Override
    public DetalleUsuarioDTO autenticarUsuario(String correo, String contrasena) throws Exception {
        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new Exception("Usuario no encontrado con el correo: " + correo));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new Exception("Contraseña incorrecta");
        }

        return new DetalleUsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getCorreo(),
                usuario.getEstado(),
                usuario.getEmpleado().getIdEmpleado()
        );
    }
}
