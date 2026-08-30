package database;

import java.sql.Connection;

public class TestConnection {

	public static void main(String[] args) {

		try(Connection com = DB.getConection()) {
			System.out.println("Conectando ao MySQL!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}