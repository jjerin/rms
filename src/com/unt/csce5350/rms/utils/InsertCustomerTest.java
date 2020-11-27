package com.unt.csce5350.rms.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.unt.csce5350.rms.dao.CustomerDAO;
import com.unt.csce5350.rms.updated.model.Customer;

public class InsertCustomerTest {
	
	ArrayList<String> states = new ArrayList<String>();



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InsertCustomerTest insertCustomer = new InsertCustomerTest();
		insertCustomer.getRandomCustomer();

	}
	
	
	private void getRandomCustomer() {
		try {
			List<String> nameList = Files.readAllLines(Paths.get("C:\\Jerin\\people-names.txt"));
		    Random randomGenerator = new Random();
	        int index = randomGenerator.nextInt(nameList.size());
			System.out.println("size: "+nameList.size());
			
        	String customerFirstName = "";
        	String customerLastName = "";
        	String customerStreet1 = "";
        	String customerStreet2 = "";
        	String customerCity = "";
        	String customerState = "";
        	String customerZip = "";
        	String customerPhone = "";
        	String customerEmail = "";
        	
        	
        	DBConnectionUtil dbUtil = new DBConnectionUtil();
        	Connection connection = DBConnectionUtil.getConnection();
        	CustomerDAO customerDao = new CustomerDAO();

			
	        for(int i=0; i< 10000; i++) {
	        	int index1 = randomGenerator.nextInt(nameList.size());
	        	int index2 = randomGenerator.nextInt(nameList.size());
	        	int index3 = randomGenerator.nextInt(nameList.size());
	        	int index4 = randomGenerator.nextInt(nameList.size());

	        	customerFirstName = nameList.get(index1);
	        	customerLastName = nameList.get(index2);
	        	
	        	customerStreet1 = randomGenerator.nextInt(1000)+" "+nameList.get(index4)+" St";
	        	customerStreet2 = "Apt " + randomGenerator.nextInt(1000);
	        	
	        	customerCity = nameList.get(index3);
	        	
	        	customerState = states.get(randomGenerator.nextInt(states.size()));
	        	
	        	long phoneNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
	        	customerPhone = phoneNumber+"";

	        	long zipCode = (long) Math.floor(Math.random() * 90_000L) + 10_000L;
	        	customerZip = zipCode+"";
	        	
	        	customerEmail = customerFirstName+"."+customerLastName+"@gmail.com";
	        	
	        	int customerCurrentDiscount = randomGenerator.nextInt(3);
	        	int customerOrders = randomGenerator.nextInt(50);

	        	Customer customer = new Customer(customerCity, customerCurrentDiscount, customerEmail, customerFirstName, customerLastName, customerOrders, customerPhone, customerState, customerStreet1, customerStreet2, customerZip);

	        	//System.out.println("customer: "+customer);
	        	
	        	customerDao.insertCustomer(customer, connection);
	        	
	        	
	        	
				//System.out.println("Name: "+nameList.get(index1)+" "+nameList.get(index2));

	        }
			// System.out.println("Name: "+nameList.get(index));
			
			// System.out.println("Name: "+nameList.get(7423));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	public InsertCustomerTest() {
		states.add("AL");
		states.add("AK");
		states.add("AS");
		states.add("AZ");
		states.add("AR");
		states.add("CA");
		states.add("CO");
		states.add("CT");
		states.add("DE");
		states.add("DC");
		states.add("FM");
		states.add("FL");
		states.add("GA");
		states.add("GU");
		states.add("HI");
		states.add("ID");
		states.add("IL");
		states.add("IN");
		states.add("IA");
		states.add("KS");
		states.add("KY");
		states.add("LA");
		states.add("ME");
		states.add("MH");
		states.add("MD");
		states.add("MA");
		states.add("MI");
		states.add("MN");
		states.add("MS");
		states.add("MO");
		states.add("MT");
		states.add("NE");
		states.add("NV");
		states.add("NH");
		states.add("NJ");
		states.add("NM");
		states.add("NY");
		states.add("NC");
		states.add("ND");
		states.add("MP");
		states.add("OH");
		states.add("OK");
		states.add("OR");
		states.add("PW");
		states.add("PA");
		states.add("PR");
		states.add("RI");
		states.add("SC");
		states.add("SD");
		states.add("TN");
		states.add("TX");
		states.add("UT");
		states.add("VT");
		states.add("VI");
		states.add("VA");
		states.add("WA");
		states.add("WV");
		states.add("WI");
		states.add("WY");
	}

}
