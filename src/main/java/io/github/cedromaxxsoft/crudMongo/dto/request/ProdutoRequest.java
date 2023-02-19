package io.github.cedromaxxsoft.crudMongo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.cedromaxxsoft.crudMongo.entities.enums.Categorias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Categorias categoria;
    private BigDecimal preco;
    private Integer avaliacao;
    private String comentarios;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;


}
