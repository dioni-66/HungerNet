package com.myspring.hungernet.Model.pojo;

import java.util.List;

public class RestaurantPojo {
	
	private int id;
	private String restaurantName;
	private List<MenuPojo> menus;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<MenuPojo> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuPojo> menus) {
		this.menus = menus;
	}

}
