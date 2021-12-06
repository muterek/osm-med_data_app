/*
* Projekt 1 OSM
* Klasa Pacjenci
* Katarzyna Muter Katarzyna Rzeczyca
*/
package proj1;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PacjenciTableModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] { "Imiê i Nazwisko", "P³eæ", "PESEL", "Ubezpieczenie", "Badanie"};
	private ArrayList<pacjent> listaPacjentow;
	
	public PacjenciTableModel(ArrayList<pacjent> podanaListaPacjentow) {
		this.listaPacjentow = podanaListaPacjentow;
	}
	
	public void removeRow(int rowIndex) {
		listaPacjentow.remove(rowIndex);
		fireTableRowsDeleted(rowIndex,rowIndex);
	}
	
	public int getRowCount() {
		int size;
		if(listaPacjentow ==null) {
			size = 0;
		}
		else {
			size = listaPacjentow.size();
		}
		return size;
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	public Object getValueAt(int row, int col) {
		Object temp = null;
		if(col ==0) {
			temp = listaPacjentow.get(row).getImie() + " " + listaPacjentow.get(row).getNazwisko();
		}
		else if (col == 1) {
			temp = listaPacjentow.get(row).getPlec();			
		}
		else if (col == 2) {
			temp = listaPacjentow.get(row).getPesel();			
		}
		else if (col == 3) {
			temp = listaPacjentow.get(row).getUbezpieczenie();			
		}
		else if (col == 4) {
			temp = listaPacjentow.get(row).getpWykonanieBadania();			
		}
		return temp;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int col) {
		if (col == 4) {
			return Boolean.class;
		}
		else if (col == 2) {
			return Double.class;
		}
		else {
			return String.class;
		}
	}
}
