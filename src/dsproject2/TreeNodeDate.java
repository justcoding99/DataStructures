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
public class TreeNodeDate {
    
	Date data;
	Flight[] flights = new Flight[100];
	int numberOfFlights = 0;
	TreeNodeDate left;
	TreeNodeDate right;
	
	public TreeNodeDate(Date data) {
		this.data = data;
		left = null;
		right = null;
	}
}
