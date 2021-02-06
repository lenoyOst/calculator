package calculator;

public class BinaryExpression implements Expression {
	
	 Expression left;
	 Expression right;
		public BinaryExpression(Expression left, Expression right) 
		{
			super();
			this.left = left;
			this.right = right;
		}
	@Override
	public double caculate()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
