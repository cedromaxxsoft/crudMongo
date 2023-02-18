package io.github.cedromaxxsoft.crudMongo.service.impl;

import io.github.cedromaxxsoft.crudMongo.config.ModelMapperConfig;
import io.github.cedromaxxsoft.crudMongo.dto.request.ProdutoRequest;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponse;
import io.github.cedromaxxsoft.crudMongo.entities.Produto;
import io.github.cedromaxxsoft.crudMongo.repository.ProdutoRepository;
import io.github.cedromaxxsoft.crudMongo.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ModelMapperConfig modelMapper;

    @Override
    public List<ProdutoResponse> listarTodosProdutos() {
        return produtoRepository.findAll().stream().map(this::dto).collect(Collectors.toList());
    }

    @Override
    public Optional<ProdutoResponse> listaUmProduto(String id) {
        return produtoRepository.findById(id).map(this::dto);
    }

    @Override
    public ProdutoResponse salvaProduto(ProdutoRequest produto) {
        Produto produt = produtoRepository.save(modelMapper.mapper().map(produto,Produto.class));
        return modelMapper.mapper().map(produt,ProdutoResponse.class);
    }

    @Override
    public ProdutoResponse AlteraProduto(String id, ProdutoRequest produto) {
        return null;
    }

    @Override
    public void deletaProduto() {

    }

    public ProdutoResponse dto (Produto produto){
        return modelMapper.mapper().map(produto,ProdutoResponse.class);
    }
}
