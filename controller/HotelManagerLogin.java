	package Developer_Squad.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Developer_Squad.dao.FoodItemsCrud;
import Developer_Squad.dao.HotelManagerCrud;
import Developer_Squad.dao.RestaurantCrud;
import Developer_Squad.dao.UserCrud;
import Developer_Squad.dto.FoodItems;
import Developer_Squad.dto.HotelManager;
import Developer_Squad.dto.Restaurant;
import Developer_Squad.dto.User;

@WebServlet("/managerlogin")
public class HotelManagerLogin extends HttpServlet {

	static int count;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		HotelManagerCrud hotelManagerCrud = new HotelManagerCrud();
		HotelManager manager;
		try {
			manager = hotelManagerCrud.fetchByEmail(email);
			if (manager.getPassword().equals(password)) {
				req.setAttribute("items", manager.getRestaurant());
				RequestDispatcher dis = req.getRequestDispatcher("fetchitems.jsp");
				dis.forward(req, resp);
			} else {
				count++;
				req.setAttribute("msg", "Please enter correct password");
				RequestDispatcher dis = req.getRequestDispatcher("managerlogin.jsp");
				dis.include(req, resp);
			}

		} catch (Exception e) {
			req.setAttribute("msg", "Please enter correct Email");
			RequestDispatcher dis = req.getRequestDispatcher("managerlogin.jsp");
			dis.include(req, resp);
		}
		System.out.println(count);
		if(count==3) {
			RequestDispatcher dis = req.getRequestDispatcher("forgot.jsp");
			dis.forward(req, resp);
			count=0;
		}
	}
}
