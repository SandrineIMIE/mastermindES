import java.util.Scanner;

public abstract class User {
	//**Attributs***//
	protected String login;
	protected int iduser;
	
	/*muteurs et ascesseurs */	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getIduser() {
		return iduser;
	}	
	
	//**Constructeurs**//
	
	public User(String login) {
		this.login = login;
	}
	
	public User() {
		saisirUser();
	}
	
	/*Saisir un nouveau joueur*/
	public void saisirUser() {

	}
	/*afficher les informations joueur*/
	public void infoUser() {

	}

}
