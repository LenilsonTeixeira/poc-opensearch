## Configurando domain 
```
aws opensearch create-domain --domain-name prisma --profile localstack 
```

### Endereço do cluster
```
prisma.us-east-1.opensearch.localhost.localstack.cloud:4566
```

### Verificar o status do cluster
```
aws opensearch describe-domain --domain-name prisma --profile localstack
```

### Verificar saúde do cluster - GET /_cluster/health
```
curl prisma.us-east-1.opensearch.localhost.localstack.cloud:4566/_cluster/health | jq
```

### Criando índice - PUT /<index-name>
```
curl -X PUT prisma.us-east-1.opensearch.localhost.localstack.cloud:4566/products
```

### Consultar indices - GET /_cat/indices
```
curl -X GET prisma.us-east-1.opensearch.localhost.localstack.cloud:4566/_cat/indices
```

### Consultar documento por ID  - GET /<index-name>/_doc/<document-id>
```
curl -X GET prisma.us-east-1.opensearch.localhost.localstack.cloud:4566/bike/_doc/0ab5aaad-0e37-4751-8c55-c92d5eb7beaa
```
### DELETAR um documento por ID  - DELETE /<index-name>/<document-id>
```
curl -X DELETE prisma.us-east-1.opensearch.localhost.localstack.cloud:4566/bike/0ab5aaad-0e37-4751-8c55-c92d5eb7beaa
```

### DELETAR o indice  - DELETE /<index-name>
```
curl -X DELETE prisma.us-east-1.opensearch.localhost.localstack.cloud:4566/bike
```