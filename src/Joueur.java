import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Joueur extends User {
	//**Attributs**//
	private String nom;
	private String prenom;
	private String mail;
	private String password;
	private int score;
	
	/*muteurs et ascesseurs **/
	
	//**Constructeurs**//
	public Joueur() {
		saisirIdentifiants();	
	}
	/*vide*//*appel du joueur invité*/
	public Joueur(String nom, String prenom, String mail,String login, String password ) {
		this.login = login;
		this.nom=nom;
		this.prenom=prenom;
		this.mail=mail;
		this.password=password;
		if (creerJoueur()==true)
		System.out.println("Bravo ! Le compte "+ login +" a été crée");
		else {
		System.out.println("Le compte n'a pas été crée");}
	}
	
	/**
	 * Fonction qui demande à l'utilisateur de saisir ses identifiants
	 */
	public void saisirIdentifiants()/*a faire */
	{
		System.out.println("Saisir un compte");
		Scanner sc = new Scanner(System.in);
		System.out.println("Login : ");
		login = sc.nextLine();
		System.out.println("Mot de passe : ");
		password = sc.nextLine();		
		/*if (verifierIdentifiants())
		{}
		else {
			System.out.println("Reessayez de saisir votre identifiant !");
			};*/
	}
	
	/**
	 * Fonction qui creer un joueur en BDD
	 * @return true si tout s'est bien passe, faux sinon
	 */
	
		public boolean creerJoueur()
		{
			boolean res = false;
			String query = "INSERT INTO joueur (login, password, nom, prenom, mail) VALUES (?,?,?,?,?) RETURNING id_joueur;";
			try {
				PreparedStatement prepare = Connexion.getInstance().prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				prepare.setString(1, login);
				prepare.setString(2, password);
				prepare.setString(3, nom);
				prepare.setString(4, prenom);
				prepare.setString(5, mail);
				
				//On execute la requete
				prepare.execute();
					
				//Si la requete s'est bien passee, on recupere le id_joueur qui a ete genere
				ResultSet result = prepare.getResultSet();
				if(result.first())
				{
					iduser = result.getInt("id_joueur");
					res = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return res;
		}
		
		/**
		 * Fonction qui verifie les identifiants saisis par l'utilisateur
		 * @return true si tout est ok, false sinon
		 */
		public boolean verifierIdentifiants()
		{
			String query = "SELECT * FROM joueur WHERE login=?";
			String query2 = "SELECT * FROM joueur WHERE login=? AND password=?";
			try {
				PreparedStatement prepare = Connexion.getInstance().prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				prepare.setString(1, login);
				
				ResultSet result = prepare.executeQuery();
				
				//Si login existe
				if(result.first())
				{
					PreparedStatement prepare2 = Connexion.getInstance().prepareStatement(query2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					prepare2.setString(1, login);
					prepare2.setString(2, password);
					
					ResultSet result2 = prepare2.executeQuery();
					//user identifie
					if(result2.first())
					{
						System.out.println("\nIdentification OK...\n");
						
						//On remplit nos attibuts
						iduser = result2.getInt("id_joueur");
						if(result2.getString("nom") != null)
							nom = result2.getString("nom");
						if(result2.getString("prenom") != null)
							prenom = result2.getString("prenom");
						
						return true;
					}
					//Mot de passe non valide !
					else
					{
						System.out.println("\nAttention le mot de passe est invalide !\n");
						return false;
					}
				}
				else
				{
					System.out.println("\nAttention le login saisi n'existe pas !\n");
					return false;
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param mdp the mdp to set
		 */
		public void setMdp(String mdp) {
			this.password = mdp;
		}

		/**
		 * @return the nom
		 */
		public String getNom() {
			return nom;
		}

		/**
		 * @param nom the nom to set
		 */
		public void setNom(String nom) {
			this.nom = nom;
		}

		/**
		 * @return the prenom
		 */
		public String getPrenom() {
			return prenom;
		}

		/**
		 * @param prenom the prenom to set
		 */
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		/**
		 * @return the mail
		 */
		public String getMail() {
			return mail;
		}

		/**
		 * @param mail the mail to set
		 */
		public void setMail(String mail) {
			this.mail = mail;
		}
		
	/*rappel du mot de passe*/
		
	/*modifier le mot de passe*/
		
	/*afficher l'historique du joueur*/
		public void history(int nb) {
			
		}
		
	/*afficher les informations joueur*/
		public void infoUser(){
			System.out.println("*** Votre compte Utilisateur ***");
			System.out.println("Login : "+ login);
			System.out.println("Nom : "+ nom);
			System.out.println("Prénom : "+ prenom);
			System.out.println("Courriel : "+ mail);
			System.out.println("Mot de passe : "+ password);
			System.out.println("Score : "+ score + " points");
		}
}
