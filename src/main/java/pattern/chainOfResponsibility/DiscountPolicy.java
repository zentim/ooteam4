package main.java.pattern.chainOfResponsibility;
/**
 * chain of responsibility - handler
 */
public abstract class DiscountPolicy {

	DiscountPolicy nextDiscountPolicy;
	
	abstract public DiscountRequest handleDiscount(DiscountRequest discountRequest);

	public DiscountPolicy getNextDiscountPolicy() {
		return nextDiscountPolicy;
	}

	public void setNextDiscountPolicy(DiscountPolicy nextDiscountPolicy) {
		this.nextDiscountPolicy = nextDiscountPolicy;
	}
	
}
