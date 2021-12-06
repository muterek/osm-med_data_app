/*
* Projekt 1 OSM
* Katarzyna Muter Katarzyna Rzeczyca
*/
package proj1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class proj1 extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//------------------------------- DEKLARACJA ZMIENNYCH ------------------------------------
	JDateChooser kalendarz;
	JPanel badanie, danePacjenta, listaPacjentow, listaPacjentow1, listaPacjentow2;
	JLabel limie, lnazwisko, lpesel, lplec, lubezpieczenie, lskurcz, lrozkurcz, ltetno, ldata;
	JTextField timie, tnazwisko;
	JNumberTextField tpesel, tskurcz, trozkurcz, ttetno;
	JRadioButton rpleck, rplecm;
	ButtonGroup bg;	//jeden JRadioButton bêdzie móg³ byæ wybrany
	JComboBox <String> cubezpieczenie;
	JButton bzapisz, banuluj, bdodaj, busun, bbadanieZapisz, bbadanieAnuluj;
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints gbcdanePacjenta = new GridBagConstraints();
	GridBagConstraints gbcbadanie = new GridBagConstraints();
	public static String[] opcjeUbez = {"NFZ", "Prywatne", "Brak"};
	JTable tTabela;
	Calendar rightNow;
	//ArrayList<String> aListaPacjentow = new ArrayList<String>();
	ArrayList<pacjent> Pacjenci = new ArrayList<pacjent>();
	PacjenciTableModel model;
	pacjent selectedPacjent;
	int day;
	
	public proj1()
	{
		
//-------------------------------------- WYGL¥D ------------------------------------------
		//setLocationRelativeTo(null);
		setLayout(new GridBagLayout()); //ustawienie rozmieszczenia okna g³ównego
		setTitle("Rejestracja wyników badañ"); //dodanie tytu³u okna g³ównego
		setBackground(Color.white); //ustawienie t³a

		gbc.insets = new Insets(5,5,5,5); //dodanie przerw miêdzy panelami
		
//----------------------- DANE PACJENTA -----------------------------------------------
		
		danePacjenta = new JPanel(); //dodanie panelu (Dane Pacjenta)
		danePacjenta.setBorder(BorderFactory.createTitledBorder("Dane Pacjenta"));
		gbc.gridx = 0;
		gbc.gridy = 0;
		danePacjenta.setBackground(Color.white);
		add(danePacjenta, gbc);
				
		danePacjenta.setLayout(new GridBagLayout()); //ustawienie rozmieszczenia dla panelu Dane Pacjenta
		gbcdanePacjenta.insets = new Insets(5,5,5,5); //dodanie przerw miêdzy komponentami
		
		limie = new JLabel("Imiê: "); //napis "Imiê"
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 0;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(limie, gbcdanePacjenta);
		
		timie = new JTextField(""); //pole tekstowe "Imiê"
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 0;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(timie, gbcdanePacjenta);
		
		lnazwisko = new JLabel("Nazwisko: ");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 1;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(lnazwisko, gbcdanePacjenta);
		
		tnazwisko = new JTextField("");
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 1;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(tnazwisko, gbcdanePacjenta);
		
		lpesel = new JLabel("PESEL: ");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 2;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(lpesel, gbcdanePacjenta);
		
		tpesel = new JNumberTextField();
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 2;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(tpesel, gbcdanePacjenta);
		//d³ugoœæ numeru PESEL nie wiêksza ni¿ 11 cyfr
		tpesel.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tpesel.getText().length()>= 11) 
		            e.consume(); 
		    }  
		});
	
		lplec = new JLabel("P³eæ: ");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 3;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(lplec, gbcdanePacjenta);
		
		bg = new ButtonGroup(); //aby jeden JRadioButton móg³ byæ wciœniêty
		
		rpleck= new JRadioButton("Kobieta", true);
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 3;
		gbcdanePacjenta.gridwidth = 1;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		rpleck.setBackground(Color.white);
		bg.add(rpleck);
		danePacjenta.add(rpleck, gbcdanePacjenta);
		
		rplecm= new JRadioButton("Mê¿czyzna", false);
		gbcdanePacjenta.gridx = 3;
		gbcdanePacjenta.gridy = 3;
		gbcdanePacjenta.gridwidth = 1;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		rplecm.setBackground(Color.white);
		bg.add(rplecm);
		danePacjenta.add(rplecm, gbcdanePacjenta);
			
		lubezpieczenie = new JLabel("Ubezpieczenie: ");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 4;gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(lubezpieczenie, gbcdanePacjenta);
		
		cubezpieczenie= new JComboBox <String> (opcjeUbez);
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 4;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		cubezpieczenie.setBackground(Color.white);
		danePacjenta.add(cubezpieczenie, gbcdanePacjenta);
		
		bzapisz = new JButton("Zapisz");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 5;
		gbcdanePacjenta.gridwidth = 1;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(bzapisz, gbcdanePacjenta);
		
		banuluj = new JButton("Anuluj");
		gbcdanePacjenta.gridx = 1;
		gbcdanePacjenta.gridy = 5;
		gbcdanePacjenta.gridwidth = 1;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(banuluj, gbcdanePacjenta);
				
		bzapisz.addActionListener(this);
		banuluj.addActionListener(this);
		rpleck.addActionListener(this);
		rplecm.addActionListener(this);
		
