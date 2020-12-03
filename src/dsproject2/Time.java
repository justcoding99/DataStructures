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
public class Time {
    private int hour, minute;

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public Time(String time) {
		String[] elements = time.split("\\.");
		if(elements[0].charAt(0) == '0') {
			this.hour = Integer.parseInt(String.valueOf(elements[0].charAt(1)));
		}
		else {
			this.hour = Integer.parseInt(elements[0]);
		}
		if(elements[1].charAt(0) == '0') {
			this.minute = Integer.parseInt(String.valueOf(elements[1].charAt(1)));
		}
		else {
			this.minute = Integer.parseInt(elements[1]);
		}
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public String toString() {
		String output = "";
		if(hour < 10) {
			output += "0" + this.hour;
		}
		else {
			output += this.hour;
		}
		if(minute < 10) {
			output += ".0" + this.minute;
		}
		else {
			output +=  "." + this.minute;
		}
		return output;
	}
}
