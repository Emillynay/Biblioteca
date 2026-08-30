package aplication;

import model.User;
import repository.jdbc.JdbcUserRepository;

public class TestUserRepository {
	
	public static void main(String[] args) {

        JdbcUserRepository repository = new JdbcUserRepository();
        
        /*

        User user = new User(
                "Emilly",
                "(98) 99999-9999",
                "emilly@email.com",
                "Santa Inês"
        );

        repository.save(user);

        System.out.println("Usuário adicionado!"); 
        
        System.out.println("\n--- USUÁRIOS ---");

        for (User u : repository.findAll()) {
            System.out.println(
                u.getId() + " | " +
                u.getName() + " | " +
                u.getNumber() + " | " +
                u.getEmail() + " | " +
                u.getAnddress()
            );
            
            User foundUser = repository.findById(1L);

            if (foundUser != null) {
                System.out.println("\n--- USUÁRIO ENCONTRADO ---");
                System.out.println(
                    foundUser.getId() + " | " +
                    foundUser.getName() + " | " +
                    foundUser.getEmail()
                );
            } else {
                System.out.println("Usuário não encontrado.");
            }  
      
        User user = repository.findById(1L);

        if (user != null) {

            user.setName("Emilly Atualizada");
            user.setNumber("(98) 98888-8888");
            user.setEmail("emilly.novo@email.com");
            user.setAnddress("Santa Inês - MA");

            repository.update(user);

            System.out.println("Usuário atualizado!");

        } else {
            System.out.println("Usuário não encontrado.");
        } 
        
        User user = repository.findById(1L);

        if (user != null) {

            repository.delete(user);

            System.out.println("Usuário deletado!");

        } else {
            System.out.println("Usuário não encontrado.");
        }
        */
        
        }
    }


