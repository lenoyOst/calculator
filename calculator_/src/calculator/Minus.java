package calculator;

import java.math.BigDecimal;
import java.util.function.DoubleToLongFunction;

public class Minus extends BinaryExpression {
public Minus(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

public double caculate() {return new BigDecimal(Double.toString(left.caculate())).subtract(new BigDecimal(Double.toString(right.caculate()))).doubleValue();}
}

