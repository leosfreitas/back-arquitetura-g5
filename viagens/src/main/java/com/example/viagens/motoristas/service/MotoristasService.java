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
        motorista.setStatusOcupacao("DISPONIVEL");
        return motoristasRepository.save(motorista);
    }

    public List<Motoristas> listarMotoristas(String cpf) {
        if (cpf != null) {
            return motoristasRepository.findByCpf(cpf);
        }
        return motoristasRepository.findAll();
    }

    public void excluirMotorista(Integer id) {
        Motoristas motorista = motoristasRepository.findById(id).orElse(null);
        if (motorista == null) {
            throw new RuntimeException("Motorista não encontrado!");
        }
        motoristasRepository.delete(motorista);
    }

    public Motoristas editarMotorista(Integer id, Motoristas motorista) {
        Motoristas motoristaEditado = motoristasRepository.findById(id).orElse(null);
        if (motoristaEditado == null) {
            throw new RuntimeException("Motorista não encontrado!");
        }
        if (motorista.getNome() != null){
            motoristaEditado.setNome(motorista.getNome());}
        if (motorista.getCpf() != null){
            motoristaEditado.setCpf(motorista.getCpf());}
        if (motorista.getPlacaVeiculo() != null){
            motoristaEditado.setPlacaVeiculo(motorista.getPlacaVeiculo());}
        if (motorista.getModeloVeiculo() != null){
            motoristaEditado.setModeloVeiculo(motorista.getModeloVeiculo());}
        if (motorista.getPrecoViagem() != null){
            motoristaEditado.setPrecoViagem(motorista.getPrecoViagem());}

        return motoristasRepository.save(motoristaEditado);
    }

    public Motoristas retornaDisponivel() {
        for (Motoristas motorista : motoristasRepository.findAll()) {
            if (motorista.getStatusOcupacao().equals("DISPONIVEL")) {
                return motorista;
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
