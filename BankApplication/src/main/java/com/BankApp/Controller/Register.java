package com.BankApp.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.BankApp.Model.Cust_Details_Model;
@WebServlet("/Register")
public class Register extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("cust_name");
		String id = request.getParameter("cust_id");
		
		String s_acc_no = request.getParameter("cust_acc_no");
		int acc_no = Integer.parseInt(s_acc_no); //Converts string to int type
		
		String pwd = request.getParameter("cust_pwd");
		String s_bal = request.getParameter("cust_bank_bal");
		int bal = Integer.parseInt(s_bal);
		
		String email = request.getParameter("cust_email");
		
		try {
			Cust_Details_Model m = new Cust_Details_Model();
			m.setCust_name(name);
			m.setCust_id(id); 
			m.setCust_acc_no(acc_no);
			m.setCust_pwd(pwd);
			m.setCust_bank_bal(bal);
			m.setCust_email(email);
			
			boolean b = m.register();
			
			if(b==true) {
				response.sendRedirect("/BankApplication/SuccessRegistration.html");
			}else {
				response.sendRedirect("/BankApplication/FailureRegistration.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}