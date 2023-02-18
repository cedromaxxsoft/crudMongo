package io.github.cedromaxxsoft.crudMongo.entities.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public enum Categorias {
    CALCADOS("Calçados"),
    ROUPAS("Roupas"),
    ACESSORIOS("Acessorios");

    private String categoria;

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
