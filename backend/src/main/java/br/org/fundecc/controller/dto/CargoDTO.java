package br.org.fundecc.controller.dto;

import br.org.fundecc.model.Cargo;

public class CargoDTO {
    private Long key;
    private String nome;

    public static CargoDTO Converter(Cargo c) {
        var dto = new CargoDTO();

        dto.setKey(c.getId());
        dto.setNome(c.getNome());

        return dto;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}