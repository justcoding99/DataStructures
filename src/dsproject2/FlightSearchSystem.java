/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproject2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author meltem koc
 */
public class FlightSearchSystem {
        
    private File file = new File("src/dsproject2/Flights.txt");
    private File tempFile = new File("src/dsproject2/FlightsTemp.txt");
    private int numberOfFlights;

    //Yeni bir u�u� bilgisi eklemek i�in
        public void addFlight(Flight flight) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(flight.toString());
        bw.newLine();
        bw.close();
        System.out.println("New flight information is added.");
    }

    //�stenilen s�radaki u�u� bilgilerini silmek i�in
    public void deleteFlight(int index) throws IOException {
        int count = 1;
        String line = "";
        clearFile(tempFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true));
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        clearFile(file);
        br.close();
        bw.close();
        BufferedReader brNew = new BufferedReader(new FileReader(tempFile));
        BufferedWriter bwNew = new BufferedWriter(new FileWriter(file, true));
        while ((line = brNew.readLine()) != null) {
            if (count == index) {
                count++;
                continue;
            }
            bwNew.write(line);
            bwNew.newLine();
            count++;
        }
        brNew.close();
        bwNew.close();
        System.out.println("The flight you have choosed is removed from system.");
    }

    //B�t�n u�u�lar� g�stermek i�in
    public void showAllFlights() throws IOException {
        int counter = 1;
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            System.out.println(counter + "-) " + line);
            counter++;
        }
        br.close();
    }

    //Dosyan�n i�indeki verileri silmek i�in
    public void clearFile(File file) throws IOException {
        BufferedWriter bwClear = new BufferedWriter(new FileWriter(file, false));
        bwClear.write("");
        bwClear.close();
    }

    //Toplam u�u� bilgisi say�s�
    public int getNumberOfFlights() throws IOException {
        return this.numberOfFlights;
    }

    //Toplamda ka� adet u�u� bilgisi oldu�unu buluyor
    public void setNumberOfFlights() throws IOException {
        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.readLine() != null) {
            count++;
        }
        this.numberOfFlights = count;
        br.close();
    }

    //Girilen de�erin tamsay� olup olmad���n� kontrol ediyor
    public void controlIntInput(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.print("You didn't enter a number. Please enter a number: ");
            scan.nextLine();
        }
    }

    //Girilen g�n de�eri do�ru mu
    public boolean isDayTrue(int year, int month, int day) {
        if (day < 0) {
            return false;
        }
        if ((year % 4) == 0 && month == 2) {
            if (day > 29) {
                return false;
            } else {
                return true;
            }
        } else {
            switch (month) {
                case 1:
                    if (day > 31) {
                        return false;
                    }
                    break;
                case 2:
                    if (day > 28) {
                        return false;
                    }
                    break;
                case 3:
                    if (day > 31) {
                        return false;
                    }
                    break;
                case 4:
                    if (day > 30) {
                        return false;
                    }
                    break;
                case 5:
                    if (day > 31) {
                        return false;
                    }
                    break;
                case 6:
                    if (day > 30) {
                        return false;
                    }
                    break;
                case 7:
                    if (day > 31) {
                        return false;
                    }
                    break;
                case 8:
                    if (day > 31) {
                        return false;
                    }
                    break;
                case 9:
                    if (day > 30) {
                        return false;
                    }
                    break;
                case 10:
                    if (day > 31) {
                        return false;
                    }
                    break;
                case 11:
                    if (day > 30) {
                        return false;
                    }
                    break;
                default:
                    if (day > 31) {
                        return false;
                    }
                    break;
            }
            return true;
        }
    }

    //Yeni u�u� kayd� eklenebilir mi
    public boolean canNewFlightAdd(Flight flight) throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            if (flight.toString().equals(line)) {
                br.close();
                return false;
            }
        }
        br.close();
        return true;
    }

    public TreeDate createTreeDate() throws IOException {
        String line = "";
        TreeDate tree = new TreeDate();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            Flight flight = getFlightFromFile(line);
            tree.insert(new TreeNodeDate(flight.getDate()), flight);
        }
        br.close();
        return tree;
    }

    public Flight getFlightFromFile(String line) {
        String elements[] = line.split("\t");
        int price = Integer.parseInt(elements[5].substring(0, elements[5].length() - 2));
        return new Flight(new Date(elements[0]), new Time(elements[1]), elements[2], elements[3], elements[4], price);
    }

    public Date inputDate() {
        boolean isYearTrue;
        boolean isMonthTrue;
        boolean isDayTrue;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a date (dd-mm-yyyy) (01-01-1900 / 31.12.2100)");
        System.out.print("Enter a year (1900 - 2100): ");
        controlIntInput(scan);
        int year = scan.nextInt();
        if (year < 1900 || year > 2100) {
            isYearTrue = false;
        } else {
            isYearTrue = true;
        }
        while (!isYearTrue) {
            System.out.print("You entered a wrong year. Please enter a year between 1900 - 2100: ");
            controlIntInput(scan);
            year = scan.nextInt();
            if (year >= 1900 && year <= 2100) {
                isYearTrue = true;
            }
        }
        System.out.print("Enter a month (1 - 12): ");
        controlIntInput(scan);
        int month = scan.nextInt();
        if (month < 0 || month > 12) {
            isMonthTrue = false;
        } else {
            isMonthTrue = true;
        }
        while (!isMonthTrue) {
            System.out.print("You entered a wrong month. Please enter a month between 1 - 12: ");
            controlIntInput(scan);
            month = scan.nextInt();
            if (month >= 1 && month <= 12) {
                isMonthTrue = true;
            }
        }
        System.out.print("Enter a day: ");
        controlIntInput(scan);
        int day = scan.nextInt();
        isDayTrue = isDayTrue(year, month, day);
        while (!isDayTrue) {
            System.out.print("You entered a wrong day. Please enter again: ");
            controlIntInput(scan);
            day = scan.nextInt();
            isDayTrue = isDayTrue(year, month, day);
        }
        return new Date(day, month, year);
    }

    public static void main(String[] args) throws IOException {
        FlightSearchSystem flightSearchSystem = new FlightSearchSystem();
        int choice;
        boolean isExitLoop = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome To Airline Flight Search System");
        do {
            System.out.println("\n1-) Add a new flight\n2-) Remove a flight\n3-) Show all flights\n"
                    + "4-) Search flights with date\n5-) Search flights with from city\n6-) Search flights with from city "
                    + "and date\n7-) Search flights between two dates\n8-) Search flights with date less than price\n"
                    + "9-) Exit the system\n");
            boolean isInputTrue;
            System.out.print("Enter the sequence number of choice you want: ");
            flightSearchSystem.controlIntInput(scan);
            choice = scan.nextInt();
            if (choice < 1 || choice > 9) {
                isInputTrue = false;
            } else {
                isInputTrue = true;
            }
            while (!isInputTrue) {
                System.out.print("You entered a wrong number. Please enter a number between 1 - 9: ");
                flightSearchSystem.controlIntInput(scan);
                choice = scan.nextInt();
                if (choice > 0 || choice <= 9) {
                    isInputTrue = true;
                }
            }

            switch (choice) {

                case 1:
                    boolean isHourTrue;
                    boolean isMinuteTrue;
                    boolean isPriceTrue;
                    boolean canNewFlightAdd;
                    Date date = flightSearchSystem.inputDate();
                    System.out.println("Enter a time (hh.mm) (00.00 - 23.59)");
                    System.out.print("Enter hour (0 - 23): ");
                    flightSearchSystem.controlIntInput(scan);
                    int hour = scan.nextInt();
                    if (hour < 0 || hour > 23) {
                        isHourTrue = false;
                    } else {
                        isHourTrue = true;
                    }
                    while (!isHourTrue) {
                        System.out.print("You entered a wrong hour. Please enter an hour between 0 - 23: ");
                        flightSearchSystem.controlIntInput(scan);
                        hour = scan.nextInt();
                        if (hour >= 0 && hour <= 23) {
                            isHourTrue = true;
                        }
                    }
                    System.out.print("Enter minute (0 - 59): ");
                    flightSearchSystem.controlIntInput(scan);
                    int minute = scan.nextInt();
                    if (minute < 0 || minute > 59) {
                        isMinuteTrue = false;
                    } else {
                        isMinuteTrue = true;
                    }
                    while (!isMinuteTrue) {
                        System.out.print("You entered a wrong minute. Please enter a minute between 0 - 59");
                        flightSearchSystem.controlIntInput(scan);
                        minute = scan.nextInt();
                        if (minute >= 0 && minute < 60) {
                            isMinuteTrue = true;
                        }
                    }
                    Time time = new Time(hour, minute);
                    System.out.print("Enter from city (ex: Ankara): ");
                    String fromCity = scan.next();
                    System.out.print("Enter to city (ex: �stanbul): ");
                    String toCity = scan.next();
                    System.out.print("Enter a carrier (ex: THY): ");
                    String carrier = scan.next();
                    System.out.print("Enter a price (minimum 0): ");
                    flightSearchSystem.controlIntInput(scan);
                    int price = scan.nextInt();
                    if (price < 0) {
                        isPriceTrue = false;
                    } else {
                        isPriceTrue = true;
                    }
                    while (!isPriceTrue) {
                        System.out.println("Price can't be negative. Please enter a price: ");
                        flightSearchSystem.controlIntInput(scan);
                        price = scan.nextInt();
                        if (price > 0) {
                            isPriceTrue = true;
                        }
                    }
                    Flight newFlight = new Flight(date, time, fromCity, toCity, carrier, price);
                    canNewFlightAdd = flightSearchSystem.canNewFlightAdd(newFlight);
                    if (canNewFlightAdd) {
                        flightSearchSystem.addFlight(newFlight);
                    } else {
                        System.out.println("The flight you want to add already exists in the system.");
                    }
                    break;

                case 2:
                    flightSearchSystem.setNumberOfFlights();
                    System.out.println();
                    System.out.println("ALL FLIGHTS");
                    System.out.println("-------------------------------------------------------------------------------");
                    flightSearchSystem.showAllFlights();
                    if (flightSearchSystem.getNumberOfFlights() > 0) {
                        if (flightSearchSystem.getNumberOfFlights() == 1) {
                            System.out.println("There is only one flight.");
                            System.out.print("Enter 1 to delete: ");
                        } else {
                            System.out.println("-------------------------------------------------------------------------------");
                            System.out.println("There are " + flightSearchSystem.getNumberOfFlights() + " flights.");
                        }
                        isInputTrue = false;
                        System.out.print("Enter the sequence number of flight you want to delete: ");
                        flightSearchSystem.controlIntInput(scan);
                        int delete = scan.nextInt();
                        if (delete < 1 || delete > flightSearchSystem.getNumberOfFlights()) {
                            isInputTrue = false;
                        } else {
                            isInputTrue = true;
                        }
                        while (!isInputTrue) {
                            System.out.print("You entered a wrong number. ");
                            if (flightSearchSystem.getNumberOfFlights() == 1) {
                                System.out.print("Enter 1 to delete: ");
                            } else {
                                System.out.print("Enter between 1 - " + flightSearchSystem.getNumberOfFlights() + ": ");
                            }
                            flightSearchSystem.controlIntInput(scan);
                            delete = scan.nextInt();
                            if (delete > 0 && delete < (flightSearchSystem.getNumberOfFlights()) + 1) {
                                isInputTrue = true;
                            }
                        }
                        flightSearchSystem.deleteFlight(delete);
                    } else {
                        System.out.println("There are no flights to remove.");
                    }
                    break;
                case 3:
                    flightSearchSystem.setNumberOfFlights();
                    if (flightSearchSystem.getNumberOfFlights() > 0) {
                        if (flightSearchSystem.getNumberOfFlights() == 1) {
                            System.out.println("There is only one flight.");
                        } else {
                            System.out.println("There are " + flightSearchSystem.getNumberOfFlights() + " flights.");
                        }
                        System.out.println();
                        System.out.println("ALL FLIGHTS");
                        System.out.println("-------------------------------------------------------------------------------");
                        flightSearchSystem.showAllFlights();
                        System.out.println("-------------------------------------------------------------------------------");
                    } else {
                        System.out.println("There are no flights to show.");
                    }
                    break;
                case 4:
                    Date dateSearch = flightSearchSystem.inputDate();
                    TreeDate tree = flightSearchSystem.createTreeDate();
                    TreeNodeDate treeNode = tree.search(dateSearch);
                    
                    if (treeNode != null) {
                        System.out.println();
                        System.out.println("ALL FLIGHTS ON " + dateSearch.toString());
                        System.out.println("-------------------------------------------------------------------------------");
                        for (int i = 0; i < treeNode.numberOfFlights; i++) {
                            System.out.println((i + 1) + "-) " + treeNode.flights[i].toString());
                        }
                        System.out.println("-------------------------------------------------------------------------------");
                        if (treeNode.numberOfFlights == 1) {
                            System.out.println("There is only one flight on " + treeNode.data.toString());
                        } else {
                            System.out.println("There are " + treeNode.numberOfFlights + " flights on " + treeNode.data.toString());
                        }
                    } else {
                        TreeNodeDate[] closest = tree.closestDates(dateSearch);
                        int count = 0;
                        System.out.println();
                        System.out.println("ALL FLIGHTS CLOSE TO " + dateSearch.toString());
                        System.out.println("-------------------------------------------------------------------------------");
                        if (closest[0] != null) {
                            for (int i = 0; i < closest[0].numberOfFlights; i++) {
                                System.out.println((i + 1) + "-) " + closest[0].flights[i].toString());
                                count++;
                            }
                        }
                        if (closest[1] != null) {
                            for (int i = 0; i < closest[1].numberOfFlights; i++) {
                                System.out.println((i + 1) + "-) " + closest[1].flights[i].toString());
                                count++;
                            }
                        }
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("There are no flights on " + dateSearch.toString());
                        if (count == 1) {
                            System.out.println("There is only one flight close to " + dateSearch.toString());
                        } else {
                            System.out.println("There are " + count + " flights close to " + dateSearch.toString());
                        }
                    }
                    break;
                case 5:
                    File file = new File("src/dsproject2/Flights.txt");
                    System.out.print("Enter a from city: ");
                    String fromCitySearch = scan.next();
                    String line = "";
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    int count = 0;
                    System.out.println();
                    System.out.println("ALL FLIGHTS FROM " + fromCitySearch.toUpperCase());
                    System.out.println("-------------------------------------------------------------------------------");
                    while ((line = br.readLine()) != null) {
                        Flight flight = flightSearchSystem.getFlightFromFile(line);
                        if (flight.getFromCity().toLowerCase().equals(fromCitySearch.toLowerCase())) {
                            System.out.println((count + 1) + "-) " + flight.toString());
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("There are no flights from " + fromCitySearch);
                    } else {
                        System.out.println("-------------------------------------------------------------------------------");
                        if (count == 1) {
                            System.out.println("There is only one flight from " + fromCitySearch);
                        } else {
                            System.out.println("There are " + count + " flights from " + fromCitySearch);
                        }
                    }
                    break;
                case 6:
                    Date dateSearch2 = flightSearchSystem.inputDate();
                    System.out.print("Enter a from city: ");
                    String fromCitySearch2 = scan.next();
                    TreeDate tree2 = flightSearchSystem.createTreeDate();
                    TreeNodeDate treeNode2 = tree2.search(dateSearch2);
                    if (treeNode2 != null) {
                        int count2 = 0;
                        System.out.println();
                        System.out.println("ALL FLIGHTS ON " + dateSearch2.toString() + " FROM " + fromCitySearch2.toUpperCase());
                        System.out.println("-------------------------------------------------------------------------------");
                        for (int i = 0; i < treeNode2.numberOfFlights; i++) {
                            if (treeNode2.flights[i].getFromCity().toLowerCase().equals(fromCitySearch2.toLowerCase())) {
                                System.out.println((count2 + 1) + "-) " + treeNode2.flights[i].toString());
                                count2++;
                            }
                        }
                        if (count2 == 0) {
                            System.out.println("There are no flights on " + dateSearch2.toString() + " from " + fromCitySearch2);
                        } else {
                            System.out.println("-------------------------------------------------------------------------------");
                            if (count2 == 1) {
                                System.out.println("There is only one flight on " + treeNode2.data.toString() + " from " + fromCitySearch2);
                            } else {
                                System.out.println("There are " + count2 + " flights on " + treeNode2.data.toString() + " from " + fromCitySearch2);
                            }
                        }
                    }
                    break;
                case 7:
                    Date dateSearch3 = flightSearchSystem.inputDate();
                    Date dateSearch4 = flightSearchSystem.inputDate();
                    TreeDate tree3 = flightSearchSystem.createTreeDate();
                    Queue<TreeNodeDate> elements = tree3.betweenTwoDates(dateSearch3, dateSearch4, new LinkedList<TreeNodeDate>(), tree3.root);
                    if (!elements.isEmpty()) {
                        int count3 = 0;
                        System.out.println();
                        System.out.println("ALL FLIGHTS BETWEEN " + dateSearch3.toString() + " - " + dateSearch4.toString());
                        System.out.println("-------------------------------------------------------------------------------");
                        while (!elements.isEmpty()) {
                            TreeNodeDate node = elements.poll();
                            for (int i = 0; i < node.numberOfFlights; i++) {
                                System.out.println((count3 + 1) + "-) " + node.flights[i].toString());
                                count3++;
                            }
                        }
                        System.out.println("-------------------------------------------------------------------------------");
                        if (count3 == 1) {
                            System.out.println("There is only one flight between " + dateSearch3.toString() + " - " + dateSearch4.toString());
                        } else {
                            System.out.println("There are " + count3 + " flights between " + dateSearch3.toString() + " - " + dateSearch4.toString());
                        }
                    } else {
                        System.out.println("There are no flights between " + dateSearch3.toString() + " - " + dateSearch4.toString());
                    }
                    break;
                case 8:
                    Date dateSearch5 = flightSearchSystem.inputDate();
                    System.out.print("Enter a price (minimum 0): ");
                    flightSearchSystem.controlIntInput(scan);
                    boolean isPriceSearchTrue;
                    int priceSearch = scan.nextInt();
                    if (priceSearch < 0) {
                        isPriceSearchTrue = false;
                    } else {
                        isPriceSearchTrue = true;
                    }
                    while (!isPriceSearchTrue) {
                        System.out.println("Price can't be negative. Please enter a price: ");
                        flightSearchSystem.controlIntInput(scan);
                        priceSearch = scan.nextInt();
                        if (priceSearch > 0) {
                            isPriceSearchTrue = true;
                        }
                    }
                    TreeDate tree4 = flightSearchSystem.createTreeDate();
                    TreeNodeDate treeNode4 = tree4.search(dateSearch5);
                    if (treeNode4 != null) {
                        int count3 = 0;
                        System.out.println();
                        System.out.println("ALL FLIGHTS ON " + dateSearch5.toString() + " CHEAPER THAN " + priceSearch + "TL");
                        System.out.println("-------------------------------------------------------------------------------");
                        for (int i = 0; i < treeNode4.numberOfFlights; i++) {
                            if (treeNode4.flights[i].getPrice() < priceSearch) {
                                System.out.println((count3 + 1) + "-) " + treeNode4.flights[i].toString());
                                count3++;
                            }
                        }
                        if (count3!=0){
                            System.out.println("-------------------------------------------------------------------------------");
                            if (count3 == 1) {
                                System.out.println("There is only one flight on " + treeNode4.data.toString() + " cheaper than " + priceSearch + "TL");
                            } else {
                                System.out.println("There are " + count3 + " flights on " + treeNode4.data.toString() + " cheaper than " + priceSearch + "TL");
                            }
                        }
                    }
                    else{
                       System.out.println("There are no flights on " + dateSearch5.toString() + " less than " + priceSearch + "TL"); 
                    }
                    break;
                case 9:
                    isExitLoop = true;
                    System.out.println("Have a good day!");
                    break;
                default:
                    break;
            }
        } while (!isExitLoop);
        scan.close();
    }
}
