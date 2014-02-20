
public class Tache {
	private String description;
	private String auteur;
	private String status;
	private String affectation;
	private int num;
	
	public Tache(String description, String auteur, int num){
		this.description = description;
		this.auteur = auteur;
		this.status = "libre";
		this.affectation = "Non Affectee";
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Tache(String description, String auteur, String status, String affectation, int num){
		this.description = description;
		this.auteur = auteur;
		this.status = status;
		this.affectation = affectation;
		this.num = num;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAffectation() {
		return affectation;
	}

	public void setAffectation(String affectation) {
		this.affectation = affectation;
	}
	
	
}
