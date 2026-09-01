Sistema de Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em Java, com interface gráfica em JavaFX e persistência de dados em MySQL. O projeto foi desenvolvido com foco em Programação Orientada a Objetos, organização em camadas, acesso a banco de dados e desenvolvimento de uma aplicação desktop funcional.


Funcionalidades:

📚 Livros
- Cadastro de livros
- Listagem de livros
- Busca por ID, nome, autor e CDD
- Atualização de livros
- Remoção de livros
- Controle da quantidade total e disponível de exemplares

👤 Usuários
- Cadastro de usuários
- Listagem de usuários
- Busca por nome e e-mail
- Atualização de usuários
- Remoção de usuários

📖 Empréstimos
- Realização de empréstimos
- Listagem de empréstimos
- Busca de empréstimos
- Devolução de livros
- Remoção de empréstimos
- Controle de empréstimos ativos e devolvidos
- Controle automático da disponibilidade dos livros

  

Tecnologias utilizadas
- Java
- JavaFX
- FXML
- CSS
- JDBC
- MySQL
- Git
- GitHub

  
Desenvolvimento do projeto

O projeto foi desenvolvido de forma incremental.
Inicialmente, foram utilizados repositórios em memória para desenvolver e testar a lógica da aplicação. Posteriormente, o sistema foi integrado ao MySQL utilizando JDBC, permitindo a persistência dos dados.
Após a implementação da lógica e da persistência, foi desenvolvida a interface gráfica utilizando JavaFX e FXML.


Estrutura do projeto 

O projeto utiliza uma organização baseada em camadas:

```text
src
├── application
│   └── Main
│
├── model
│   ├── Book
│   ├── User
│   └── Loan
│
├── service
│   ├── BookService
│   ├── UserService
│   └── LoanService
│
├── repository
│   ├── BookRepository
│   ├── UserRepository
│   └── LoanRepository
│
├── repository/memory
│   ├── InMemoryBookRepository
│   ├── InMemoryUserRepository
│   └── InMemoryLoanRepository
│
├── repository/jdbc
│   ├── JdbcBookRepository
│   ├── JdbcUserRepository
│   └── JdbcLoanRepository
│
├── view
│   ├── MainView.fxml
│   ├── BookView.fxml
│   ├── UserView.fxml
│   ├── LoanView.fxml
│   └── style.css
│
└── exception
    └── BookNotFoundException
