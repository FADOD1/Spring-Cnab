package spring.boot.desafio.cnab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.boot.desafio.cnab.model.Transacao;
import spring.boot.desafio.cnab.service.CnapService;

import java.util.List;

@RestController
@RequestMapping("/api/cnab")
public class CnabController {

    private final CnapService cnapService;

    public CnabController(CnapService cnapService) {
        this.cnapService = cnapService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        cnapService.arquivoProcessar(file);
        return ResponseEntity.ok("Arquivo processado com sucesso");
    }

    @GetMapping("/transacao")
    public List<Transacao> listarTransacoes() {
        return cnapService.listarTransacoes();
    }
}