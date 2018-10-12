package main.java.pattern.chainOfResponsibility;

/**
 * Chain Of Responsibility Pattern - concrete handler
 */
public class NoDiscountPolicy extends DiscountPolicy {

	@Override
	public DiscountRequest handleDiscount(DiscountRequest discountRequest) {
	    return discountRequest;
	}

}
