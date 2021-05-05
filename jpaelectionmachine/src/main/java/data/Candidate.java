package data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the candidates database table.
 * 
 */
@Entity
@Table(name="candidates")
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
public class Candidate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CANDIDATE_ID")
	private int id;

	@Column(name="FIRST_NAME")
	private String firstName;

	private int ika;

	private String location;

	private String party;

	private String professional;

	private String surname;

	@Column(name="WHAT_ATHES_WANT_EDES")
	private String whatAthesWantEdes;

	@Column(name="WHY_COMMISSION")
	private String whyCommission;

	
	// Constructors
	public Candidate() {
	}

	public Candidate(int id, String firstName, String surname) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
	}
	public Candidate(String id, String firstName, String surname) {
		super();
		this.id = Integer.parseInt(id);
		this.firstName = firstName;
		this.surname = surname;
	}
	
	// getters and setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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