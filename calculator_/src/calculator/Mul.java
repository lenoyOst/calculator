package calculator;

public class Mul extends BinaryExpression {
	 public Mul(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	public double caculate()
	 {
		 return left.caculate() * right.caculate();
	 }
}
