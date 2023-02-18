package io.github.cedromaxxsoft.crudMongo.entities;

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
    private String ID;
    private String Nome;
    private String Descrição;
    private String Marca;
    private Categorias Categoria;
    private BigDecimal Preço;
    private Integer Avaliação;
    //avaliação do produto (em estrelas);
    private String Comentários;
    //comentários dos clientes sobre o produto;
    private LocalDate createdAt;

    public Produto(LocalDate createdAt) {
        this.createdAt = LocalDate.now();
    }
}
