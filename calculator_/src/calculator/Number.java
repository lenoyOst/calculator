package calculator;

public class Number implements Expression {
	private double value;
	public Number(double v) {this.value = v;}
	@Override
	public double caculate() {
		return this.value;
	}

}
