package com.test.todo.item;

import lombok.Data;
/**
 * 
 * ItemDTO
 * 아이템 번호, 아이템 이름, 아이템 구매 가격(포인트), 아이템 샘플 이미지 
 * 
 * @author 4조
 *
 */
@Data
public class ItemDTO {

	private String seq;
	private String itemName;
	private String itemCost;
	private String image;
	
	private int isown;

}