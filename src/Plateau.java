import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

public class Plateau {
	//**Attributs**//
		private int nb=4;/*nb de pion dans le plateau**/
		private int nbcoup=20;/*nb de coup dans le plateau*/	
		private Pion[] combinaison = new Pion[nb];
		private Pion[] copieCombinaison = new Pion[nb];
		private Pion[] proposition = new Pion[nb];
		private static int score=0;
		protected static Pion blanc= new Pion(11);
		protected static Pion noir= new Pion(10);
		protected static Pion pion1= new Pion(1);
		protected static Pion pion2= new Pion(2);
		protected static Pion pion3= new Pion(3);
		protected static Pion pion4= new Pion(4);
		protected static Pion pion5= new Pion(5);
		protected static Pion pion6= new Pion(6);
		protected static Pion pion7= new Pion(7);
		protected static Pion pion8= new Pion(8);
		protected static Pion pionq= new Pion(0);
		/*muteurs et ascesseurs */
		
		public int getNb() {
			return nb;
		}

		public int getNbcoup() {
			return nbcoup;
		}
		
		//**Constructeurs**//
		
		/*vide*/
		public Plateau (int a, int b) {
			nb=a;
			nbcoup=b;
			combinaison= aleatoireCombi();
			copieCombinaison= recopieCombie(combinaison);
			System.out.println("Les régles du jeu sont simples. \n"
					+ "Vous devez trouver une combinaison mystére de" +nb + "chiffres dans le bon ordre. \n"
					+ "Vous avez le droit à "+nbcoup +"tentatives maximum. \n"
					+ "Chaque chiffre est compris entre 1 et 8. Vous pouvez taper le chiffre 0 si vous souhaitez abandonner.\n"
					+ " \nBon jeu !\n");
		}
		
		public Plateau (Pion [] tab, int a, int b) {
			nb=a;
			nbcoup=b;
			this.combinaison = tab;
			copieCombinaison=recopieCombie(combinaison);
		}
		
		/****Méthodes****/
		
		/**tester le jeu**/
		/*saisir combi*/

		public Pion[] saisirProposition()
		{
			boolean quit = false;
			int i=0;
			Pion pionsaisi;
			Pion[] saisie = new Pion[nb];

			System.out.println("Saisissez une combinaison de " + nb + " chiffres compris entre 1 et 8 (0 pour abandonner).");
				
				while(!quit && i<nb)
				{
					System.out.println("Saisir la valeur du pion n°"+ (i+1) +" :");
					pionsaisi=new Pion();
					switch (pionsaisi.getIdpion())
							{
						case 1 : 
							pionsaisi= pion1;			
							break;
						case 2 : 
							pionsaisi= pion2;			
							break;
						case 3 : 
							pionsaisi= pion3;			
							break;
						case 4 : 
							pionsaisi= pion4;			
							break;
						case 5 : 
							pionsaisi= pion5;			
							break;
						case 6 : 
							pionsaisi= pion6;			
							break;
						case 7 : 
							pionsaisi= pion7;			
							break;
						case 8 : 
							pionsaisi= pion8;			
							break;
						case 0 : 
							pionsaisi= pionq;			
							break;
						}
					saisie[i] = pionsaisi;
					i++;
					//on verifie si le joueur a voulu quitter
					if(pionsaisi == pionq)
						{quit = true;
						System.out.println("vous voulez quitter le jeu");
					}
			}
			return saisie;
			/*return quit;*/
		}			
		
		/*combinaison aléatoire*/
		public Pion [] aleatoireCombi(){
				Pion [] combi = new Pion[nb];
				Random rand = new Random();
				//initialisation de chaque case de maniere aleatoire (chiffre entre 1 et 8)
				for(int i=0; i<nb; ++i)
				{
					Pion pional= new Pion(rand.nextInt(8)+1);
					combi[i] = pional;
				/*	pional.infoPion();
					System.out.println("|");*/
				}
				return combi;
			}
		
		/*afficher combi*/
		public void  infoCombinaison(){
			System.out.println("|");
			for(int i=0; i<this.combinaison.length; i++)
				{
					(this.combinaison[i]).infoPion();
					System.out.print("|");
				}
		}
		/*afficher combi*/
		public void  infoPropos(){
			System.out.print("|");
			for(int i=0; i<this.nb; i++)
			{
				(this.proposition[i]).infoPion();
				System.out.print("|");
			}
			System.out.println("");
		}
		/*afficher combi*/
		public void  infoCopieCombinaison(){
			System.out.print("|");
			for(int i=0; i<this.nb; i++)
				{
				if(this.copieCombinaison[i].getIdpion()==10)
				{
					System.out.print("OK|");
				}
				else if (this.copieCombinaison[i].getIdpion()==11)
				{
					System.out.print(" _|");
				}
				else{System.out.print("  |");}
				}
		}
		
