package dao;

public class Compagnie {
	private int id;
	private String nom;
	private String site;
	private String secteur;
	private String ville;
	private String pays;
	private String urlImage;

	public Compagnie(String nom,String site, String ville, String pays, String secteur,String urlImage) {
		this.nom = nom;
		this.site = site;
		this.ville = ville;
		this.pays = pays;
		this.secteur = secteur;
		this.urlImage = urlImage;
	}

	//Getters
	public int getId(){
		return this.id;
	}
	public String getNom(){
		return this.nom;
	}
	public String getSite(){
		return this.site;
	}
	public String getVille(){
		return this.ville;
	}
	public String getPays(){
		return this.pays;
	}
	public String getSecteur(){
		return this.secteur;
	}
	public String getUrlImage(){
		return this.urlImage;
	}

	//Setters
	public void setId(int id){
		this.id = id;
	}
	public void setNom(String nom){
		this.nom = nom.trim();
	}
	public void getSite(String site){
		this.site = site.trim();
	}
	public void getVille(String ville){
		this.ville = ville.trim();
	}
	public void getPays(String pays){
		this.pays = pays.trim();
	}
	public void getSecteur(String secteur){
		this.secteur = secteur.trim();
	}
	public void getUrlImage(String urlImage){
		this.urlImage = urlImage.trim();
	}
	
  public void affiche() {
	  System.out.println(this.toString());
  }

	@Override
	public String toString() {
		return "Compagnie [id = " + ", urlImage = " + getUrlImage() + ", " + getId() + ", nom = " + getNom() + ", site = " + getSite() + ", secteur = " + getSecteur() + ", ville = " + getVille() + ", pays = " + getPays() + "]";
	}

}