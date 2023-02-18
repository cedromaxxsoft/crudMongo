package io.github.cedromaxxsoft.crudMongo.controller;

import io.github.cedromaxxsoft.crudMongo.config.ModelMapperConfig;
import io.github.cedromaxxsoft.crudMongo.dto.request.ProdutoRequest;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponse;
import io.github.cedromaxxsoft.crudMongo.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ModelMapperConfig modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponse> listarTodos(){
        return produtoService.listarTodosProdutos();
    }

    @GetMapping("/produto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProdutoResponse>listUmProduto(@PathVariable String id){
        return produtoService.listaUmProduto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse salvandoProduto(@RequestBody ProdutoRequest request){
        return produtoService.salvaProduto(request);
    }


    @PutMapping("/produto/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProdutoResponse alterandoProduto(@PathVariable String id,@RequestBody ProdutoRequest request){
        return produtoService.salvaProduto(request);
    }

    @DeleteMapping("/produ/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaProduto(@PathVariable String id){
        produtoService.deletaProduto();
    }






}
