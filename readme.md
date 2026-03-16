Sistema de Cadastro de Alunos

Documentação Técnica e Manual de Uso


---

1. Sobre o Projeto

Este projeto consiste em uma aplicação web desenvolvida utilizando Spring Boot, cujo objetivo é realizar operações básicas de CRUD (Create, Read, Update, Delete) sobre uma entidade denominada Aluno.

O sistema permite ao usuário:

cadastrar alunos

visualizar alunos cadastrados

editar informações de alunos

excluir registros de alunos


A interação do usuário ocorre por meio de uma interface web construída com Thymeleaf, onde os dados são enviados e processados pelo backend Java.

O sistema utiliza o banco de dados H2, que é inicializado automaticamente junto com a aplicação, não sendo necessária nenhuma instalação externa de banco de dados.


---

2. Dependências do Projeto

Dependência	Finalidade

Java	Linguagem de programação principal
Spring Boot	Framework principal da aplicação
Spring Boot Web	Criação da aplicação web
Spring Data JPA	Persistência e acesso ao banco de dados
Thymeleaf	Renderização das páginas HTML
H2 Database	Banco de dados relacional em memória
Spring Validation	Validação dos campos do formulário
Spring Boot DevTools	Ferramentas de desenvolvimento



---

3. Arquitetura da Aplicação

A aplicação segue uma arquitetura em camadas, muito utilizada em projetos Spring Boot. Cada camada possui uma responsabilidade específica dentro do sistema.

3.1 Camada Model

A classe Aluno representa a entidade do sistema e é responsável por definir os dados que serão armazenados no banco.

Atributo	Tipo	Descrição

id	Long	Identificador único do aluno
nome	String	Nome do aluno
email	String	Email do aluno
curso	String	Curso em que o aluno está matriculado


Essa classe é anotada com @Entity, indicando que será uma tabela no banco de dados.


---

3.2 Camada Repository

A interface AlunoRepository estende JpaRepository, fornecendo automaticamente métodos prontos para manipulação de dados no banco.

Entre os métodos disponíveis estão:

save()

findAll()

findById()

deleteById()


Esses métodos permitem realizar operações de persistência sem necessidade de escrever SQL manualmente.


---

3.3 Camada Service

A classe AlunoService contém a lógica de negócio da aplicação.

Ela atua como intermediária entre o Controller e o Repository, garantindo que as operações sejam executadas corretamente antes de acessar o banco de dados.

Os principais métodos dessa camada são:

listarTodos()

buscarPorId()

salvar()

excluir()



---

3.4 Camada Controller

O AlunoController é responsável por receber as requisições HTTP feitas pelo navegador e direcionar para a lógica da aplicação.

Ele também envia os dados para as páginas HTML utilizando o objeto Model do Spring MVC.

Principais rotas do sistema:

Método	Rota	Descrição

GET	/alunos	Lista todos os alunos
GET	/alunos/novo	Abre o formulário de cadastro
GET	/alunos/editar/{id}	Abre o formulário de edição
POST	/alunos/salvar	Salva ou atualiza um aluno
POST	/alunos/excluir/{id}	Remove um aluno



---

4. Interface Web (Thymeleaf)

A interface do sistema é construída com Thymeleaf, um template engine que permite integrar dados do backend Java diretamente nas páginas HTML.

O Thymeleaf utiliza atributos especiais prefixados com th: para fazer a ligação entre HTML e Java.


---

4.1 Página de Lista de Alunos (/alunos)

Ao acessar a rota:

http://localhost:8080/alunos

o sistema exibe uma tabela contendo todos os alunos cadastrados.

Na tabela são exibidos:

ID

Nome

Email

Curso


Cada linha da tabela possui dois botões:

Editar

Excluir



---

4.2 Página de Cadastro de Alunos (/alunos/novo)

Essa página apresenta um formulário onde o usuário pode inserir os dados do aluno.

Campos disponíveis:

Nome

Email

Curso


Após preencher os dados, basta clicar no botão Salvar para registrar o aluno no sistema.


---

4.3 Página de Edição de Alunos (/alunos/editar/{id})

Ao clicar no botão Editar na lista de alunos, o sistema abre o formulário já preenchido com os dados do aluno selecionado.

O usuário pode alterar as informações e salvar novamente.


---

5. Integração entre Thymeleaf e Backend

A comunicação entre o backend e as páginas HTML ocorre por meio do objeto Model.

O controller envia os dados para a página, e o Thymeleaf os exibe utilizando expressões.

Atributo	Função

th:object	Vincula um objeto Java ao formulário
th:field	Liga um campo do formulário ao atributo do objeto
th:text	Exibe dados no HTML
th:each	Percorre listas de objetos
th:action	Define a rota de envio do formulário
th:href	Cria links dinâmicos


Exemplo:

th:each="aluno : ${alunos}"

Esse comando percorre todos os alunos da lista enviada pelo controller.


---

6. Banco de Dados H2

O sistema utiliza o H2 Database, um banco de dados leve escrito em Java que pode ser executado diretamente dentro da aplicação.

Ele é configurado automaticamente pelo Spring Boot.


---

6.1 Console do H2

O console do banco pode ser acessado em:

http://localhost:8080/h2-console

Dados de acesso:

Usuário:

sa

Senha:

sa


---

7. Como Executar o Projeto

7.1 Pré-requisitos

Para executar o projeto é necessário possuir:

Java instalado

Maven instalado

Uma IDE (recomendado IntelliJ IDEA)



---

7.2 Executando o Projeto

1. Abra o projeto na IDE


2. Aguarde o Maven baixar todas as dependências


3. Execute a classe principal da aplicação Spring Boot


4. Abra o navegador



Acesse:

http://localhost:8080/alunos


---

8. Guia de Uso do Sistema

8.1 Cadastrar um Aluno

1. Acesse o sistema no navegador


2. Clique no botão Novo Aluno


3. Preencha os dados solicitados


4. Clique em Salvar



O aluno será adicionado à lista.


---

8.2 Visualizar Alunos

A página inicial exibe todos os alunos cadastrados em formato de tabela.


---

8.3 Editar um Aluno

1. Clique no botão Editar na linha do aluno desejado


2. Altere os dados necessários


3. Clique em Salvar




---

8.4 Excluir um Aluno

1. Clique no botão Excluir


2. O aluno será removido do sistema.




---

9. Estrutura do Projeto

src/main/java/com/biopark/alunos/

controller/
    AlunoController.java

model/
    Aluno.java

repository/
    AlunoRepository.java

service/
    AlunoService.java

src/main/resources/

templates/
    alunos/
        lista.html
        formulario.html

application.properties

