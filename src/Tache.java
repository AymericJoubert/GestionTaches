
public class Tache {
	private String description;
	private String auteur;
	private String status;
	private String affectation;
	
	public Tache(String description, String auteur){
		this.description = description;
		this.auteur = auteur;
		this.status = "libre";
		this.affectation = null;
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
