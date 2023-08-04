package com.fernandes.dto;

import java.time.LocalDate;

import com.fernandes.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {
    
    private Long id;

    @NotBlank(message = "Campo requerido")
    private String name;
    private String cpf;
    private Double income;

    @PastOrPresent
    private LocalDate birthDate;
    
    private Integer children;
    

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client inst){
        id = inst.getId();
        name = inst.getName();
        income = inst.getIncome();
        birthDate = inst.getBirthDate();
        children = inst.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
    
}