//------------------------------ BADANIE ----------------------------------------
		
		badanie = new JPanel(); //dodanie panelu "Badanie"
		badanie.setBorder(BorderFactory.createTitledBorder("Badanie"));
		gbc.gridx = 0;
		gbc.gridy = 1;
		badanie.setBackground(Color.white);
		this.add(badanie, gbc);
		badanie.setLayout(new GridBagLayout());
		gbcbadanie.insets = new Insets(5,5,5,5);
		
		ldata = new JLabel("Data: ");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 0;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(ldata, gbcbadanie);
		
		kalendarz = new JDateChooser();
		gbcbadanie.gridx = 2;
		gbcbadanie.gridy = 0;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
    	badanie.add(kalendarz, gbcbadanie);
    	rightNow = Calendar.getInstance();
    	
		lskurcz = new JLabel("Ciœnienie skurczowe: ");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 1;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(lskurcz, gbcbadanie);
		
		tskurcz = new JNumberTextField();
		gbcbadanie.gridx = 2;
		gbcdanePacjenta.gridy = 1;
		gbcbadanie.gridwidth = 2;
		tskurcz.setPreferredSize(new Dimension(165, 20));
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(tskurcz, gbcbadanie);
		
		lrozkurcz = new JLabel("Ciœnienie rozkurczowe: ");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 2;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(lrozkurcz, gbcbadanie);
		
		trozkurcz = new JNumberTextField();
		gbcbadanie.gridx = 2;
		gbcdanePacjenta.gridy = 2;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(trozkurcz, gbcbadanie);
		
		ltetno = new JLabel("Têtno: ");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 3;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(ltetno, gbcbadanie);
		
		ttetno = new JNumberTextField();
		gbcbadanie.gridx = 2;
		gbcdanePacjenta.gridy = 3;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(ttetno, gbcbadanie);
		
		bbadanieZapisz = new JButton("Zapisz");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 4;
		gbcbadanie.gridwidth = 1;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(bbadanieZapisz, gbcbadanie);
		
		bbadanieAnuluj = new JButton("Anuluj");
		gbcbadanie.gridx = 1;
		gbcbadanie.gridy = 4;
		gbcbadanie.gridwidth = 1;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(bbadanieAnuluj, gbcbadanie);
		
		bbadanieZapisz.addActionListener(this);
		bbadanieAnuluj.addActionListener(this);
		
