package main.java.pattern.chainOfResponsibility;
/**
 * chain of responsibility - handler
 */
public abstract class DiscountPolicy {
    
    public static final int BUY_X_GET_Y_FREE = 4;
    public static final int BROUGHT_MORE_THAN_IN_LAST_YEAR = 3;
    public static final int EACH_GROUP_OF_N = 2;
    public static final int PRODUCT_SET = 1;
    public static final int NO_DISCOUNT = 0;

	DiscountPolicy nextDiscountPolicy;
	
	abstract public DiscountRequest handleDiscount(DiscountRequest discountRequest);

	public DiscountPolicy getNextDiscountPolicy() {
		return nextDiscountPolicy;
	}

	public void setNextDiscountPolicy(DiscountPolicy nextDiscountPolicy) {
		this.nextDiscountPolicy = nextDiscountPolicy;
	}
	
}
