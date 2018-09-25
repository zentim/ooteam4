package main.java.model.util;

public class Page {

	int start;
	int count;
	int total;
	String param;
	
	public Page(int start, int count) {
		super();
		this.start = start;
		this.count = count;
	}
	
	public int getTotalPage() {
		int totalPage;
		// 安砞羆计琌 50琌镑砆 5 俱埃ê或碞Τ 10 
		if (total % count == 0) {
			totalPage = total / count;
		} else {
			// 安砞羆计琌 51ぃ镑砆 5 俱埃ê或碞Τ 11 
			totalPage = (total / count) + 1;
		}
		
		if (totalPage == 0) {
			totalPage = 1;
		}
		
		return totalPage;
	}
	
	public int getLast() {
		int last;
		// 安砞羆计琌 50琌镑砆 5 俱埃ê或程秨﹍碞琌 45
		if (total % count == 0) {
			last = total - count;
		} else {
			// 安砞羆计琌 51ぃ镑砆 5 俱埃ê或程秨﹍碞琌 50
			last = total - (total % count);
		}
		last = last < 0 ? 0 : last;
		
		return last; 
	}
	
	public boolean isHasPrevious() {
		if (start == 0) {
			return false;
		}
		return true;
	}
	
	public boolean isHasNext() {
		if (start == getLast()) {
			return false;
		}
		return true;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	

}
