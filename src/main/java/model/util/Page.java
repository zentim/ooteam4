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
		// Assuming a total of 50, which is divisible by 5, 
		// then there are 10 pages.
		if (total % count == 0) {
			totalPage = total / count;
		} else {
			// Assuming the total number is 51 and cannot be divisible by 5, 
		    // then there are 11 pages.
			totalPage = (total / count) + 1;
		}

		if (totalPage == 0) {
			totalPage = 1;
		}

		return totalPage;
	}

	public int getLast() {
		int last;
		// Assuming the total is 50, it is divisible by 5, 
		// then the beginning of the last page is 45
		if (total % count == 0) {
			last = total - count;
		} else {
			// Assuming the total number is 51 and cannot be divisible by 5, 
		    // then the beginning of the last page is 50.
			last = total - (total % count);
		}
		last = last < 0 ? 0 : last;

		return last;
	}

	public boolean isHasPrevious() {
		return start == 0;
	}

	public boolean isHasNext() {
		return start == getLast();
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
