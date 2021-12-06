/*
* Projekt 1 OSM
* Klasa badanie
* Katarzyna Muter Katarzyna Rzeczyca
*/
package proj1;
import java.util.Date;

public class badanie  
{
	private Long skurcz;
	private Long rozkurcz;
	private Long tetno;
	private Date data;	//data badania - JCalendar
	
	public badanie (Long skurcz, Long rozkurcz, Long tetno, Date data) {
		this.skurcz = skurcz;
		this.rozkurcz = rozkurcz;
		this.tetno = tetno;
		this.data = data;
	}
	
    public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getSkurcz() 
    {
        return skurcz;
    }
    public void setSkurcz(Long skurcz) 
    {
        this.skurcz = skurcz;
    }    
    public Long getRozkurcz() 
    {
        return rozkurcz;
    }
    public void setRozkurcz(Long rozkurcz) 
    {
        this.rozkurcz = rozkurcz;
    }    
    public Long getTetno() 
    {
        return tetno;
    }
    public void setTetno(Long tetno) 
    {
        this.tetno = tetno;
    }
}
