package com.test.todo.todolist;



import lombok.Data;

@Data
public class CalDTO {
	private int seq;
	private String content;
	private String startdate;
	private String endd;
	private int mesq;
	@Override
	public String toString() {
		return "CalDTO [seq=" + seq + ", content=" + content + ", startdate=" + startdate + ", endd=" + endd + ", mesq="
				+ mesq + "]";
	}
	public CalDTO(int seq, String content, String startdate, String endd, int mesq) {
		super();
		this.seq = seq;
		this.content = content;
		this.startdate = startdate;
		this.endd = endd;
		this.mesq = mesq;
	}
	
	
}
