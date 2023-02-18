package io.github.cedromaxxsoft.crudMongo.repository;

import io.github.cedromaxxsoft.crudMongo.entities.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
