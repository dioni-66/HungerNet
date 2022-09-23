package com.myspring.hungernet.request;

public class UpdateRestaurantRequestDTO {
		private int id;
		private String restaurantName;

		public String getRestaurantName() {
			return restaurantName;
		}

		public void setRestaurantName(String name) {
			this.restaurantName = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		
}
