# Operações com mongo aggregation

Existem muitos tipos de agregações disponíveis no MongoDB, mas vou listar aqui alguns dos principais tipos e fornecer exemplos básicos de cada um:

### Match: Filtra documentos com base em uma condição.
Exemplo: Encontre todos os produtos com preço superior a R$100.


db.produtos.aggregate([
    {
        $match: {
            preco: { $gt: 100 }
        }
    }
])

### Group: Agrupa documentos com base em um ou mais campos e executa operações em cada grupo.
Exemplo: Encontre a quantidade total de produtos em cada categoria.


db.produtos.aggregate([
    {
        $group: {
            _id: "$categoria",
            quantidade: { $sum: 1 }
        }
    }
])


### Project: Retorna apenas os campos especificados de cada documento.
Exemplo: Retorne apenas o nome e o preço de todos os produtos.


db.produtos.aggregate([
    {
        $project: {
            nome: 1,
            preco: 1,
            _id: 0
        }
    }
])


### Sort: Ordena os documentos com base em um ou mais campos.
Exemplo: Ordene todos os produtos por preço, do menor para o maior.


db.produtos.aggregate([
    {
        $sort: {
            preco: 1
        }
    }
])


### Limit: Limita o número de documentos retornados.
Exemplo: Retorne apenas os 3 produtos mais caros.


db.produtos.aggregate([
    {
        $sort: {
            preco: -1
        }
    },
    {
        $limit: 3
    }
])


### Skip: Pula um número especificado de documentos.
Exemplo: Retorne todos os produtos, exceto os 2 mais baratos.


db.produtos.aggregate([
    {
        $sort: {
            preco: 1
        }
    },
    {
        $skip: 2
    }
])


### Unwind: Separa um campo de matriz em vários documentos.
Exemplo: Separe os comentários de cada produto em documentos separados.


db.produtos.aggregate([
    {
        $unwind: "$comentarios"
    }
])


### Lookup: Faz uma junção entre documentos em coleções diferentes.
Exemplo: Encontre todos os pedidos com os nomes dos produtos correspondentes.


db.pedidos.aggregate([
    {
        $lookup: {
            from: "produtos",
            localField: "produtoId",
            foreignField: "_id",
            as: "produto"
        }
    }
])
