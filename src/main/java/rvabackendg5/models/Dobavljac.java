package rvabackendg5.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Dobavljac implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "DOBAVLJAC_SEQ_GENERATOR", sequenceName = "DOBAVLJAC_SEQ",
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOBAVLJAC_SEQ_GENERATOR")
	private int id;
	private String naziv;
	private String adresa;
	private String kontakt;
	
	private Dobavljac() {
		
	}
	
	private Dobavljac(int id, String naziv, String adresa, String kontakt) {
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.kontakt = kontakt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}
	
	
}