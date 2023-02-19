package io.github.cedromaxxsoft.crudMongo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseProjecaoNomeComent {

    private String nome;
    private String comentarios;

}