//------------------------------ LISTA PACJENTÓW -------------------------------------
		
		listaPacjentow = new JPanel(); // dodanie panelu "Lista Pacjetów"
		BorderLayout borderLayout = new BorderLayout();
		listaPacjentow.setLayout(borderLayout);
		listaPacjentow1 = new JPanel();
		listaPacjentow1.setBackground(Color.white);
		listaPacjentow2 = new JPanel();
		listaPacjentow2.setBackground(Color.white);
		borderLayout.addLayoutComponent(listaPacjentow1, BorderLayout.NORTH);
		listaPacjentow.add(listaPacjentow1);
		borderLayout.addLayoutComponent(listaPacjentow2, BorderLayout.SOUTH);
		listaPacjentow.add(listaPacjentow2);

		listaPacjentow.setBorder(BorderFactory.createTitledBorder("Lista Pacjentów"));
		listaPacjentow.setBackground(Color.white);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		add(listaPacjentow, gbc);
		
		model = new PacjenciTableModel(Pacjenci);
		tTabela = new JTable(model);
		tTabela.setRowSelectionAllowed(true);
				
		listaPacjentow1.add(tTabela);
		tTabela.setBackground(Color.white);
		tTabela.setFillsViewportHeight(true);
		JScrollPane jsp = new JScrollPane(tTabela);
		jsp.setPreferredSize(new Dimension(500,320));
		jsp.setViewportView(tTabela);
		listaPacjentow1.add(jsp);
		
		listaPacjentow2.setLayout(new BorderLayout());
		JPanel przyciski = new JPanel();
		bdodaj = new JButton("Dodaj");
		busun = new JButton("Usuñ");
		przyciski.add(bdodaj);
		przyciski.add(busun);
		przyciski.setBackground(Color.white);
		listaPacjentow2.add(przyciski, BorderLayout.WEST);
		
		bdodaj.addActionListener(this);
		busun.addActionListener(this);
		
		tTabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(tTabela.getSelectedRow() < 0) {
					return;
				}
				cubezpieczenie.setEnabled(true);
				rpleck.setEnabled(true);
				rplecm.setEnabled(true);
				timie.setEnabled(true);
				tnazwisko.setEnabled(true);
				tpesel.setEnabled(true);
				tskurcz.setEnabled(true);
				trozkurcz.setEnabled(true);
				ttetno.setEnabled(true);
				kalendarz.setEnabled(true);

				int wiersz = tTabela.getSelectedRow();
				pacjent pacj = Pacjenci.get(wiersz);
				selectedPacjent = pacj;
				badanie bad = pacj.getBadanie();
				
				timie.setText(pacj.getImie());
				tnazwisko.setText(pacj.getNazwisko());
				tpesel.setText(pacj.getPesel().toString());
				if(pacj.getPlec() == "K") {
					rpleck.setSelected(true);
				} else if(pacj.getPlec() == "M") {
					rplecm.setSelected(true);
				}			
				cubezpieczenie.setSelectedItem(pacj.getUbezpieczenie().toString());
				if(bad!=null) {
					if(bad.getSkurcz()!=null)tskurcz.setText(bad.getSkurcz().toString());
					if(bad.getRozkurcz()!=null)trozkurcz.setText(bad.getRozkurcz().toString());
					if(bad.getTetno()!=null)ttetno.setText(bad.getTetno().toString());
					kalendarz.setDate(bad.getData());
				}
				
			}
		});
		
		pack();
		setResizable(false);
		
		onInit();
	}
	
	private void onInit() {
		cubezpieczenie.setEnabled(false);
		rpleck.setEnabled(false);
		rplecm.setEnabled(false);
		timie.setEnabled(false);
		tnazwisko.setEnabled(false);
		tpesel.setEnabled(false);
		tskurcz.setEnabled(false);
		kalendarz.setEnabled(false);
		trozkurcz.setEnabled(false);
		ttetno.setEnabled(false);
	
	}
	
	public class JNumberTextField extends JTextField {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
	    public void processKeyEvent(KeyEvent ev) {
	        if (Character.isDigit(ev.getKeyChar())) {
	            super.processKeyEvent(ev);
	        }
	        if(ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)
	        {  
	            super.processKeyEvent(ev);
	        }
	        ev.consume();
	        return;
	    }
	    public Long getNumber() {
	        Long result = null;
	        String text = getText();
	        if (text != null && !"".equals(text)) {
	            result = Long.valueOf(text);
	        }
	        return result;
	    }
	}
	
