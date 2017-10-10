package com.commsen.em.demo.calc.fancy;

import org.osgi.service.component.annotations.Component;

import com.commsen.em.annotations.Provides;
import com.commsen.em.demo.calc.api.Calculator;

import parsii.eval.Parser;
import parsii.tokenizer.ParseException;

@Provides( //
		value = "fantastic.calculator", //
		options = { //
				"add=true", //
				"substract=true", //
				"multiply=true", //
				"divide=true", //
				"power=true" //
		} //
) //
@Component
public class FancyCalculator implements Calculator {

	public Number calculate(String expression) {
		try {
			return Parser.parse(expression).evaluate();
		} catch (ParseException e) {
			return Double.NaN;
		}
	}
}