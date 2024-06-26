package Developer_Squad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Developer_Squad.dao.CartCrud;
import Developer_Squad.dao.UserCrud;
import Developer_Squad.dto.Cart;
import Developer_Squad.dto.User;

@WebServlet("/pay")
public class Pay extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double bill = Double.parseDouble(req.getParameter("bill"));
		LoginUser loginUser = new LoginUser();
		String email = loginUser.getUser();
		UserCrud userCrud = new UserCrud();
		try {
			User user = userCrud.fetchByEmail(email);
			if(user.getWallet()>=bill) {
				user.setWallet(user.getWallet()-bill);
				userCrud.update(user);
//				CartCrud cart=  new CartCrud();
//				cart.deleteAll();
				RequestDispatcher dis = req.getRequestDispatcher("payment.jsp");
				dis.forward(req, resp);
			} 
			else {
				req.setAttribute("msg", "insufficient balance");
				RequestDispatcher dis = req.getRequestDispatcher("buy.jsp");
				dis.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("error");
		}
	}
}
