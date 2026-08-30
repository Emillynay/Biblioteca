package aplication;

import java.util.List;

import model.User;
import service.UserService;

public class TestUserService {
	
	    public static void main(String[] args) {

	    	
	        UserService service = new UserService();
	        
	        /*

	        User user = new User(
	                "Carlos",
	                "(98) 98888-7777",
	                "carlos@email.com",
	                "Santa Inês"
	        );

	        service.addUser(user);

	        System.out.println("Usuário adicionado pelo Service!");
	        
	        try {

	            User duplicate = new User(
	                    "Outro Usuário",
	                    "(98) 97777-6666",
	                    "carlos@email.com",
	                    "Outra cidade"
	            );

	            service.addUser(duplicate);

	            System.out.println("Usuário adicionado!");

	        } catch (IllegalArgumentException e) {

	            System.out.println(e.getMessage());
	        } 
	        
	        System.out.println("\n--- LISTA DE USUÁRIOS ---");

	        for (User u : service.listUsers()) {
	            System.out.println(
	                u.getId() + " | " +
	                u.getName() + " | " +
	                u.getEmail()
	            ); 
	        }
	            
	            User found = service.searchByEmail("carlos@email.com");

	            if (found != null) {
	                System.out.println("\n--- USUÁRIO ENCONTRADO ---");
	                System.out.println(
	                    found.getId() + " | " +
	                    found.getName() + " | " +
	                    found.getEmail()
	                );
	            } else {
	                System.out.println("Usuário não encontrado.");
	            }
	            
	            System.out.println("\n--- BUSCA POR NOME ---");

	            List<User> results = service.searchByName("Carlos");

	            for (User u : results) {
	                System.out.println(
	                    u.getId() + " | " +
	                    u.getName() + " | " +
	                    u.getEmail()
	                );
	            }
	            
	            User user = service.listUsers().get(0);

	            user.setName("Carlos Atualizado");
	            user.setNumber("(98) 90000-1111");
	            user.setEmail("carlos.atualizado@email.com");
	            user.setAnddress("Santa Inês - MA");

	            service.updateUser(user);

	            System.out.println("Usuário atualizado pelo Service!");
	            */
	        
	        try {

	            service.removeUser(2L);

	            System.out.println("Usuário removido pelo Service!");

	        } catch (Exception e) {

	            System.out.println(e.getMessage());
	        }
	        }
	    }
	