package main.java.pattern.strategy;

import main.java.pattern.chainOfResponsibility.DiscountRequest;

public interface Strategy {
	DiscountRequest Execute(DiscountRequest discountRequest);
}
