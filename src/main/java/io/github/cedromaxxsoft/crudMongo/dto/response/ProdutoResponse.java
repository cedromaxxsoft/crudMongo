package io.github.cedromaxxsoft.crudMongo.dto.response;

import io.github.cedromaxxsoft.crudMongo.entities.enums.Categorias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {


    private String id;
    private String nome;
    private String descricao;
    private String marca;
    private Categorias categoria;
    private BigDecimal preco;
    private Integer avaliacao;

    private String comentarios;
    private LocalDate createdAt;

    public ProdutoResponse(String nome, String comentarios) {
        this.nome = nome;
        this.comentarios = comentarios;
    }
}
