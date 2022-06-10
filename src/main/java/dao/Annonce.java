package dao;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Annonce {
	private static final AtomicInteger count=new AtomicInteger(0);
	private  String id;
	private String nomCompagnie;
	private String date;
	private String profil;
	private int nbr_posts;
	private String region;
	private String secteur;
	private String ville;
	private String domaine;
	private List<String> techniques ;//EXPERIENCE Nature du contrat NIVEAU EXPERIENCE
	private String niveauEtude;
	private String typeContrat;
	private String Niveau_Experience;

  
  //Methodes


	public Annonce( String nomCompagnie, String date, String profil, int nbr,String region,
			String secteur, List<String> techniques,String domaine, String etude, String cdi, String niveau_Experience) {

		this.id = String.valueOf(count.incrementAndGet());
		this.nomCompagnie = nomCompagnie;
		this.date = date;
		this.profil = profil;
		this.nbr_posts=nbr;
		this.region = region;
		this.secteur = secteur;
		this.techniques = techniques;
		this.niveauEtude = etude;
		this.typeContrat = cdi;
		this.domaine=domaine;
		Niveau_Experience = niveau_Experience;
	}
	public Annonce() {
		
		this.id = String.valueOf(count.incrementAndGet());
		this.nomCompagnie = null;
		this.date = null;
		this.profil = null;
		this.nbr_posts=0;
		this.id = null;
		this.domaine=null;
		this.region = null;
		this.secteur = null;
		this.techniques = new ArrayList<String>();
		this.niveauEtude = null;
		this.typeContrat = null;
		Niveau_Experience = null;
	}

	
	
	public String getCdi() {
		return typeContrat;
	}
	public void setCdi(String cdi) {
		this.typeContrat = cdi;
	}
	public String getNiveau_Experience() {
		return Niveau_Experience;
	}
	public void setNiveau_Experience(String niveau_Experience) {
		Niveau_Experience = niveau_Experience;
	}
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}
	public void setTechniques(List<String> techniques) {
		this.techniques = techniques;
	}
	//Getters
	public String getId(){
		return this.id;
	}
	public void setId() {
		
		 this.id=String.valueOf(count.getAndIncrement());
	}
	public String getNomCompagnie(){
		return this.nomCompagnie;
	}
	public String getDate(){
		return this.date;
	}
	public String getProfil(){
		return this.profil;
	}

	public String getRegion(){
		return this.region;
	}
	public String getSecteur(){
		return this.secteur;
	}
	public List<String> getTechniques(){
		return this.techniques;
	}


	public void setId(String id){
		this.id = String.valueOf(count.incrementAndGet());
	}
	public void setNomCompagnie(String nomCompagnie){
		this.nomCompagnie = nomCompagnie;
	}
	public void setDate(String date){
		this.date = date;
	}
	public void setProfil(String profil){
		this.profil = profil;
	}

	public void setRegion(String region){
		this.region = region;
	}
	public void getSecteur(String secteur){
		this.secteur = secteur;
	}
   public void addTechniques(String tech) {
	   this.techniques.add(tech);
   }

public void emptyAnnonce() {
	for(int i=0;i<this.techniques.size();i++)
	{
		this.techniques.remove(i);
	}
}

  public void affiche() {
	  System.out.println(this.toString());
  }






@Override
public String toString() {
	return "Annonce [id=" + id + ", nomCompagnie=" + nomCompagnie + ", date=" + date + ", profil=" + profil
			+ ", nbr_posts=" + nbr_posts + ", region=" + region + ", secteur=" + secteur + ", ville=" + ville
			+ ", domaine=" + domaine + ", techniques=" + techniques + ", niveauEtude=" + niveauEtude + ", typeContrat="
			+ typeContrat + ", Niveau_Experience=" + Niveau_Experience + "]";
}
public int getNbr_posts() {
	return nbr_posts;
}
public void setNbr_posts(int nbr_posts) {
	this.nbr_posts = nbr_posts;
}


public String getDomaine() {
	return domaine;
}
public void setDomaine(String domaine) {
	this.domaine = domaine;
}
public String getNiveauEtude() {
	return niveauEtude;
}


public void setNiveauEtude(String niveauEtude) {
	this.niveauEtude = niveauEtude;
}
public String getVille() {
	return ville;
}
public void setVille(String ville) {
	this.ville = ville;
}
public String getTypeContrat() {
	return typeContrat;
}
public void setTypeContrat(String typeContrat) {
	this.typeContrat = typeContrat;
}

}