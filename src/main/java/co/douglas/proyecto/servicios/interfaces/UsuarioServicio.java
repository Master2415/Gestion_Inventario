package co.douglas.proyecto.servicios.interfaces;

import co.douglas.proyecto.dto.usuario.DetalleUsuarioDTO;
import co.douglas.proyecto.dto.usuario.GuardarUsuarioDTO;
import co.douglas.proyecto.dto.usuario.ListarUsuarioDTO;

import java.util.List;

public interface UsuarioServicio {

    DetalleUsuarioDTO crearUsuario(GuardarUsuarioDTO usuarioDTO) throws Exception;

    DetalleUsuarioDTO modificarUsuario(int idUsuario, GuardarUsuarioDTO usuarioDTO) throws Exception;

    void eliminarUsuario(int idUsuario) throws Exception;

    DetalleUsuarioDTO obtenerUsuario(int idUsuario) throws Exception;

    List<ListarUsuarioDTO> listarUsuarios() throws Exception;

    DetalleUsuarioDTO autenticarUsuario(String correo, String contrasena) throws Exception;
}
