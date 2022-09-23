package com.myspring.hungernet.request;

public class CreateMenuRequestDTO {
		private String type;
		private int restaurantId;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getRestaurantId() {
			return restaurantId;
		}
		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}
		
		
}
