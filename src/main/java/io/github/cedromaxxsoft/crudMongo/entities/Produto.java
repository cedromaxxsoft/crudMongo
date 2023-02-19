package io.github.cedromaxxsoft.crudMongo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.cedromaxxsoft.crudMongo.entities.enums.Categorias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private String marca;
    private Categorias categoria;
    private BigDecimal preco;
    private Integer avaliacao;

    private String comentarios;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    public Produto(String nome, String comentarios) {
        this.nome = nome;
        this.comentarios = comentarios;
    }
}
