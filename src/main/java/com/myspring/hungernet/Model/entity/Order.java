package com.myspring.hungernet.Model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")private int id;
		
		@Column(name="name")private int name;
		@Column(name="status")private int status;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getName() {
			return name;
		}
		public void setName(int name) {
			this.name = name;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
		
	
		
		
}
