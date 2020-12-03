/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproject2;

/**
 *
 * @author meltem koc
 */
public class Flight {
   private Date date;
	private Time time;
	private String fromCity, toCity, carrier;
	private int price;
	
	public Flight(Date date, Time time, String fromCity, String toCity, String carrier, int price) {
		this.date = date;
		this.time = time;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.carrier = carrier;
		this.price = price;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	public String getFromCity() {
		return fromCity;
	}
	
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	
	public String getToCity() {
		return toCity;
	}
	
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	
	public String getCarrier() {
		return carrier;
	}
	
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString() {
		return this.date.toString() + "\t" + this.time.toString() + "\t" + this.fromCity + "\t" + this.toCity + "\t" +
				this.carrier + "\t" + this.price + "TL";
	} 
}
