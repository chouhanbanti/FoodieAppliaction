package Developer_Squad.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Developer_Squad.dao.CartCrud;
import Developer_Squad.dao.FoodItemsCrud;
import Developer_Squad.dao.RestaurantCrud;
import Developer_Squad.dto.Cart;
import Developer_Squad.dto.FoodItems;
import Developer_Squad.dto.Restaurant;

@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		FoodItemsCrud foodItemsCrud = new FoodItemsCrud();
		FoodItems food = foodItemsCrud.fetchById(id);
//		ArrayList al = new ArrayList<>();
//		al.add(food);
//		req.setAttribute("additem", food);
//		RequestDispatcher dis = req.getRequestDispatcher("addtocart.jsp");
//		dis.forward(req, resp);
		CartCrud cartCrud = new CartCrud();
		cartCrud.save(new Cart(food.getName(), food.getPrice(),food.getRestaurant()));
		RestaurantCrud restaurantCrud = new RestaurantCrud();
		List<Restaurant> ls = restaurantCrud.fetchAll();
		req.setAttribute("list", ls);
		RequestDispatcher dis = req.getRequestDispatcher("fetch.jsp");
		dis.forward(req, resp);
	}
}
