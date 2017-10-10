package com.commsen.em.demo.rest.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.commsen.em.annotations.Requires;

@Requires (
		value="fantastic.calculator", 
		filter="(&(em.contract=fantastic.calculator)(power=true))"
		)
@Retention(RetentionPolicy.CLASS)
public @interface RequiresPowerCalculator {

}
