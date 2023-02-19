package io.github.cedromaxxsoft.crudMongo.controller;

import io.github.cedromaxxsoft.crudMongo.config.ModelMapperConfig;
import io.github.cedromaxxsoft.crudMongo.dto.request.ProdutoRequest;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponse;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponseProjecaoNomeComent;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponseProjecaoNomePreco;
import io.github.cedromaxxsoft.crudMongo.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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


    @GetMapping("/produtos/valor")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponse>listaPorPreco(@RequestParam BigDecimal preco){
        return produtoService.produtosPorPreço(preco);
    }

    @GetMapping("/produtos/marca/")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponse>listaPorMarca(@RequestParam String marca){
        return produtoService.produtosPorMarca(marca);
    }

    @GetMapping("/produtos/categoria")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponse>listaPorCategoria(@RequestParam String categoria){
        return produtoService.produtosPorCategoria(categoria);
    }

    @GetMapping("/produtos/filtroData")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponse>listaPorData(@RequestParam String data){
        return produtoService.produtosPorData(data);
    }

    //Projeções

    @GetMapping("/projecao/nomeAndPreco")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseProjecaoNomePreco>listaProjecaoNomePreco(@RequestParam BigDecimal value){
        return produtoService.produtosTrazNomeValor(value);
    }

    @GetMapping("/projecao/nomeAndComentarios")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseProjecaoNomeComent>listaProjecaoNomeComentarios(@RequestParam String marca){
        return produtoService.produtosTrazNomeComentarios(marca);
    }


}
