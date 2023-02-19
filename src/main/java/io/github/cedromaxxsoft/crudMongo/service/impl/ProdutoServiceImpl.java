package io.github.cedromaxxsoft.crudMongo.service.impl;

import io.github.cedromaxxsoft.crudMongo.config.ModelMapperConfig;
import io.github.cedromaxxsoft.crudMongo.dto.request.ProdutoRequest;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponse;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponseProjecaoNomeComent;
import io.github.cedromaxxsoft.crudMongo.dto.response.ProdutoResponseProjecaoNomePreco;
import io.github.cedromaxxsoft.crudMongo.entities.Produto;
import io.github.cedromaxxsoft.crudMongo.repository.ProdutoRepository;
import io.github.cedromaxxsoft.crudMongo.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ModelMapperConfig modelMapper;

    private final MongoTemplate mongoTemplate;

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
        produto.setCreatedAt(LocalDate.now());
        Produto produt = produtoRepository.save(modelMapper.mapper().map(produto, Produto.class));
        return modelMapper.mapper().map(produt, ProdutoResponse.class);
    }

    @Override
    public ProdutoResponse AlteraProduto(String id, ProdutoRequest produto) {
        return null;
    }

    @Override
    public void deletaProduto() {

    }

    @Override
    public List<ProdutoResponse> produtosPorPreço(BigDecimal preco) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("preco").gte(preco)
                )

        );

        AggregationResults<ProdutoResponse> responses = mongoTemplate.aggregate(
                aggregation,
                "produto",
                ProdutoResponse.class
        );

        List<ProdutoResponse> responseList = responses.getMappedResults();
        return responseList;

    }

    @Override
    public List<ProdutoResponse> produtosPorMarca(String marca) {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("marca")
                        .regex(Pattern.compile("^" + marca + "$", Pattern.CASE_INSENSITIVE))

                ));

        AggregationResults<ProdutoResponse> results = mongoTemplate.aggregate(
                aggregation,
                "produto",
                ProdutoResponse.class
        );

        List<ProdutoResponse> responses = results.getMappedResults();
        return responses;

    }

    @Override
    public List<ProdutoResponse> produtosPorCategoria(String categoria) {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("categoria").is(categoria)
                )
        );

        AggregationResults<ProdutoResponse> responses = mongoTemplate.aggregate(
                aggregation,
                "produto",
                ProdutoResponse.class
        );

        List<ProdutoResponse> responseList = responses.getMappedResults();
        return responseList;
    }

    @Override
    public List<ProdutoResponse> produtosPorData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFormatter = LocalDate.parse(data, formatter);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("createdAt").is(dateFormatter)
                )
        );

        AggregationResults<ProdutoResponse> results = mongoTemplate.aggregate(
                aggregation,
                "produto",
                ProdutoResponse.class
        );

        List<ProdutoResponse> responses = results.getMappedResults();
        return responses;

    }

    @Override
    public List<ProdutoResponseProjecaoNomePreco> produtosTrazNomeValor(BigDecimal value) {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("preco").gte(value)),
                Aggregation.project("nome", "preco"),
                Aggregation.sort(Sort.Direction.ASC, "preco"));

        AggregationResults<ProdutoResponseProjecaoNomePreco> results = mongoTemplate.aggregate(
                aggregation,
                "produto",
                ProdutoResponseProjecaoNomePreco.class
        );
        List<ProdutoResponseProjecaoNomePreco> responses = results.getMappedResults();

        responses.forEach(System.out::println);

        return responses;
    }

    @Override
    public List<ProdutoResponseProjecaoNomeComent> produtosTrazNomeComentarios(String marca) {

        //Paginação
        int pageNumber = 0;
        int pageSize = 10;

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("marca").is(marca)),
                Aggregation.project("nome", "comentarios"),
                Aggregation.sort(Sort.Direction.ASC, "nome"),
                Aggregation.skip(pageNumber * pageSize),
                Aggregation.limit(pageSize)
        );

        AggregationResults<ProdutoResponseProjecaoNomeComent> responses = mongoTemplate.aggregate(
                aggregation,
                "produto",
                ProdutoResponseProjecaoNomeComent.class
        );


        return responses.getMappedResults();
    }

    public ProdutoResponse dto(Produto produto) {
        return modelMapper.mapper().map(produto, ProdutoResponse.class);
    }
}
