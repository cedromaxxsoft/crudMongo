package io.github.cedromaxxsoft.crudMongo.service;

import io.github.cedromaxxsoft.crudMongo.dto.request.ProdutoRequest;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponse;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponseProjecaoNomeComent;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponseProjecaoNomePreco;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<ProdutoResponse>listarTodosProdutos();
    Optional<ProdutoResponse>listaUmProduto(@PathVariable String id);
    ProdutoResponse salvaProduto(@RequestBody ProdutoRequest produto);
    ProdutoResponse AlteraProduto(@PathVariable String id,@RequestBody ProdutoRequest produto);
    void deletaProduto();

    //Operações usando Agregações

    //1 - Pesquisa baseado preço do produto
    List<ProdutoResponse>produtosPorPreço(@PathVariable BigDecimal preco);

    //2 - Pesquisa baseado na marca do produto
    List<ProdutoResponse>produtosPorMarca(@RequestParam String marca);
    //3 - Pesquisa baseada na categoria de produto
    List<ProdutoResponse>produtosPorCategoria(@RequestParam String categoria);

    //4 - Pesquisa por data de criação do produto(?)
    List<ProdutoResponse>produtosPorData(@RequestParam String data);

    //PROJEÇÃO

    //5 - Baseado na data ele traz somento o nome e valor do produto
    List<ProdutoResponseProjecaoNomePreco>produtosTrazNomeValor(@RequestParam BigDecimal value);


    //6 - trás somentos o nome do produto e os comentarios

    List<ProdutoResponseProjecaoNomeComent>produtosTrazNomeComentarios(@RequestParam String marca);



}
