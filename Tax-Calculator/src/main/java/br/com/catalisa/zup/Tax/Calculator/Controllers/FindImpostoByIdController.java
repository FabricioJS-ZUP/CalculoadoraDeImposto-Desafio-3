package br.com.catalisa.zup.Tax.Calculator.Controllers;

import br.com.catalisa.zup.Tax.Calculator.DTOs.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Services.FindImpostoById;
import br.com.catalisa.zup.Tax.Calculator.Services.ListAllImpostos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/impostos/find")
public class FindImpostoByIdController {
    private final FindImpostoById findImpostoById;
    private final ListAllImpostos listAllImpostos;

    public FindImpostoByIdController(FindImpostoById findImpostoById, ListAllImpostos listAllImpostos) {
        this.findImpostoById = findImpostoById;
        this.listAllImpostos = listAllImpostos;
    }

    @GetMapping("/Alls")
    public ResponseEntity<List<TaxDTO>> listarimpostos(){
        return ResponseEntity.ok(listAllImpostos.listarimpostos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaxDTO> buscarimposto(@PathVariable Long id) {
        try {
            TaxDTO taxDTO = findImpostoById.searchid(id);
            return ResponseEntity.ok(taxDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

}