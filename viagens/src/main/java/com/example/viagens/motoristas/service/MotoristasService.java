package com.example.viagens.motoristas.service;

import com.example.viagens.motoristas.model.Motoristas;
import com.example.viagens.motoristas.repository.MotoristasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristasService {
    @Autowired
    private MotoristasRepository motoristasRepository;

    public Motoristas cadastrarMotoristas(Motoristas motorista) {
        return motoristasRepository.save(motorista);
    }

    public List<Motoristas> listarMotoristas(String cpf) {
        if (cpf != null) {
            return motoristasRepository.findByCpf(cpf);
        }
        return motoristasRepository.findAll();
    }

    public Motoristas excluirMotorista(Motoristas motorista) {
        Motoristas motoristas = motoristasRepository.findById(id).orElse(null);
        if (motorista == null) {
            throw new RuntimeException("Motorista não encontrado!");
        }
        motoristasRepository.delete(motorista);
        return motorista;
    }

}
