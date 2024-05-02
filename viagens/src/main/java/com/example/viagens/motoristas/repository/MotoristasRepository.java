package com.example.viagens.motoristas.repository;

import com.example.viagens.motoristas.model.Motoristas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoristasRepository extends JpaRepository<Motoristas, Integer> {

    Motoristas findByNome(String nome);

    List<Motoristas> findByCpf(String cpf);

    Motoristas findByPlacaVeiculo(String placaVeiculo);

    Motoristas findByModeloVeiculo(String modeloVeiculo);

    Motoristas findByStatusOcupacao(String statusOcupacao);
}