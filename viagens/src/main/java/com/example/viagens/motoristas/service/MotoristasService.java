package com.example.viagens.motoristas.service;

import com.example.viagens.motoristas.model.Motoristas;
import com.example.viagens.motoristas.repository.MotoristasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Motoristas excluirMotorista(Integer id) {
        Motoristas motorista = motoristasRepository.findById(id).orElse(null);
        if (motorista == null) {
            throw new RuntimeException("Motorista não encontrado!");
        }
        motoristasRepository.delete(motorista);
        return motorista;
    }

    public Motoristas editarMotorista(Integer id, String nome, String cpf, String placaVeiculo, String modeloVeiculo, Double precoViagem) {
        Motoristas motorista = motoristasRepository.findById(id).orElse(null);
        if (motorista == null) {
            throw new RuntimeException("Motorista não encontrado!");
        }

        if (nome != null) {
            motorista.setNome(nome);
        }
        if (cpf != null) {
            motorista.setCpf(cpf);
        }
        if (placaVeiculo != null) {
            motorista.setPlacaVeiculo(placaVeiculo);
        }
        if (modeloVeiculo != null) {
            motorista.setModeloVeiculo(modeloVeiculo);
        }  
        if (precoViagem != null) {
            motorista.setPrecoViagem(precoViagem);
        }

        return motoristasRepository.save(motorista);
    }

    public Motoristas retornaDisponivel() {
        for (Motoristas motorista : motoristasRepository.findAll()) {
            if (motorista.getStatusOcupacao().equals("DISPONIVEL")) {
                List<Motoristas> motoristas = motoristasRepository.findAll();
                return motoristas.getFirst();
            }
        }
        throw new RuntimeException("Nenhum motorista disponível!");
    }

    public String retornaDisponibilidade(Integer id){
        Optional<Motoristas> motorista = motoristasRepository.findById(id);
        if (motorista.isEmpty()){
            throw new RuntimeException("Motorista não encontrado!");
        }
        if (motorista.get().getStatusOcupacao().equals("DISPONIVEL")){
            return motorista.get().getStatusOcupacao();
        }
        else {
            return motorista.get().getStatusOcupacao(); // INDISPONIVEL
        }
    }

    public Motoristas mudaStatus(Integer id){ // SÓ INDISPONIVEL PARA DISPONIVEL
        Optional<Motoristas>  motorista = motoristasRepository.findById(id);
        if (motorista.isEmpty()){
            throw new RuntimeException("Motorista não encontrado!");

        }
        if (motorista.get().getStatusOcupacao().equals("DISPONIVEL")){
            throw new RuntimeException("Motorista já disponível!");
        }
        else {
            motorista.get().setStatusOcupacao("DISPONIVEL");
            return motoristasRepository.save(motorista.get());
        }
    }

    public Motoristas mudaStatusString(Integer id, String status){
        Optional<Motoristas>  motorista = motoristasRepository.findById(id);
        if (motorista.isEmpty()){
            throw new RuntimeException("Motorista não encontrado!");
        }
        if (status.equals("DISPONIVEL") && motorista.get().getStatusOcupacao().equals("INDISPONIVEL")) {
            motorista.get().setStatusOcupacao(status);
            return motoristasRepository.save(motorista.get());
        }
        if (status.equals("INDISPONIVEL") && motorista.get().getStatusOcupacao().equals("DISPONIVEL")) {
            motorista.get().setStatusOcupacao(status);
            return motoristasRepository.save(motorista.get());
        }
        else {
            throw new RuntimeException("Status inválido!");
        }
    }
}
