package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the candidateanswers database table.
 * 
 */
@Entity
@Table(name="candidateanswers")
@NamedQuery(name="Candidateanswer.findAll", query="SELECT c FROM Candidateanswer c")
public class Candidateanswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int candidateid;
	private int question;
	private int candidateans;

	@Column(name="`COMMENT`")
	private String comment;


	public Candidateanswer() {
	}

	public Candidateanswer(int candidateid, int question,int candidateans) {
		super();
		this.candidateans = candidateans;
		this.candidateid = candidateid;
		this.question = question;
		this.comment = "candidate"+candidateid+"answer to question"+question;
	}
	public Candidateanswer(String candidateid, String question,String candidateans) {
		super();
		this.candidateans = Integer.parseInt(candidateans);
		this.candidateid = Integer.parseInt(candidateid);
		this.question = Integer.parseInt(question);
		this.comment = "candidate"+candidateid+"answer to question"+question;
	}
	public Candidateanswer(String id, String candidateid, String question,String candidateans) {
		super();
		this.id = Integer.parseInt(id);
		this.candidateans = Integer.parseInt(candidateans);
		this.candidateid = Integer.parseInt(candidateid);
		this.question = Integer.parseInt(question);
		this.comment = "candidate"+candidateid+"answer to question"+question;
	}
	public Candidateanswer(int id, int candidateid, int question,int candidateans) {
		super();
		this.id = id;
		this.candidateans = candidateans;
		this.candidateid = candidateid;
		this.question = question;
		this.comment = "candidate"+candidateid+"answer to question"+question;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCandidateans() {
		return this.candidateans;
	}

	public void setCandidateans(int candidateans) {
		this.candidateans = candidateans;
	}

	public int getCandidateid() {
		return this.candidateid;
	}

	public void setCandidateid(int candidateid) {
		this.candidateid = candidateid;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getQuestion() {
		return this.question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

}