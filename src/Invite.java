import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Invite extends User {
	private int score;
	
		/*muteurs et ascesseurs* */
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getIdInvite()
	{
		int iduser = 0;
		String query = "SELECT * FROM joueur WHERE login=?";/*pratique plus sécurisée */
		try {
			PreparedStatement prepare = Connexion.getInstance().prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			prepare.setString(1, "invite");
			
			ResultSet result = prepare.executeQuery();
			
			//Je recupere l'id du compte invite
			if(result.first())
				iduser = result.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return iduser;
	}
	
		//**Constructeurs**//
	
	public Invite() {
		login = "invite";
		iduser = getIdInvite();
	}

	public Invite(String login) {
		super(login);
	}
	
/*afficher les informations joueur*/
	public void infoUser(){
		super.infoUser();
		System.out.println("Nom : Invite");
		System.out.println("Score : "+ score + " points");
	}

}

