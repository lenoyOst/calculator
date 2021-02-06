package calculator;

import java.math.BigDecimal;

public class Plus extends BinaryExpression {
 public Plus(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

 public double caculate() {return new BigDecimal(Double.toString(left.caculate())).add(new BigDecimal(Double.toString(right.caculate()))).doubleValue();}
}
