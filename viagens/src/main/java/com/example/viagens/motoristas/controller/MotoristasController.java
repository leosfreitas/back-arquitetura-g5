package com.example.viagens.motoristas.controller;


import com.example.viagens.motoristas.model.Motoristas;
import com.example.viagens.motoristas.service.MotoristasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/motoristas/exclusao/{id}")
    public void deleteMotoristas(@PathVariable Integer id) {
        motoristasService.excluirMotorista(id); }

    @PutMapping("/motoristas/{id}")
    public Motoristas editarMotorista(@PathVariable Integer id, @RequestBody Motoristas motoristas) {
        return motoristasService.editarMotorista(id, motoristas);
    }

    @GetMapping("motoristas/disponivel")
    public Motoristas retornaDisponivel(){
        return motoristasService.retornaDisponivel();
    }

    @GetMapping("motoristas/disponibilidade/{id}")
    public String retornaDisponibilidade(@PathVariable Integer id){
        return motoristasService.retornaDisponibilidade(id);
    }

    @PutMapping("motoristas/{id}/status")
    public Motoristas mudaStatus(@PathVariable Integer id){
        return motoristasService.mudaStatus(id);
    }

    @PutMapping("motoristas/status/{id}/key/{status}")
    public Motoristas mudaStatusKey(@PathVariable Integer id, @PathVariable String status){
        return motoristasService.mudaStatusString(id, status);
    }

}
