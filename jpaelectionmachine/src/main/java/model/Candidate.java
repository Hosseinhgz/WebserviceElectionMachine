package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the candidate database table.
 * 
 */
@Entity
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String firstname;

	private int ika;

	private String location;

	private String party;

	private String professional;

	private String surname;

	@Column(name="WHAT_ATHES_WANT_EDES")
	private String whatAthesWantEdes;

	@Column(name="WHY_COMMISSION")
	private String whyCommission;

	public Candidate() {
	}

	public Candidate(String id, String surname, String firstname) {
		// TODO Auto-generated constructor stub
		this.id = Integer.parseInt(id);
		this.surname=surname;
		this.firstname=firstname;
	}
	public Candidate(int id, String surname, String firstname) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.surname=surname;
		this.firstname=firstname;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getIka() {
		return this.ika;
	}

	public void setIka(int ika) {
		this.ika = ika;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getWhatAthesWantEdes() {
		return this.whatAthesWantEdes;
	}

	public void setWhatAthesWantEdes(String whatAthesWantEdes) {
		this.whatAthesWantEdes = whatAthesWantEdes;
	}

	public String getWhyCommission() {
		return this.whyCommission;
	}

	public void setWhyCommission(String whyCommission) {
		this.whyCommission = whyCommission;
	}

}