		/*recopie Combi*/
		public Pion[] recopieCombie(Pion [] tableau)
		{
			Pion[] copie = new Pion [tableau.length];
			for(int i=0; i<tableau.length; ++i)
			{
				copie[i] = new Pion(tableau[i].getIdpion());
			}
			return copie;
		}


		
		/****/
		public void jouerP() {
			int bienPlaces, malPlaces, Tentatives, resteTentative;
			boolean abandon, replay;
			bienPlaces = malPlaces = Tentatives = 0;
			resteTentative=nbcoup;
			abandon = replay= false;	
			do {
				proposition= saisirProposition();
			do
			{
				if(!abandon)
				{
					//on regarde le nombre de bien places et de mal places
					bienPlaces = bienPlaces(combinaison, proposition, copieCombinaison);

					//maintenant les mal places
					malPlaces = malPlaces(copieCombinaison, proposition);
					//on affiche le tableau de réponses correcte
					System.out.println("Votre proposition");
					infoPropos();
					System.out.println("Voici le tableau des correspondances.\n"
							+ "_ pour les malplacés et OK pour les bien placés");
					infoCopieCombinaison();
					//on affiche les resultats
					afficheResultat(bienPlaces, malPlaces);
					//on a fait une tentative de plus
					
				}
					Tentatives=Tentatives+1;
					resteTentative=resteTentative-1;	
					System.out.println("Il vous reste " + (resteTentative) + " tentatives.");
			}
			while(!abandon && bienPlaces != nb && resteTentative== 0);

			//on regarde maintenant si on a arreter a cause d'un abandon ou d'une victoire ou d'une defaite
			System.out.println();
			if(abandon)
				System.out.println("Vous quittez le jeu Master. ");
			else if(bienPlaces == nb)
				{System.out.println("Bravo, vous êtes un champion ! Vous avez trouvé la combinaison suivante : "); 
				infoCombinaison();
				System.out.println("en"+ Tentatives + " tentatives.");
				score=scorePlateau(bienPlaces);
				System.out.println("Vous venez de gagnez " + score + " points.");}
			else if (resteTentative== 0)
				System.out.println("Dommage, vous avez perdu ! Vous avez épuisé toutes vos tentatives.");
			
			//On demande au joueur s'il veut rejouer
			replay = ouiOuNon("Souhaitez-vous rejouer (\"o\" pour oui, \"n\" pour non) ? : ");
			System.out.println();
			}
			while(replay);

		System.out.println("Au revoir !");	
		}
		
		/*Comparer 2 combi Mal place*/
		public int malPlaces(Pion [] combinaisonModifie, Pion [] propositionModifie){
			int malPlaces = 0;

			//On peut maintenant avec ce tableau trouver le nombre de mal places en remplacant par des zero les cases deja traites
			//On compare chaque case de combinaisonModifie...
			for(int i = 0; i<combinaisonModifie.length; ++i)
			{
				//...avec chaque case du tableau proposition
				for(int j=0; j<combinaisonModifie.length; ++j)
				{
					//si notre case n'est pas un zero c'est qu'il s'agit peut-etre d'un chiffre mal place
					if(combinaisonModifie[i] != pionq && propositionModifie[j] != pionq && combinaisonModifie[i] == propositionModifie[j])
					{
						//on memorise notre chiffre mal place qu'on ne doit plus traite, ATTENTION aux i et j !
						combinaisonModifie[i] = noir;
						propositionModifie[j] = noir;
						malPlaces++; //un chiffre de plus mal place
					}
				}
			}
			return malPlaces;
		}
		
		/*Comparer 2 combi Bien place*/
		public int bienPlaces(Pion [] combinaison, Pion [] proposition, Pion [] combinaisonModifie) {
			int bienPlaces = 0;
			for(int i=0; i<combinaison.length; ++i)
			{
				if(combinaison[i].getIdpion() == proposition[i].getIdpion())
				{
					combinaisonModifie[i] = blanc; //on retient que cette case est un chiffre bien place
					proposition[i] = blanc; //idem
					bienPlaces++;
				}
				else
					combinaisonModifie[i] = new Pion(combinaison[i].getIdpion()); //sinon on recopie le chiffre initial qui n'est pas un bien place
			}

			return bienPlaces;
		}
		
		/*calcul du score*/
		public int scorePlateau (int bienPlaces){
			int scorePlateau =0;
			if(bienPlaces==nb)
			{
				scorePlateau=100;
			}
			return scorePlateau;
		}
		/*affiche le résultat*/
		public void afficheResultat(int b, int m)
		{
			System.out.println("\n" + b + " chiffre(s) bien placé(s) " + m + " chiffre(s) mal placé(s).\n");
			
		}
		public static boolean ouiOuNon(String question)
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
		
		/**Premier menu premier (invite/connexion/créer)**/
		/** menu **/
		
		/*connecter invité*/
	    /*  ResultSet result = state.executeQuery(query);*/
		/* resultMeta.getColumnCount(1)*/
		/* resultMeta.getColumnName(1)*/
	      
		/*se connecter*/
		 
		/*creer un compte*/
}
