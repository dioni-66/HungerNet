package com.myspring.hungernet.request;

public class CreateRestaurantRequestDTO {

	private String restaurantName;
	private int managerId;

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int id) {
		this.managerId = id;
	}

}
