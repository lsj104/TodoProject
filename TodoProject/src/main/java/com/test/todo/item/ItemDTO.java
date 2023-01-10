package com.test.todo.item;

import lombok.Data;

@Data
public class ItemDTO {

	private String seq;
	private String itemName;
	private String itemCost;
	private String image;
	
	private int isown;

}