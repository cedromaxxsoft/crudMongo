package io.github.cedromaxxsoft.crudMongo.dto.response;

import io.github.cedromaxxsoft.crudMongo.entities.enums.Categorias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {

    private String ID;
    private String Nome;
    private String Descrição;
    private String Marca;
    private Categorias Categoria;
    private BigDecimal Preço;
    private Integer Avaliação;
    private String Comentários;
    private LocalDate createdAt;
}
