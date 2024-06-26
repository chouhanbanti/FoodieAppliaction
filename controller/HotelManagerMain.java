package Developer_Squad.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Developer_Squad.dao.HotelManagerCrud;
import Developer_Squad.dao.RestaurantCrud;
import Developer_Squad.dao.UserCrud;
import Developer_Squad.dto.HotelManager;
import Developer_Squad.dto.Restaurant;
import Developer_Squad.dto.User;

@WebServlet("/managerregister")
public class HotelManagerMain extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String email = req.getParameter("email");
		long phone = Long.parseLong(req.getParameter("phone"));
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmpassword");
		int restaurant_id = Integer.parseInt(req.getParameter("restaurant"));
		
		RestaurantCrud restaurantCrud = new RestaurantCrud();
		Restaurant r = restaurantCrud.fetchById(restaurant_id);
				
		PrintWriter writer = resp.getWriter();
		HotelManagerCrud hotelManagerCrud = new HotelManagerCrud();
		HotelManager hotelManager = new HotelManager(firstName, lastName, email, phone, password, confirmPassword,r);
		try {
			hotelManagerCrud.save(hotelManager);
			RequestDispatcher dis = req.getRequestDispatcher("managerlogin.jsp");
			dis.forward(req, resp);
		} catch (Exception e) {
			RequestDispatcher dis = req.getRequestDispatcher("hotelmanager.jsp");
			dis.include(req, resp);
		}
	}
}
