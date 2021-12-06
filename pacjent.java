/*
* Projekt 1 OSM
* Klasa pacjent
* Katarzyna Muter Katarzyna Rzeczyca
*/
package proj1;

//import java.util.ArrayList;

public class pacjent extends Pacjenci implements Comparable<pacjent>
{
	private String pImie;
	private String pNazwisko;
	private String pPlec;
	private Long pPesel;
	private String pUbezpieczenie;
	private Boolean pWykonanieBadania;
	private badanie pBadanie;
		
	public pacjent(String pImie, String pNazwisko, String pPlec, Long pPesel, String pUbezpieczenie, badanie pBadanie, Boolean pWykonanieBadania) 
	{ 
	    this.pImie = pImie;
	    this.pNazwisko = pNazwisko;
	    this.pPesel = pPesel;
	    this.pPlec = pPlec;
	    this.pUbezpieczenie = pUbezpieczenie;
	    this.pWykonanieBadania = pWykonanieBadania;
	    this.pBadanie = pBadanie;
	}
	
	@Override
	  public int hashCode() {
		  int prime = 31;
		  int result = 1;
		
		  result = prime * result	+ ((this.pPesel == null) ? 0 : this.pPesel.hashCode());
		
		  return (result);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		
		pacjent other = (pacjent) obj;
		if (this.pPesel == null) {
			if (other.pPesel != null)
				return(false);
		} else if (!this.pPesel.equals(other.pPesel))
			return(false);
		return(true);
	}
	
	@Override
	public int compareTo(pacjent other) {
		return (this.pPesel.compareTo(other.pPesel));
	}
	  
//	@Override
//	public String toString() {
//		return ("[" + this.pPesel + ", " + this.pImie + "]");
//	}
	
	public String getImie() 
	{
        return pImie;
    }
    public void setImie(String pImie) 
    {
        this.pImie = pImie;
    }
    
    public String getNazwisko() 
    {
        return pNazwisko;
    }
    public void setNazwisko(String pNazwisko) 
    {
        this.pNazwisko = pNazwisko;
    }
    
    public String getPlec() 
    {
        return pPlec;
    }
    public void setPlec(String pPlec) 
    {
        this.pPlec = pPlec;
    }
    
    public Long getPesel() 
    {
        return pPesel;
    }
    public void setPesel(Long long1) 
    {
        this.pPesel = long1;
    }
    
    public String getUbezpieczenie() 
    {
        return pUbezpieczenie;
    }
    public void setUbezpieczenie(String pUbezpieczenie) 
    {
        this.pUbezpieczenie = pUbezpieczenie;
    }
    
    public badanie getBadanie() 
    {
        return pBadanie;
    }
    public void setBadanie(badanie pBadanie) 
    {
        this.pBadanie = pBadanie;
    }
    public Boolean getpWykonanieBadania() {
		return pWykonanieBadania;
	}
	public void setpWykonanieBadania(Boolean pWykonanieBadania) {
		this.pWykonanieBadania = pWykonanieBadania;
	}
}
