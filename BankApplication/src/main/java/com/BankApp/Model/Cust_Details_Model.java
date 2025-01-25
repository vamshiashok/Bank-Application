package com.BankApp.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;

public class Cust_Details_Model {
	private String cust_name;
	private String cust_id;
	private int cust_acc_no;
	private String cust_pwd;
	private int cust_bank_bal;
	private String cust_email;
	private int rec_acc_no;

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	public ArrayList al = new ArrayList();
	public ArrayList sal = new ArrayList();
	public ArrayList ral = new ArrayList();
	public Cust_Details_Model() throws Exception {
		//Load the driver
		Class.forName("com.mysql.jdbc.Driver");
		//Establish the Connection
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApplicationDB", "root", "Krishna");
		System.out.println("Driver is Loaded & Established the Connection");
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public int getCust_acc_no() {
		return cust_acc_no;
	}
	public void setCust_acc_no(int cust_acc_no) {
		this.cust_acc_no = cust_acc_no;
	}
	public String getCust_pwd() {
		return cust_pwd;
	}
	public void setCust_pwd(String cust_pwd) {
		this.cust_pwd = cust_pwd;
	}
	public int getCust_bank_bal() {
		return cust_bank_bal;
	}
	public void setCust_bank_bal(int cust_bank_bal) {
		this.cust_bank_bal = cust_bank_bal;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public int getRec_acc_no() {
		return rec_acc_no;
	}
	public void setRec_acc_no(int rec_acc_no) {
		this.rec_acc_no = rec_acc_no;
	}
	public boolean register(){
		String insert_cust_details_query = "insert into sbi_cust_details values(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(insert_cust_details_query);
			pstmt.setString(1, cust_name);
			pstmt.setString(2, cust_id);
			pstmt.setInt(3, cust_acc_no);
			pstmt.setString(4, cust_pwd);
			pstmt.setInt(5, cust_bank_bal);
			pstmt.setString(6, cust_email);
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Customer Details Inserted.");
				return true;
			}
		}
		catch (SQLException e) {
			System.out.println("Error in inserting customer details"+ e.getMessage());
		}		
		return false;
	}
	public boolean login() throws SQLException {
		String validate_login_query = "select * from sbi_cust_details where cust_id=? and cust_pwd=?";
		pstmt = con.prepareStatement(validate_login_query);
		pstmt.setString(1, cust_id);
		pstmt.setString(2, cust_pwd);
		res = pstmt.executeQuery();
		while(res.next()) {
			cust_acc_no = res.getInt("cust_acc_no");
			return true;
		}
		return false;
	}
	public boolean checkBalance() throws SQLException {
		String check_bal_query = "select cust_bank_bal from sbi_cust_details where cust_acc_no=?";
		pstmt = con.prepareStatement(check_bal_query);
		pstmt.setInt(1, cust_acc_no);
		res = pstmt.executeQuery();
		while(res.next()) {
			cust_bank_bal = res.getInt("cust_bank_bal");
			return true;
		}
		return false;
	}
	public boolean changePassword() throws SQLException {
		String change_pwd_query = "update sbi_cust_details set cust_pwd=? where cust_acc_no=?";
		pstmt = con.prepareStatement(change_pwd_query);
		pstmt.setString(1, cust_pwd);
		pstmt.setInt(2, cust_acc_no);
		int x = pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}
	public boolean transferFund() throws SQLException {
		//Validating the receiver account whether it's present in the same bank or not
		String q1 = "select * from sbi_cust_details where cust_acc_no=?";
		pstmt = con.prepareStatement(q1);
		pstmt.setInt(1, rec_acc_no);
		res = pstmt.executeQuery();
		while(res.next()==true) {
			String q5 = "select * from sbi_cust_details where cust_acc_no=?";
			pstmt = con.prepareStatement(q5);
			pstmt.setInt(1, cust_acc_no);
			res = pstmt.executeQuery();
			while(res.next()==true) {
				int x = res.getInt("cust_bank_bal");
				if(x>=cust_bank_bal) {
					//if the receiver is present in the same DB, debt the transfer amount from sender
					String q2 = "update sbi_cust_details set cust_bank_bal=cust_bank_bal-? where cust_acc_no=?";
					pstmt = con.prepareStatement(q2);
					pstmt.setInt(1, cust_bank_bal); //in TranferFund Servlet we stored the transfer fund amount in cust_bank_bal variable
					pstmt.setInt(2, cust_acc_no);
					int y = pstmt.executeUpdate();
					if(y>0) {
						//check if the transfer amount exceeds the customer bank balance or not
						int rem_cust_bank_bal = res.getInt("cust_bank_bal");
						if(rem_cust_bank_bal>0) {//if transfer amount is less than the customer bank balance then proceed
							//credit that amount into the receivers account
							String q3 = "update sbi_cust_details set cust_bank_bal=cust_bank_bal+? where cust_acc_no=?";
							pstmt = con.prepareStatement(q3);
							pstmt.setInt(1, cust_bank_bal);
							pstmt.setInt(2, rec_acc_no);
							int y1 = pstmt.executeUpdate();
							if(y1>0) {
								//fetching the statement
								String q4 = "insert into statement values(?,?,?)";
								pstmt = con.prepareStatement(q4);
								pstmt.setInt(1, cust_acc_no);
								pstmt.setInt(2, rec_acc_no);
								pstmt.setInt(3, cust_bank_bal);//cust_bank_bal holds the transfered amount here
								int y2 = pstmt.executeUpdate();
								if(y2>0) {
									return true;
								}else {
									return false;
								} 
							}
						}else {
							return false;
						}
					}
				}
			}
		}
		return false;
	}
	public ArrayList getStatement() throws SQLException {
		String get_stmt_query = "select * from statement where cust_acc_no=?";
		pstmt = con.prepareStatement(get_stmt_query);
		pstmt.setInt(1, cust_acc_no);
		res = pstmt.executeQuery();
		while(res.next()==true) {
			sal.add(res.getInt("cust_acc_no"));
			ral.add(res.getInt("rec_acc_no"));
			al.add(res.getInt("tr_amt"));
		}
		return al;
	}
	public boolean applyLoan() throws SQLException {
		String q1 = "select * from sbi_cust_details where cust_acc_no=?";
		pstmt = con.prepareStatement(q1); 
		pstmt.setInt(1, cust_acc_no);
		res = pstmt.executeQuery();
		while(res.next()==true) {
			cust_name = res.getString("cust_name");
			cust_email = res.getString("cust_email");
			return true;
		}
		return false;
	}


}