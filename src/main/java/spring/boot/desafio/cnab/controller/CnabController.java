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
        // Adicionado mais logs para verificar o estado da requisição
        System.out.println("Entrou no endpoint de upload.");

        if (file == null) {
            System.out.println("Nenhum arquivo enviado na requisição.");
            return ResponseEntity.badRequest().body("Nenhum arquivo enviado.");
        }

        if (file.isEmpty()) {
            System.out.println("Arquivo enviado está vazio.");
            return ResponseEntity.badRequest().body("O arquivo está vazio.");
        }

        System.out.println("Arquivo recebido: " + file.getOriginalFilename());
        System.out.println("Tamanho do arquivo: " + file.getSize());

        return ResponseEntity.ok("Arquivo recebido e processado com sucesso.");
    }

    @GetMapping("/transacao")
    public List<Transacao> listarTransacoes() {
        return cnapService.listarTransacoes();
    }
}