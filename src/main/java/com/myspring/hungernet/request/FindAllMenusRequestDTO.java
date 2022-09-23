package com.myspring.hungernet.request;

public class FindAllMenusRequestDTO {
		private int restaurantId;
		private int menuId;
		
		public int getRestaurantId() {
			return restaurantId;
		}
		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}
		public int getMenuId() {
			return menuId;
		}
		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}
		
}
