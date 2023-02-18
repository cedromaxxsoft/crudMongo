package io.github.cedromaxxsoft.crudMongo.service;

import io.github.cedromaxxsoft.crudMongo.dto.request.ProdutoRequest;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<ProdutoResponse>listarTodosProdutos();
    Optional<ProdutoResponse>listaUmProduto(@PathVariable String id);
    ProdutoResponse salvaProduto(@RequestBody ProdutoRequest produto);
    ProdutoResponse AlteraProduto(@PathVariable String id,@RequestBody ProdutoRequest produto);
    void deletaProduto();
}
