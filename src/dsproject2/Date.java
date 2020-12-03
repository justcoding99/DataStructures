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
public class Date {
    private int day, month, year;

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Date(String date) {
		String[] elements = date.split("-");
		if(elements[0].charAt(0) == '0') {
			this.day = Integer.parseInt(String.valueOf(elements[0].charAt(1)));
		}
		else {
			this.day = Integer.parseInt(elements[0]);
		}
		if(elements[1].charAt(0) == '0') {
			this.month = Integer.parseInt(String.valueOf(elements[1].charAt(1)));
		}
		else {
			this.month = Integer.parseInt(elements[1]);
		}
		this.year = Integer.parseInt(elements[2]);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString() {
		String output = "";
		if(day < 10) {
			output += "0" + this.day;
		}
		else {
			output += this.day;
		}
		if(month < 10) {
			output += "-0" + this.month;  
		}
		else {
			output += "-" + this.month;
		}
		return output += "-" + this.year;
	}
    
}
