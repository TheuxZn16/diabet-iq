package theuxzn16.com.github.diabetiq.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import theuxzn16.com.github.diabetiq.dto.response.PerfilResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioUpdateSenhaDTO;
import theuxzn16.com.github.diabetiq.exception.TokenVerificacaoEmailInvalidoException;
import theuxzn16.com.github.diabetiq.service.UsuarioService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<PerfilResponseDTO> findById(@PathVariable UUID id){
        PerfilResponseDTO perfil = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(perfil);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<?> updatePassword(@PathVariable UUID id, @Valid @RequestBody UsuarioUpdateSenhaDTO body){
        service.updatePassword(id, body);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/verificar-email")
    public ResponseEntity<Void> verificarEmail(@RequestParam String token) {
        service.verificarEmail(token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
