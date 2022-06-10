package dao;

import java.util.ArrayList;
import java.util.List;

public class Compagnies {
    private List<Compagnie> compagnies;
    private int nbr;

    
    
	public Compagnies(List<Compagnie> compagnies, int nbr) {
		
		this.compagnies = compagnies;
		this.nbr = this.compagnies.size();
	}
	public Compagnies() {
		
		this.compagnies = new ArrayList<Compagnie>();
		this.nbr = this.compagnies.size();
	}

	public List<Compagnie> getCompagnies() {
		return compagnies;
	}

	public void setCompagnies(List<Compagnie> compagnies) {
		this.compagnies = compagnies;
	}
	public void addCompagnies(Compagnie a) {
		this.compagnies.add(a);
	}
	public void removeCompagnie(String b)
	{
		for(int i=0;i<this.nbr;i++)
		{
			if(this.compagnies.get(i).equals(b)) {
				this.compagnies.remove(i);
				this.nbr--;
			}
		}
		
	}
	public void empty()
	{
		for(int i=0;i<this.nbr;i++)
		{
			
				this.compagnies.remove(i);
				nbr--;
			}
		}
		
	

	public int getNbr() {
		return this.compagnies.size();
	}

	public void setNbr(int nbr) {
		this.nbr = this.compagnies.size();
	}

  public void affiche() {
	  System.out.println(this.toString());
  }
	@Override
	public String toString() {
		return "Compagnies [compagnies=" + compagnies + ", nbr=" + nbr + "]";
	}

}
