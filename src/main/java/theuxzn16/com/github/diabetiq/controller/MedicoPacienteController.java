package theuxzn16.com.github.diabetiq.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theuxzn16.com.github.diabetiq.dto.response.MedicoPacienteResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.MedicoPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.service.MedicoPacienteService;

@RestController
@RequestMapping("/api/v1/medico-paciente")
public class MedicoPacienteController {
    private final MedicoPacienteService medicoPacienteService;


    public MedicoPacienteController(MedicoPacienteService medicoPacienteService) {
        this.medicoPacienteService = medicoPacienteService;
    }

    @PostMapping
    public ResponseEntity<MedicoPacienteResponseDTO> create(@Valid @RequestBody MedicoPacienteRequestDTO body){
        var response = medicoPacienteService.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
