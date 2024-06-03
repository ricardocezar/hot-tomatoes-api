# hot-tomatoes-api
Projeto de teste de competências
seguindo as especificações determinadas [aqui](src/main/resources/specification/Especificação Backend.pdf) 

## Como Executar
1. Para executar o projeto localmente, clone o projeto localmente em sua máquina
2. Abra o cmd a partir da pasta do projeto
3. Execute ```./mvnw.cmd spring-boot:run ```
4. Para executar os testes, execute ```./mvnw.cmd test```

### Informações adicionais
A porta do servidor está configurada inicialmente como 8080

O endpoint pedido, a partir do projeto em execução, é http://localhost:8080/distinguished-producers

A importação do arquivo CSV está fixa para 1 arquivo e o endereço relativo do arquivo está no aplication.properties, propriedade csvfile.relativepath

O nome da API é um trocadilho com Rotten Tomatoes =)