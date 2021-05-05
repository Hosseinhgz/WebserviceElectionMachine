package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	private int answer;
	
	public Answer() {
		
	}
	public Answer(int answer) {
		this.answer=answer;
	}
	public Answer(String answer) {
		this.answer=Integer.parseInt(answer);
	}
	public Answer(int id, int answer) {
		this.id=id;
		this.answer=answer;
	}
	public Answer(String id, String answer) {
		this.id=Integer.parseInt(id);
		this.answer=Integer.parseInt(answer);
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
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public void setAnswer(String answer) {
		try {
			this.id = Integer.parseInt(answer);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}

	public String toString() {
		return this.id+": "+this.answer;
	}
}
