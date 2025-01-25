package com.BankApp.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.BankApp.Model.Cust_Details_Model;
@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("hi");
		int cust_acc_no = (int) session.getAttribute("cust_acc_no");
		
		try {
			Cust_Details_Model m = new Cust_Details_Model();
			m.setCust_acc_no(cust_acc_no);
			ArrayList al = m.getStatement();
			
			if(al.isEmpty()==true) {
				response.sendRedirect("/BankApplication/StatementFail.html");
			}else {
				session.setAttribute("sal", m.sal);
				session.setAttribute("ral", m.ral);
				session.setAttribute("al", m.al);
				response.sendRedirect("/BankApplication/StatementSuccess.jsp");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}