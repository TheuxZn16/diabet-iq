package theuxzn16.com.github.diabetiq.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theuxzn16.com.github.diabetiq.dto.resquest.LoginRequestDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.RefreshTokenRequestDTO;
import theuxzn16.com.github.diabetiq.dto.response.TokenResponseDTO;
import theuxzn16.com.github.diabetiq.service.AutenticacaoService;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(autenticacaoService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refresh(@Valid @RequestBody RefreshTokenRequestDTO request) {
        return ResponseEntity.ok(autenticacaoService.refresh(request));
    }
}
