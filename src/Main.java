import java.util.Scanner;

public class Main {

public static void main(String[] args) {
		
		//Init de la connexion a la bdd, si on s'est bien connecte c'est parti*
		if(Connexion.getInstance() != null)
			menuconnexion();
		else
			System.out.println("La connexion à la BDD a échoué ! Au revoir !");
	}

	private static void menuconnexion() {
	Scanner sc = new Scanner(System.in);		
	int choix;
	boolean choixValide, quit;
	choixValide = false; //booleen pour savoir si le pion saisi est compris entre 1 et 8
	quit = false; //booleen pour savoir si le joueur souhaite quitter le jeu ou pas (si il a tape un 0)	
		do{
			System.out.println("Bienvenue dans le jeu du MasterMind ! \n");
			System.out.println("----------- MENU PRINCIPAL ------------");
			System.out.println("Choisissez une des 4 possibilités\n"
					+ "1- Jouez en tant qu'invite \n"
					+ "2- Créez un compte \n"
					+ "3- Connectez-moi !\n"
					+ "4- Quittez le jeu !\n");	
			
			choix = saisirEntierEntre1Etn(4, "\n Choisir une option : ");
			
				switch(choix) {
				case 1:/*je suis invite*/
					Invite inv = new Invite();
					System.out.println("**** Jouer en tant que "+ inv.getLogin()+ " ****");				
					Plateau inviter = new Plateau (4, 15);	
					inviter.jouerP();
					break;
				case 2 :/*je crée un compte*/
					System.out.println(" Creér un compter");
					String noms, prenoms, mails,logins,  mdps;
					Scanner sc1 = new Scanner(System.in);
					System.out.println("Nom : ");
					noms = sc1.nextLine();
					System.out.println("Prénom : ");
					prenoms = sc1.nextLine();	
					System.out.println("Email : ");
					mails = sc1.nextLine();
					System.out.println("Login : ");
					logins = sc1.nextLine();
					System.out.println("Mot de Passe : ");
					mdps = sc1.nextLine();
					Joueur nouveau = new Joueur(noms, prenoms, mails,logins, mdps);
					break;
				case 3 :/*je me connecte*/
					Joueur j = new Joueur();
					if(j.verifierIdentifiants())	
					{
						System.out.println("**** Jouer en tant que "+ j.getLogin()+ " ****");
						Plateau joueur= new Plateau (4, 30);	
						joueur.jouerP();
					}
						else	
							System.out.println(" Erreur de saisie recommencez !");
					break;
				case 4 :/*quitte*/
					System.out.println("Au revoir, vous venez de quitter le jeu Mastermind !");
					break;
				}
		}while(choix != 4);
		}
	public static int saisirEntierEntre1Etn(int n, String message)
	{		
		Scanner sc = new Scanner(System.in);
		int choix;
		boolean choixValide;
		choixValide = false; //booleen pour savoir si le chiffre saisi est compris entre 1 et n
		do{
			System.out.print(message);
			choix = sc.nextInt();

			//on verifie si le choix est valide ou pas, sinon on informe le joueur de son erreur
			if(choix >= 1 && choix <= n)
				choixValide = true;
			//Sinon erreur de saisie
			else
				System.out.println("Attention, vous devez saisir un entier entre 1 et " + n + ".");
		}
		while(!choixValide); //tant que le choix n'est pas valide ou qu'il n'a pas demander a quitter
		return choix;
	}
}
