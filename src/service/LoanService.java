package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.LoanLimitException;
import exception.LoanNotFoundException;
import exception.NoAvailableCopyException;
import model.Book;
import model.Loan;
import model.User;
import repository.BookRepository;
import repository.LoanRepository;
import repository.jdbc.JdbcBookRepository;
import repository.jdbc.JdbcLoanRepository;

public class LoanService {

    private LoanRepository repository = new JdbcLoanRepository();
    private BookRepository bookRepository = new JdbcBookRepository();

    // LISTAR TODOS OS EMPRÉSTIMOS
    public List<Loan> listLoans() {
        return repository.findAll();
    }

    // PESQUISAR EMPRÉSTIMOS POR USUÁRIO
    public List<Loan> searchByUser(User user) {

        List<Loan> results = new ArrayList<>();

        for (Loan loan : repository.findAll()) {

            if (loan.getUser().getId().equals(user.getId())) {
                results.add(loan);
            }
        }

        return results;
    }

    // PESQUISAR EMPRÉSTIMOS POR LIVRO
    public List<Loan> searchByBook(Book book) {

        List<Loan> results = new ArrayList<>();

        for (Loan loan : repository.findAll()) {

            if (loan.getBook().getId().equals(book.getId())) {
                results.add(loan);
            }
        }

        return results;
    }

    // VERIFICAR SE O USUÁRIO JÁ FEZ ALGUM EMPRÉSTIMO
    public boolean hasPreviousLoan(User user) {

        for (Loan loan : repository.findAll()) {

            if (loan.getUser().getId().equals(user.getId())) {
                return true;
            }
        }

        return false;
    }

    // CONTAR EMPRÉSTIMOS ATIVOS DO USUÁRIO
    public int countActiveLoans(User user) {

        int count = 0;

        for (Loan loan : repository.findAll()) {

            if (loan.getUser().getId().equals(user.getId())
                    && loan.isActive()) {

                count++;
            }
        }

        return count;
    }

    // DEFINIR LIMITE DE EMPRÉSTIMOS
    public int getLoanLimit(User user) {

        if (!hasPreviousLoan(user)) {
            return 1;
        }

        return 3;
    }

    // VERIFICAR SE O USUÁRIO PODE PEGAR UM LIVRO
    public boolean canBorrow(User user) {

        int activeLoans = countActiveLoans(user);
        int limit = getLoanLimit(user);

        return activeLoans < limit;
    }

    // REALIZAR EMPRÉSTIMO
    public Loan makeLoan(User user, Book book) {

        // Verifica limite
        if (!canBorrow(user)) {

            throw new LoanLimitException(
                    "Usuário atingiu o limite de empréstimos."
            );
        }

        // Verifica se há exemplar disponível
        if (!book.loan()) {

            throw new NoAvailableCopyException(
                    "Não há exemplares disponíveis."
            );
        }

        // Atualiza quantidade disponível no banco
        bookRepository.update(book);

        // Cria empréstimo
        Loan loan = new Loan(
                null,
                book,
                user,
                LocalDate.now(),
                LocalDate.now().plusDays(20)
        );

        // Salva no banco
        repository.save(loan);

        return loan;
    }

    // BUSCAR EMPRÉSTIMO POR ID
    public Loan findById(Long id) {

        Loan loan = repository.findById(id);

        if (loan == null) {

            throw new LoanNotFoundException(
                    "Empréstimo não encontrado."
            );
        }

        return loan;
    }

    // DEVOLVER LIVRO
    public void returnLoan(Long id) {

        Loan loan = findById(id);

        // Verifica se já foi devolvido
        if (!loan.isActive()) {

            throw new IllegalStateException(
                    "Este empréstimo já foi devolvido."
            );
        }

        // Registra a data de devolução
        loan.returnBook();

        // Devolve exemplar para o estoque
        loan.getBook().returnBook();

        // Atualiza empréstimo no banco
        repository.update(loan);

        // Atualiza quantidade disponível do livro
        bookRepository.update(loan.getBook());
    }

    // LISTAR SOMENTE EMPRÉSTIMOS ATIVOS
    public List<Loan> listActiveLoans() {

        List<Loan> results = new ArrayList<>();

        for (Loan loan : repository.findAll()) {

            if (loan.isActive()) {
                results.add(loan);
            }
        }

        return results;
    }

    // LISTAR EMPRÉSTIMOS ATIVOS DE UM USUÁRIO
    public List<Loan> searchActiveByUser(User user) {

        List<Loan> results = new ArrayList<>();

        for (Loan loan : repository.findAll()) {

            if (loan.getUser().getId().equals(user.getId())
                    && loan.isActive()) {

                results.add(loan);
            }
        }

        return results;
    }

    // REMOVER EMPRÉSTIMO
    public void removeLoan(Long id) {

        Loan loan = findById(id);

        // Não permitir apagar empréstimo ainda ativo
        if (loan.isActive()) {

            throw new IllegalStateException(
                    "Não é possível remover um empréstimo ativo. "
                    + "Faça a devolução primeiro."
            );
        }

        repository.delete(loan);
    }
}