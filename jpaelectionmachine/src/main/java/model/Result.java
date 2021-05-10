package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the result database table.
 * 
 */
@Entity
@NamedQuery(name="Result.findAll", query="SELECT a FROM Result a")
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double result;

	public Result() {
	}

	public Result(String result) {
		this.result=Integer.parseInt(result);
	}
	public Result(int result) {
		this.result=result;
	}

	public Result(int id,int result) {
		this.id=id;
		this.result=result;
	}
	public Result(String id,String result) {
		this.id=Integer.parseInt(id);
		this.result=Integer.parseInt(result);
	}
	public Result(String id,Double result) {
		this.id=Integer.parseInt(id);
		this.result=result;
	}	
	public Result(int id,Double result) {
		this.id=id;
		this.result=result;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getResult() {
		return this.result;
	}

	public void setResult(double d) {
		this.result = d;
	}

}