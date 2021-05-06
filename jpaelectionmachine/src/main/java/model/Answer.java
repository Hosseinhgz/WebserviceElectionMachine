package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the answer database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int answer;

	public Answer() {
	}

	public Answer(String answer) {
		this.answer=Integer.parseInt(answer);
	}
	public Answer(int answer) {
		this.answer=answer;
	}

	public Answer(int id,int answer) {
		this.id=id;
		this.answer=answer;
	}
	public Answer(String id,String answer) {
		this.id=Integer.parseInt(id);
		this.answer=Integer.parseInt(answer);
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnswer() {
		return this.answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

}