package main.java.pattern.strategy;

import main.java.pattern.chainOfResponsibility.DiscountRequest;

public class StrategyContext {

	Strategy strategy;
	
	public StrategyContext(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public DiscountRequest ExecuteStrategy(DiscountRequest discountRequest)
    {
        return this.strategy.Execute(discountRequest);
    }

}
