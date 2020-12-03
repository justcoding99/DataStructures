/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproject2;

import java.util.Queue;

/**
 *
 * @author meltem koc
 */
public class TreeDate {
   TreeNodeDate root;
	
	public TreeDate() {
			root = null;
		}

		//A�a�ta girilen tarihi ar�yor.
		public TreeNodeDate search(Date value) {
			TreeNodeDate d;
			d = root;
		while(d != null) {
			if(d.data.toString().equals(value.toString())) {
				return d;
			}
			else if(isBeforeDate(value, d.data)) {
				d = d.left;
			}
			else {
				d = d.right;
			}
		}
		return null;
	}
	
	//Girilen tarihe yak�n iki tarih d�nd�r�yor
	public TreeNodeDate[] closestDates(Date value) {
		TreeNodeDate closest[] = new TreeNodeDate[2];
		TreeNodeDate d, closestBefore = null, closestAfter = null;
		d = root;
		while(d != null) {
			if(differenceBetweenDates(value, d.data) < 0) {
				closestBefore = d;
				d = d.right;
			}
			else if(differenceBetweenDates(value, d.data) > 0) {
				closestAfter = d;
				d = d.left;
			}
		}
		closest[0] = closestBefore;
		closest[1] = closestAfter;
		return closest;
	}
	
	//A�aca node ekliyor
	public void insert(TreeNodeDate node, Flight flight) {
		TreeNodeDate y = null;
		TreeNodeDate x = root;
		while(x != null) {
			y = x;
			if(node.data.toString().equals(y.data.toString())) {
				y.flights[y.numberOfFlights] = flight;
				y.numberOfFlights++;
				return;
			}
			if(isBeforeDate(node.data, x.data)) {
				x = x.left;
			}
			else {
				x = x.right;
			}
		}
		if(y == null) {
			root = node;
			root.flights[0] = flight;
			root.numberOfFlights++;
		}
		else if(isBeforeDate(node.data, y.data)) {
			if(y != null) {
				if(node.data.toString().equals(y.data.toString())) {
					y.flights[y.left.numberOfFlights] = flight;
					y.numberOfFlights++;
					return;
				}
			}
			y.left = node;
			y.left.flights[0] = flight;
			y.left.numberOfFlights++;
		}
		else {
			if(y != null) {
				if(node.data.toString().equals(y.data.toString())) {
					y.flights[y.left.numberOfFlights] = flight;
					y.numberOfFlights++;
					return;
				}
			}
			y.right = node;
			y.right.flights[0] = flight;
			y.right.numberOfFlights++;
		}
	}
	
	//�lk girilen tarih ikincisinden �nceki bir tarih mi diye kontrol ediyor
	public boolean isBeforeDate(Date date1, Date date2) {
		if(date1.getYear() != date2.getYear()) {
			if(date1.getYear() < date2.getYear()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(date1.getMonth() != date2.getMonth()) {
				if(date1.getMonth() < date2.getMonth()) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(date1.getDay() < date2.getDay()) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	//�ki tarih aras�ndaki de�erleri buluyor.
	public Queue<TreeNodeDate> betweenTwoDates(Date date1, Date date2, Queue<TreeNodeDate> queue, TreeNodeDate root) {
		Queue<TreeNodeDate> elements = queue;
		if(root == null) {
			return elements;
		}
		else {
			if(isBeforeDate(root.data, date1) == false && isBeforeDate(root.data, date2) == true) {
				elements.add(root);
			}
			elements = betweenTwoDates(date1, date2, elements, root.left);
			elements = betweenTwoDates(date1, date2, elements, root.right);
		}
		return elements;
	}
	
	//�ki tarih aras�ndaki g�n say�s�n� buluyor. E�er ilk tarih daha ileri bir tarihse sonu� negatif d�n�yor.
	public int differenceBetweenDates(Date date1, Date date2) {
		int year1, month1, day1, year2, month2, day2, difference = 0;
		boolean reverseDifference;
		if(isBeforeDate(date1, date2)) {
			year1 = date1.getYear();
			month1 = date1.getMonth();
			day1 = date1.getDay();
			year2 = date2.getYear();
			month2 = date2.getMonth();
			day2 = date2.getDay();
			reverseDifference = false;
		}
		else {
			year1 = date2.getYear();
			month1 = date2.getMonth();
			day1 = date2.getDay();
			year2 = date1.getYear();
			month2 = date1.getMonth();
			day2 = date1.getDay();
			reverseDifference = true;
		}
		difference += (year2 - year1) * 365;
		difference += (month2 - month1) * 30;
		difference += day2 - day1;
		if(!reverseDifference) {
			return difference;
		}
		else {
			return -difference;
		}
	}
}
