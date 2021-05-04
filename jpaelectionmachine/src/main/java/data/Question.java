package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	private String question;
	
	public Question() {
		
	}
	public Question(String question) {
		this.question=question;
	}
	public Question(int id, String question) {
		this.id=id;
		this.question=question;
	}


	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}


	public String toString() {
		return this.id+": "+this.question;
	}
}
