import java.util.Scanner;

public class Pion {
	//**Attributs**//
	/*private String couleur;*/
	private int idpion;
	
	/*muteurs et ascesseurs **/
	public int getIdpion() {
		return idpion;
	}

	public void setIdpion(int idpion) {
		this.idpion = idpion;
	}

	//**Constructeurs**//
	
	/*vide*/
	public Pion() {
		idpion = saisirPion();
	}
	
	public Pion(int valeur/*,String couleur*/) {
		this.idpion = valeur;
		/*this.couleur= couleur;*/
	}
	//**Méthodes**//
	/*créer pion et verifier valeur du pion de 1 à 8 - 0 pour quitter la création du pion */
	public int saisirPion() {
		int valeur;
		boolean choixValide, quit;
		choixValide = false; //booleen pour savoir si le pion saisi est compris entre 1 et 8
		quit = false; //booleen pour savoir si le joueur souhaite quitter le jeu ou pas (si il a tape un 0)	
		do{
			Scanner scan = new Scanner (System.in);
			valeur = scan.nextInt();scan.nextLine();
			/*System.out.println("Saisir la couleur du pion :");
			couleur = sc.nextLine();	*/
			//on verifie si le choix est valide ou pas, sinon on informe le joueur de son erreur
			if(valeur >= 1 && valeur <= 8){
				choixValide = true;
				}
			//Si il a tape un 0 on lui demande de confirmer au cas ou ce n'etait pas volontaire
			else if(valeur==0){
				quit = ouiOuNon("Voulez-vous vraiment abandonner (\"o\" pour oui, \"n\" pour non) : ");
			}
			//Sinon erreur de saisie
			else{
				System.out.println("Attention, vous devez saisir un entier entre 1 et 8, ou 0 pour quitter.");}
		}
		while(!choixValide && !quit); //tant que le choix n'est pas valide ou qu'il n'a pas demander a quitter
		return valeur;
	}
	public boolean ouiOuNon(String question)
	{
		Scanner scan = new Scanner(System.in);
		String rp;
		do {
			System.out.print(question);
			rp = scan.nextLine();
		}
		while(!rp.equals("o") && !rp.equals("n")); //on continue tant qu'il n'a pas saisie "o" ou "n"

		//On verifie sa reponse, si oui on memorise son choix dans le booleen quit
		if(rp.equals("o"))
			return true;
		else 
			return false;
	}
	
	/*afficher pion*/
	public void infoPion() {
		System.out.print(idpion /*+"-"+ couleur*/);
	}
}
