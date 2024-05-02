package com.example.viagens.motoristas.model;

import jakarta.persistence.*;

@Entity
public class Motoristas {
    @RequestParam(required = false) String nome,
    @RequestParam(required = false) String cpf,
    @RequestParam(required = false) String placaVeiculo,
    @RequestParam(required = false) String modeloVeiculo,
    @RequestParam(required = false) double precoViagem,
    @RequestParam(required = false) String statusOcupacao)
    @Column(nullable = false)
    private double precoViagem;
    @Column(nullable = false)
    private String statusOcupacao;

    public Motoristas() {
    }

    public Motoristas(Integer id, String nome, String cpf, String placaVeiculo, String modeloVeiculo, double precoViagem, String statusOcupacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.placaVeiculo = placaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.precoViagem = precoViagem;
        this.statusOcupacao = statusOcupacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public double getPrecoViagem() {
        return precoViagem;
    }

    public void setPrecoViagem(double precoViagem) {
        this.precoViagem = precoViagem;
    }

    public String getStatusOcupacao() {
        return statusOcupacao;
    }

    public void setStatusOcupacao(String statusOcupacao) {
        this.statusOcupacao = statusOcupacao;
    }
}
