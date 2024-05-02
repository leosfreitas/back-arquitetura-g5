package com.example.viagens.motoristas.controller;


import com.example.viagens.motoristas.model.Motoristas;
import com.example.viagens.motoristas.service.MotoristasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MotoristasController {

    @Autowired
    private MotoristasService motoristasService;

    @GetMapping("/motoristas")
    public List<Motoristas> getMotoristas(@RequestParam(required = false) String cpf) {
        return motoristasService.listarMotoristas(cpf);
    }
    @PostMapping("/motoristas")
    @ResponseStatus(HttpStatus.CREATED)
    public Motoristas salvarMotoristas(@RequestBody Motoristas motoristas) {
        return motoristasService.cadastrarMotoristas(motoristas);
    }

    @DeleteMapping("/motoristas/{id}")
    public Motoristas deleteMotoristas(@RequestParam(required = true) Integer id) {
        return motoristasService.excluirMotorista(id);
    }

    @PutMapping("/motoristas/{id}")
    public Motoristas editMotoristas(@PathVariable Integer id,
                                     @RequestParam String nome,
                                     @RequestParam String cpf,
                                     @RequestParam String placaVeiculo,
                                     @RequestParam String modeloVeiculo,
                                     @RequestParam double precoViagem,
                                     @RequestParam String statusOcupacao){
        return motoristasService.editarMotorista(id, nome, cpf, placaVeiculo, modeloVeiculo, precoViagem, statusOcupacao);
    }

    @GetMapping("motoristas/disponivel")
    public Motoristas retornaDisponivel(){
        return motoristasService.retornaDisponivel();
    }

    @PutMapping("motoristas/{id}/status")
    public Motoristas mudaStatus(@PathVariable Integer id){
        return motoristasService.mudaStatus(id);
    }

}
