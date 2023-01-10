package com.test.todo.challenge;

public class ChallengeMemberDTO {
	private int seq;
	private int cseq;
	private int mseq;
	private String cnickname;
	private int ccnt;
	
	
	@Override
	public String toString() {
		return "ChallengeMemberDTO [seq=" + seq + ", cseq=" + cseq + ", mseq=" + mseq + ", cnickname=" + cnickname
				+ ", ccnt=" + ccnt + "]";
	}


	public ChallengeMemberDTO(int seq, int cseq, int mseq, String cnickname, int ccnt) {
		super();
		this.seq = seq;
		this.cseq = cseq;
		this.mseq = mseq;
		this.cnickname = cnickname;
		this.ccnt = ccnt;
	}
	
	
	
	
	
	
}
