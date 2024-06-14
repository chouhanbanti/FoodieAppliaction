package Developer_Squad.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Developer_Squad.dao.FoodItemsCrud;
import Developer_Squad.dao.RestaurantCrud;
import Developer_Squad.dto.FoodItems;
import Developer_Squad.dto.Restaurant;

@WebServlet("/searchrestaurant")
public class SearchItems3 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchitem = req.getParameter("search");
		RestaurantCrud restaurantCrud = new RestaurantCrud();
		List<Restaurant> rlist = restaurantCrud.fetchAll();
		for (Restaurant restaurant : rlist) {
			if (restaurant.getName().equalsIgnoreCase(searchitem)) {
				FoodItemsCrud foodItemsCrud = new FoodItemsCrud();
				List<FoodItems> flist = foodItemsCrud.fetch(restaurant.getId());
				req.setAttribute("foodlist", flist);
				RequestDispatcher dis = req.getRequestDispatcher("searchrestaurant.jsp");
				dis.forward(req, resp);
			}
		}
		FoodItemsCrud foodItemsCrud = new FoodItemsCrud();
		List<FoodItems> flist = foodItemsCrud.fetchAll();
		ArrayList<FoodItems> al = new ArrayList<>();
		int count = 0;
		for (FoodItems item : flist) {
			if (searchitem.equalsIgnoreCase(item.getName())) {
				al.add(item);
				count++;
				System.out.println("hello");
			}
		}
		if (count > 0) {
			req.setAttribute("foodlist", al);
			RequestDispatcher dis = req.getRequestDispatcher("searchfooditem.jsp");
			dis.forward(req, resp);
		}

	}

}
