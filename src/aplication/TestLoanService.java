package aplication;

import java.util.List;

import model.Book;
import model.Loan;
import model.User;
import service.BookService;
import service.LoanService;
import service.UserService;

public class TestLoanService {

    public static void main(String[] args) {

        LoanService loanService = new LoanService();
        BookService bookService = new BookService();
        UserService userService = new UserService();

        try {

            // =====================================================
            // 1 - CADASTRAR UM LIVRO
            // =====================================================

            System.out.println("===== 1. CADASTRANDO LIVRO =====");

            Book newBook = new Book(
                    "Livro Teste LoanService",
                    "Autor Teste",
                    100,
                    5
            );

            bookService.addBook(newBook);

            // Buscar o livro no banco para obter o ID
            Book book = bookService
                    .findByName("Livro Teste LoanService")
                    .get(0);

            System.out.println("Livro cadastrado!");
            System.out.println("ID: " + book.getId());
            System.out.println("Nome: " + book.getName());
            System.out.println("Disponíveis: "
                    + book.getAvailableCopies());


            // =====================================================
            // 2 - CADASTRAR USUÁRIO
            // =====================================================

            System.out.println("\n===== 2. CADASTRANDO USUÁRIO =====");

            User newUser = new User(
                    "Usuário Teste LoanService",
                    "999999999",
                    "teste.loanservigfce@email.com",
                    "Endereço Teste"
            );

            userService.addUser(newUser);

            // Buscar usuário no banco para obter o ID
            User user = userService
                    .searchByEmail("teste.loanservice@email.com");

            System.out.println("Usuário cadastrado!");
            System.out.println("ID: " + user.getId());
            System.out.println("Nome: " + user.getName());


            // =====================================================
            // 3 - VERIFICAR EMPRÉSTIMOS ANTERIORES
            // =====================================================

            System.out.println("\n===== 3. EMPRÉSTIMOS ANTERIORES =====");

            System.out.println(
                    "Possui empréstimo anterior? "
                    + loanService.hasPreviousLoan(user)
            );

            System.out.println(
                    "Limite de empréstimos: "
                    + loanService.getLoanLimit(user)
            );


            // =====================================================
            // 4 - VERIFICAR SE PODE PEGAR LIVRO
            // =====================================================

            System.out.println("\n===== 4. PODE PEGAR LIVRO? =====");

            System.out.println(
                    "Pode pegar livro? "
                    + loanService.canBorrow(user)
            );


            // =====================================================
            // 5 - REALIZAR EMPRÉSTIMO
            // =====================================================

            System.out.println("\n===== 5. REALIZANDO EMPRÉSTIMO =====");

            System.out.println(
                    "Disponíveis antes: "
                    + book.getAvailableCopies()
            );

            Loan loan = loanService.makeLoan(user, book);

            System.out.println("Empréstimo realizado!");
            System.out.println("ID do empréstimo: "
                    + loan.getId());

            System.out.println(
                    "Data do empréstimo: "
                    + loan.getLoanDate()
            );

            System.out.println(
                    "Data prevista para devolução: "
                    + loan.getExpectedReturnDate()
            );

            System.out.println(
                    "Empréstimo ativo? "
                    + loan.isActive()
            );

            System.out.println(
                    "Disponíveis depois: "
                    + book.getAvailableCopies()
            );


            // =====================================================
            // 6 - LISTAR TODOS OS EMPRÉSTIMOS
            // =====================================================

            System.out.println("\n===== 6. LISTANDO EMPRÉSTIMOS =====");

            List<Loan> loans = loanService.listLoans();

            System.out.println(
                    "Quantidade de empréstimos: "
                    + loans.size()
            );

            for (Loan l : loans) {

                System.out.println(
                        "ID: " + l.getId()
                        + " | Livro: " + l.getBook().getName()
                        + " | Usuário: " + l.getUser().getName()
                        + " | Ativo: " + l.isActive()
                );
            }


            // =====================================================
            // 7 - BUSCAR EMPRÉSTIMO POR ID
            // =====================================================

            System.out.println("\n===== 7. BUSCANDO POR ID =====");

            Loan foundLoan = loanService.findById(loan.getId());

            System.out.println("Empréstimo encontrado!");
            System.out.println(
                    "Livro: "
                    + foundLoan.getBook().getName()
            );

            System.out.println(
                    "Usuário: "
                    + foundLoan.getUser().getName()
            );


            // =====================================================
            // 8 - PESQUISAR POR USUÁRIO
            // =====================================================

            System.out.println("\n===== 8. BUSCANDO POR USUÁRIO =====");

            List<Loan> loansByUser =
                    loanService.searchByUser(user);

            System.out.println(
                    "Empréstimos deste usuário: "
                    + loansByUser.size()
            );


            // =====================================================
            // 9 - PESQUISAR POR LIVRO
            // =====================================================

            System.out.println("\n===== 9. BUSCANDO POR LIVRO =====");

            List<Loan> loansByBook =
                    loanService.searchByBook(book);

            System.out.println(
                    "Empréstimos deste livro: "
                    + loansByBook.size()
            );


            // =====================================================
            // 10 - CONTAR EMPRÉSTIMOS ATIVOS
            // =====================================================

            System.out.println("\n===== 10. EMPRÉSTIMOS ATIVOS =====");

            System.out.println(
                    "Empréstimos ativos do usuário: "
                    + loanService.countActiveLoans(user)
            );


            // =====================================================
            // 11 - LISTAR EMPRÉSTIMOS ATIVOS
            // =====================================================

            System.out.println(
                    "\n===== 11. LISTANDO EMPRÉSTIMOS ATIVOS ====="
            );

            List<Loan> activeLoans =
                    loanService.listActiveLoans();

            System.out.println(
                    "Quantidade de empréstimos ativos: "
                    + activeLoans.size()
            );


            // =====================================================
            // 12 - BUSCAR EMPRÉSTIMOS ATIVOS DO USUÁRIO
            // =====================================================

            System.out.println(
                    "\n===== 12. ATIVOS DO USUÁRIO ====="
            );

            List<Loan> activeUserLoans =
                    loanService.searchActiveByUser(user);

            System.out.println(
                    "Empréstimos ativos deste usuário: "
                    + activeUserLoans.size()
            );


            // =====================================================
            // 13 - VERIFICAR LIMITE
            // =====================================================

            System.out.println("\n===== 13. TESTANDO LIMITE =====");

            System.out.println(
                    "Empréstimos ativos: "
                    + loanService.countActiveLoans(user)
            );

            System.out.println(
                    "Limite: "
                    + loanService.getLoanLimit(user)
            );

            System.out.println(
                    "Pode pegar outro livro? "
                    + loanService.canBorrow(user)
            );


            // =====================================================
            // 14 - DEVOLVER LIVRO
            // =====================================================

            System.out.println("\n===== 14. DEVOLVENDO LIVRO =====");

            System.out.println(
                    "Disponíveis antes da devolução: "
                    + book.getAvailableCopies()
            );

            loanService.returnLoan(loan.getId());

            System.out.println("Livro devolvido!");

            // Buscar novamente no banco
            book = bookService.findById(book.getId());

            System.out.println(
                    "Disponíveis depois da devolução: "
                    + book.getAvailableCopies()
            );


            // =====================================================
            // 15 - VERIFICAR EMPRÉSTIMO DEVOLVIDO
            // =====================================================

            System.out.println(
                    "\n===== 15. VERIFICANDO DEVOLUÇÃO ====="
            );

            loan = loanService.findById(loan.getId());

            System.out.println(
                    "Empréstimo ativo? "
                    + loan.isActive()
            );

            System.out.println(
                    "Data de devolução: "
                    + loan.getReturnDate()
            );


            // =====================================================
            // 16 - REMOVER EMPRÉSTIMO
            // =====================================================

            System.out.println(
                    "\n===== 16. REMOVENDO EMPRÉSTIMO ====="
            );

            loanService.removeLoan(loan.getId());

            System.out.println(
                    "Empréstimo removido com sucesso!"
            );


            // =====================================================
            // FINAL
            // =====================================================

            System.out.println(
                    "\n===================================="
            );

            System.out.println(
                    "TODOS OS TESTES FORAM EXECUTADOS!"
            );

            System.out.println(
                    "===================================="
            );

        } catch (Exception e) {

            System.out.println(
                    "\n===== ERRO DURANTE O TESTE ====="
            );

            e.printStackTrace();
        }
    }
}