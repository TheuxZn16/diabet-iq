package theuxzn16.com.github.diabetiq.dto.mappers;

import  theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioMedicoRequestDTO;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.enums.TipoUsuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toDto(Usuario user){
        return new UsuarioResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getNome(),
                user.getTipoUsuario(),
                user.getEmailVerificado(),
                user.getUpdatedAt(),
                user.getCreatedAt()
                );
    }

    public static Usuario toEntityMedico(UsuarioMedicoRequestDTO dto){
        return new Usuario(
                dto.nome(),
                dto.email(),
                dto.senha(),
                TipoUsuario.MEDICO
        );
    }

    public static Usuario toEntityPaciente(UsuarioMedicoRequestDTO dto){
        return new Usuario(
                dto.nome(),
                dto.email(),
                dto.senha(),
                TipoUsuario.PACIENTE
        );
    }
}
