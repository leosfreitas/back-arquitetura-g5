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

    public Motoristas editarMotorista(Integer id, String nome, String cpf, String placaVeiculo, String modeloVeiculo, double precoViagem, String statusOcupacao) {
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
        if (precoViagem != 0) {
            motorista.setPrecoViagem(precoViagem);
        }
        if (statusOcupacao != null) {
            motorista.setStatusOcupacao(statusOcupacao);
        }

        return motoristasRepository.save(motorista);
    }

    public Motoristas retornaDisponivel(){
        return motoristasRepository.findByStatusOcupacao("DISPONIVEL");
    }

    public Motoristas mudaStatus(Integer id){
        Optional<Motoristas>  motorista = motoristasRepository.findById(id);
        if(motorista.isEmpty()){
            throw new RuntimeException("Motorista não encontrado!");

        }
        if(motorista.get().getStatusOcupacao().equals("DISPONIVEL")){
            throw new RuntimeException("Motorista já disponível!");
        }
        else{
            motorista.get().setStatusOcupacao("DISPONIVEL");
            return motoristasRepository.save(motorista.get());
        }


    }
}