//--------------------------------- OBS£UGA ZDARZEÑ ------------------------------

	public void actionPerformed(ActionEvent event) {

		pacjent pacj = new pacjent(null, null, null, null, null, null, null);
		pacj.setImie(timie.getText());
		pacj.setNazwisko(tnazwisko.getText());
		if(rpleck.isSelected()) {
			pacj.setPlec("K");
		} else if(rplecm.isSelected()) {
			pacj.setPlec("M");
		}
		pacj.setPesel(tpesel.getNumber());
		pacj.setUbezpieczenie(cubezpieczenie.getSelectedItem().toString());
		pacj.setpWykonanieBadania(false);
		
		badanie bad = new badanie(null, null, null, null);
		bad.setSkurcz(tskurcz.getNumber());
		bad.setRozkurcz(trozkurcz.getNumber());
		bad.setTetno(ttetno.getNumber());	
		bad.setData(kalendarz.getDate());
		
		Object source = event.getSource();
		
		if (source == bdodaj) {
			cubezpieczenie.setEnabled(true);
			rpleck.setEnabled(true);
			rplecm.setEnabled(true);
			timie.setEnabled(true);
			tnazwisko.setEnabled(true);
			tpesel.setEnabled(true);
			tskurcz.setEnabled(true);
			trozkurcz.setEnabled(true);
			ttetno.setEnabled(true);
			kalendarz.setEnabled(true);
			
			selectedPacjent = null;
			
			timie.setText("");
			tnazwisko.setText("");
			tpesel.setText("");
			tskurcz.setText("");
			trozkurcz.setText("");
			ttetno.setText("");
			cubezpieczenie.setSelectedItem("NFZ");
			rpleck.setSelected(true);
			kalendarz.setDate(rightNow.getTime());
		}
		if (source == bzapisz) {
			boolean eq = false;	//oznaczenie robocze - miara równoœci wprowadzanego pacjenta z obecnymi na liscie
			if(selectedPacjent == null || selectedPacjent.equals(pacj)!=true) {
			for (pacjent pacjent : Pacjenci) {
				if (pacj.equals(pacjent)==true) {
					eq = true;
					break;
				}
			}
			}
			if(rpleck.isSelected()) {
				pacj.setPlec("K");
			} else if(rplecm.isSelected()) {
				pacj.setPlec("M");
			}
			if (timie.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz imiê.");
				return;
			}
			if (timie.getText().contains("1")||timie.getText().contains("2")||timie.getText().contains("3")||timie.getText().contains("4")||timie.getText().contains("5")||timie.getText().contains("6")||timie.getText().contains("7")||timie.getText().contains("8")||timie.getText().contains("9")||timie.getText().contains("0")) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Imiê nie powinno zawieraæ cyfr.");
				return;
			}
			if (tnazwisko.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz nazwisko.");
				return;
			}
			if (tnazwisko.getText().contains("1")||tnazwisko.getText().contains("2")||tnazwisko.getText().contains("3")||tnazwisko.getText().contains("4")||tnazwisko.getText().contains("5")||tnazwisko.getText().contains("6")||tnazwisko.getText().contains("7")||tnazwisko.getText().contains("8")||tnazwisko.getText().contains("9")||tnazwisko.getText().contains("0")) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Nazwisko nie powinno zawieraæ cyfr.");
				return;
			}
			if (tpesel.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz PESEL.");
				return;
			}
			if (tpesel.getText().length()< 11) { 
				JOptionPane.showMessageDialog(new JInternalFrame(), "Pesel jest zbyt krótki.");
				return;
			}
			if (eq == true) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Taki pacjent juz  istnieje!");
				eq = false;
				return;
			}
			if(selectedPacjent == null) {
				Pacjenci.add(pacj);
			} else {
				int wiersz = tTabela.getSelectedRow();
				Pacjenci.set(wiersz, pacj);
			}
			
			PacjenciTableModel model = new PacjenciTableModel(Pacjenci);
			tTabela.setModel(model);
			
			timie.setText("");
			tnazwisko.setText("");
			tpesel.setText("");
			tskurcz.setText("");
			trozkurcz.setText("");
			ttetno.setText("");
			cubezpieczenie.setSelectedItem("NFZ");
			kalendarz.setDate(rightNow.getTime());
			rpleck.setSelected(true);
			cubezpieczenie.setEnabled(false);
			rpleck.setEnabled(false);
			rplecm.setEnabled(false);
			timie.setEnabled(false);
			tnazwisko.setEnabled(false);
			tpesel.setEnabled(false);
			tskurcz.setEnabled(false);
			kalendarz.setEnabled(false);
			trozkurcz.setEnabled(false);
			ttetno.setEnabled(false);
		}
		if (source == banuluj) {
			timie.setText("");
			tnazwisko.setText("");
			tpesel.setText("");
			cubezpieczenie.setSelectedItem("NFZ");
			kalendarz.setDate(rightNow.getTime());
			rpleck.setSelected(true);
			return;
		}
		if (source == bbadanieZapisz) {
			
			if (tskurcz.getText().isEmpty() || trozkurcz.getText().isEmpty() || ttetno.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Nie wprowadzono wyników badania.");
				return;
			}
			if (((!tskurcz.getText().isEmpty()) && tskurcz.getNumber()>300) || ((!trozkurcz.getText().isEmpty()) && trozkurcz.getNumber()>150) || ((!ttetno.getText().isEmpty()) && ttetno.getNumber()>200)) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Z³e wyniki badania.");
				return;
			}
			boolean eq = false;	//oznaczenie robocze - miara równoœci wprowadzanego pacjenta z obecnymi na liscie
			if(selectedPacjent == null || selectedPacjent.equals(pacj)!=true) {
			for (pacjent pacjent : Pacjenci) {
				if (pacj.equals(pacjent)==true) {
					eq = true;
					break;
				}
			}			
			}
			if(rpleck.isSelected()) {
				pacj.setPlec("K");
			} else if(rplecm.isSelected()) {
				pacj.setPlec("M");
			}
			if (timie.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz imiê.");
				return;
			}
			if (timie.getText().contains("1")||timie.getText().contains("2")||timie.getText().contains("3")||timie.getText().contains("4")||timie.getText().contains("5")||timie.getText().contains("6")||timie.getText().contains("7")||timie.getText().contains("8")||timie.getText().contains("9")||timie.getText().contains("0")) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Imiê nie powinno zawieraæ cyfr.");
				return;
			}
			if (tnazwisko.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz nazwisko.");
				return;
			}
			if (tnazwisko.getText().contains("1")||tnazwisko.getText().contains("2")||tnazwisko.getText().contains("3")||tnazwisko.getText().contains("4")||tnazwisko.getText().contains("5")||tnazwisko.getText().contains("6")||tnazwisko.getText().contains("7")||tnazwisko.getText().contains("8")||tnazwisko.getText().contains("9")||tnazwisko.getText().contains("0")) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Nazwisko nie powinno zawieraæ cyfr.");
				return;
			}
			if (tpesel.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz PESEL.");
				return;
			}
			if (tpesel.getText().length()< 11) { 
				JOptionPane.showMessageDialog(new JInternalFrame(), "Pesel jest zbyt krótki.");
				return;
			}
			
			if (eq == true) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Taki pacjent juz  istnieje!");
				eq = false;
				return;
			}
			if(selectedPacjent == null) {
				Pacjenci.add(pacj);
			} else {
				int wiersz = tTabela.getSelectedRow();
				Pacjenci.set(wiersz, pacj);
			}
			
			pacj.setBadanie(bad);
			pacj.setpWykonanieBadania(true);
			

			PacjenciTableModel model = new PacjenciTableModel(Pacjenci);
			tTabela.setModel(model);
			
			timie.setText("");
			tnazwisko.setText("");
			tpesel.setText("");
			tskurcz.setText("");
			trozkurcz.setText("");
			ttetno.setText("");
			cubezpieczenie.setSelectedItem("NFZ");
			kalendarz.setDate(rightNow.getTime());
			rpleck.setSelected(true);
			cubezpieczenie.setEnabled(false);
			rpleck.setEnabled(false);
			rplecm.setEnabled(false);
			timie.setEnabled(false);
			tnazwisko.setEnabled(false);
			tpesel.setEnabled(false);
			tskurcz.setEnabled(false);
			kalendarz.setEnabled(false);
			trozkurcz.setEnabled(false);
			ttetno.setEnabled(false);
		}
		
		if (source == bbadanieAnuluj) {
			tskurcz.setText("");
			trozkurcz.setText("");
			ttetno.setText("");
			return;
		}
		
		if (source == busun) {
			int wiersz = tTabela.getSelectedRow();
			if(tTabela.isRowSelected(wiersz)) {
			Pacjenci.remove(wiersz);
			PacjenciTableModel model = new PacjenciTableModel(Pacjenci);
			tTabela.setModel(model);
			}else {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Zaznacz wiersz, który chcesz usun¹æ.");
				return;
			}
			
			timie.setText("");
			tnazwisko.setText("");
			tpesel.setText("");
			tskurcz.setText("");
			trozkurcz.setText("");
			ttetno.setText("");
			cubezpieczenie.setSelectedItem("NFZ");
			kalendarz.setDate(rightNow.getTime());
			rpleck.setSelected(true);
			cubezpieczenie.setEnabled(false);
			rpleck.setEnabled(false);
			rplecm.setEnabled(false);
			timie.setEnabled(false);
			tnazwisko.setEnabled(false);
			tpesel.setEnabled(false);
			tskurcz.setEnabled(false);
			kalendarz.setEnabled(false);
			trozkurcz.setEnabled(false);
			ttetno.setEnabled(false);
		}
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			proj1 projekt1 = new proj1();
			projekt1.setVisible(true);
			projekt1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	    });
	}
	
}
