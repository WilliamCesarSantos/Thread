# Thread

## Sumário

<!-- TOC depthFrom:1 depthTo:2 orderedList:false withLinks:false anchorMode:gitlab.com -->
- 1 - Definição do projeto
- 2 - Tecnologias
- 3 - Execução
<!-- /TOC -->

# 1 - Definição do projeto
Aplição desenvolvida como exemplo da utilização de Thread com os aulos do 4º semestre.
App permite executar a consulta de uma determinada query em banco de dados Postgres, Mysql, também pesquisa a mesma query em arquivos e no Google apresentando ao usuário os locais com correspondência encontrado.

# 2 - Tecnologia
Aplicativo construção sobre os conceitos de Thread com a intenção de demonstrar o uso e funcionamento.
Para a construção do aplicativo foram utilizados a linguagem Java 8, com acesso a banco de dados Mysql e Postgres, também utilizado o framework jsoup para acesso a API do Google de pesquisa. Para simplificar o acesso e tratamento de informações da base de dados relacional foi utilizado o apache dbUtils.
Utilizado maven para gerencia de dependências.

# 2.1 - Mysql
Base de dados relacional, maiores informações em https://www.mysql.com/
# 2.2 - PostgreSql
Base de dados relacional, maiores informações em https://www.postgresql.org/
# 2.3 - Jsoup
Java HTML parser - Permitir a execução da consulta utilizando a API do google. Maiores informações em https://www.postgresql.org/
# 2.4 - Apache dbUtils
JDBC Utility - Utilitário para simplificar a manipulação de informação na base de dados recional. Maiores informações em https://commons.apache.org/proper/commons-dbutils/
# 2.5 - Apache Maven
Management dependencies - Gerenciador das dependências do projeto. Maiores informações em https://maven.apache.org/


# 3 - Execução
O aplicativo deve ser executada apenas pela IDE. Não existe nenhuma task do maven para gerar o jar. A classe principal do app é com.santos.will.search.view.SearchFrame.java
