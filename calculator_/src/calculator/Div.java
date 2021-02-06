package calculator;

public class Div extends BinaryExpression {
	 public Div(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	public double caculate() {return left.caculate() / right.caculate();}

}
