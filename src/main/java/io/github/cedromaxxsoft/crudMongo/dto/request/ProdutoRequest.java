package io.github.cedromaxxsoft.crudMongo.dto.request;

import io.github.cedromaxxsoft.crudMongo.entities.enums.Categorias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {

    private String Nome;
    private String Descrição;
    private String Marca;
    private Categorias Categoria;
    private BigDecimal Preço;
    private Integer Avaliação;

    private String Comentários;

}
