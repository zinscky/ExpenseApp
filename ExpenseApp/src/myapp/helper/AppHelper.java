package myapp.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTextArea;

import myapp.constants.Constants;

//import myapp.users.User;

public class AppHelper {
	//private User user;
	
	public AppHelper() {
		
	}
	
	private String getMonth(int month) {
		return Constants.MONTHS[month];
	}
	
	public boolean addRecord(String userName, String itemName, String itemCost, JTextArea console) {
		Date date = new Date();
		String myDate;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		String fileName = getMonth(cal.get(Calendar.MONTH)) + "-" + cal.get(Calendar.YEAR) + ".txt";
		myDate = cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.YEAR);
		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("src\\myapp\\data\\" + fileName, true)));
			String record = String.format("%-15s %-20s %-10s %-30s", userName, itemName, itemCost, myDate);
			out.println(record);
			out.close();
			return true;
			
		} catch (IOException e) {
			console.append(e.getMessage());
		}
		
		return false;
	}
	
	public boolean search(String month, String year, JTextArea console) {
		BufferedReader reader = getFileReader(getFileName(month, year), console);
		String record;
		String[] tokens;
		String name, item;
		String cost;
		if(reader != null) {
			try {
				String temp;
				temp = String.format("%-20s %-10s %-15s\n", "Item", "Cost", "Person");
				console.append(temp);
				console.append("----------------------------------------------------------\n");
				while((record = reader.readLine()) != null) {
					tokens = record.split("\\s\\s+");
					name = tokens[0];
					item = tokens[1];
					cost = tokens[2];
					temp = String.format("%-20s %-10s %-15s\n", item, cost, name);
					console.append(temp);
				}
				reader.close();
				console.append("----------------------------------------------------------\n");
				console.append("End of operation...\n");
				return true;
			} catch (IOException e) {
				console.append(e.getMessage() + "\n");
			}
		}
		return false;
	}
	
	public void calculate(String month, String year, JTextArea console) {
		
		BufferedReader reader = getFileReader(getFileName(month, year), console);
		String record;
		String[] tokens;
		String name, item, temp;
		ArrayList<String> users = new ArrayList<String>();
		users = getUsers();
		ArrayList<Integer> userCosts = new ArrayList<Integer>();
		//Initialize userCosts with 0s and size of the number of users.
		for(int i = 0; i < users.size(); i++) {
			userCosts.add(0);
		}
		int cost;
		if(reader != null) {
			try {
				temp = String.format("%-20s %-10s %-15s\n", "Item", "Cost", "Person");
				console.append(temp);
				console.append("----------------------------------------------------------\n");
				
				//Read all records in the file
				while((record = reader.readLine()) != null) {
					tokens = record.split("\\s\\s+");
					name = tokens[0];
					name = name.toLowerCase();
					item = tokens[1];
					cost = Integer.parseInt(tokens[2]);
					//compare the users and add costs to appropriate users
					for(int i = 0; i < users.size(); i++) {
						if(users.get(i).equals(name)) {
							userCosts.set(i, userCosts.get(i) + cost);
						}
					}
					temp = String.format("%-20s %-10s %-15s\n", item, cost, name);
					console.append(temp);
				}
					
				reader.close();
				console.append("----------------------------------------------------------\n");
				
				int total = 0;
				for(int i = 0; i < users.size(); i ++) {
					total += userCosts.get(i);
					console.append(users.get(i) + " - " + userCosts.get(i) + "\n");
				}
				console.append("Total - " + total + "\n");
				console.append("End of operation...\n");
				splitCosts(total, users, userCosts, console);
			} catch (IOException e) {
				console.append(e.getMessage() + "\n");
			}
		}
	}
	
	private void splitCosts(int total, ArrayList<String> users, ArrayList<Integer> userCosts, JTextArea console) {
		int perHead = total / users.size();
		console.append("Per Head = " + perHead + "\n");
		
		ArrayList<Integer> userPay = new ArrayList<Integer>();
		int[][] payment = new int[users.size()][users.size()]; //stores the payment matrix about who owes who
		
		for(int i = 0; i < users.size(); i++) 
			userPay.add(0);
		for(int i = 0; i < users.size(); i++) {
			userPay.set(i, perHead - userCosts.get(i));
		}
		for(int i = 0; i < users.size(); i++) {
			for(int j = 0; j < users.size(); j++) {
				if(i != j) {
					if(userPay.get(i) > 0 && userPay.get(j) < 0) {
						if(userPay.get(i) > Math.abs(userPay.get(j))) {
							payment[i][j] = Math.abs(userPay.get(j));
							userPay.set(i, userPay.get(i) + userPay.get(j));
						}
						else {
							
							payment[i][j] = userPay.get(i);
							userPay.set(j, userPay.get(i) + userPay.get(j));
							userPay.set(i, 0);
							
						}
					}
				}
			}
		}
		for(int i = 0; i < users.size(); i++) {
			for(int j = 0; j < users.size(); j++) {
				if(payment[i][j] > 0) {
					String ans = users.get(i) + " to " + users.get(j) + " --> " + payment[i][j] + "\n";
					console.append(ans);
				}
			}
		}
		finalizePayments(payment, users, console);
		
	}
	
	private void finalizePayments(int[][] payment, ArrayList<String> users, JTextArea console) {
		int perHead = Constants.TOTAL_FIXED / users.size();
		int[] finalPayment = new int[users.size()];
		
		for(int i = 0; i < users.size(); i++) {
			for(int j = 0; j < users.size(); j++) {
				if(payment[i][j] > 0) {
					finalPayment[i] += payment[i][j];
					finalPayment[j] -= payment[i][j];
					
				}
			}
		}
		console.append("Total Fixed Expenses = " + Constants.TOTAL_FIXED + "\n");
		console.append("Per Head = " + perHead + "\n\n");
		console.append("FINAL PAYMENTS\n");
		console.append("----------------------------------------------------------\n");
		for(int i = 0; i <users.size(); i++) {
			String result = users.get(i) + " --> " + (finalPayment[i] + perHead);
			console.append(result + "\n");
		}
		console.append("----------------------------------------------------------\n");
	}
	
	public void deleteFile(String month, String year, JTextArea console) {
		File file = new File(getFileName(month, year));
		
		if(file.delete()) 
			console.append("File deleted: \"" + getFileName(month, year) + "\"\n");
		else
			console.append("Specified file: \"" + getFileName(month, year) + "\" doesnt exist.\n");
	}
	
	private ArrayList<String> getUsers() {
		ArrayList<String> users = new ArrayList<String>();
		users.add("dip");
		users.add("biki");
		users.add("vicky");
		return users;
	}
	
	private BufferedReader getFileReader(String fileName, JTextArea console) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			return reader;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			console.append(e.getMessage() + "\n");
		}
		return null;
	}
	
	private String getFileName(String month, String year) {
		return Constants.DATA_PATH + month + "-" + year + ".txt"; 
	}
}
