package calculator;

import java.io.CharArrayWriter;
import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.text.StyledEditorKit.BoldAction;
//
public class Caculater
{
	public static String ShuntingYard(String expression)//The ShuntingYard algorithm
	{
		//
		Character ch;
		StringBuilder temp1 = new StringBuilder();
		String longNum = new String();
		int i;
		StringBuilder temp = new StringBuilder();
		Queue<Character> Q = new LinkedList<>();
		Stack<Character> S =  new Stack<>();
		//
		for(i = 0 ; i < expression.length() ; i++)
		{
			//
			//our current char
			//
			ch = expression.charAt(i);
			//
			//needed in case of double or number that us grater then 9
			//
			temp1 = new StringBuilder().append(ch);
			if(expression.charAt(i) >= 48 && expression.charAt(i) <= 57)
			{
				//if our current number is grater then 9
				while(i+1 < expression.length() && expression.charAt(i+1) >= 48 && expression.charAt(i+1) <= 57)
				{
					i++;
					temp1.append(expression.charAt(i));
				}
				//check if our current number is a double
				if(i+1 < expression.length() && expression.charAt(i+1) == '.')
				{	
					temp1.append(".");
					i+=1;
					while(i+1 < expression.length() && expression.charAt(i+1) >= 48 && expression.charAt(i+1) <= 57)
					{
						i++;
						temp1.append(expression.charAt(i));
					} 
				}
			}
			longNum = temp1.toString();
			if(longNum.length() == 1) //operator or integer that is smaller then 10
			{
				//
				//if number
				//
				if(ch>=48 && ch <=57) 
				{
					Q.add(ch);
					Q.add(',');
				}
				//
				//if open/close
				//	
				else if(ch == 40) {S.push(ch);}
				else if(ch == 41) 
				{
					while(!S.isEmpty())
					{
						if(S.peek() !=40)
						{
						Q.add(S.pop()); 
						Q.add(',');
						}
						else break;
					}
					if(!S.isEmpty()) S.pop();
				}
				//
				// if operator
				//
				else if(ch == 42 || ch == 47){S.push(ch);}
				else  
				{
					while(!S.isEmpty() && (S.peek() == 42 || S.peek() == 47 ||S.peek() == 43 || S.peek() == 45))
					{
						Q.add(S.pop());
						Q.add(',');
					}
					S.push(ch);
				}
			}
			//
			//if the longNum length is not 1 then we have a double number or a number that is grater then 9
			//
			else
			{
				for(int j=0 ; j< longNum.length() ; j ++ )
				{
					Q.add(longNum.charAt(j));
				}
				Q.add(',');
			}
		}
		//
		i=0;
		while(!S.isEmpty())
		{
		if(S.peek() >=42 && S.peek()<=47) Q.add(S.pop());
		}
		while(!Q.isEmpty()) 
		{
			temp.insert(i, Q.poll());
			i++;
		}
		
		return temp.toString();
	}
	public static double calc(String expression) 
	{
		expression = ShuntingYard(expression);
		Stack<Double> nextQ = new Stack<Double>();
		for(int i = 0 ; i < expression.length() ; i++)
		{
			if(expression.charAt(i)>='0' && expression.charAt(i) <='9') 
			{
				StringBuilder value = new StringBuilder();
				value.append(expression.charAt(i));
				while(expression.charAt(i+1)!= ',')
				{
					i++;
					value.append(expression.charAt(i));
				}	
				nextQ.add(Double.parseDouble(value.toString()));
			}
			
			else
			{
				switch (expression.charAt(i)) {
					case '/': 
					{
						Number right = new Number(nextQ.pop());
						Number left = new Number(nextQ.pop());
						nextQ.add((new Div(left,right).caculate()));
						break;
					}
					case '-': 
					{
						Number right = new Number(nextQ.pop());
						Number left = new Number(nextQ.pop());
						nextQ.add(new Minus(left,right).caculate());
						break;
					}
					case '+':
					{
						Number right = new Number(nextQ.pop());
						Number left = new Number(nextQ.pop());
						nextQ.add(new Plus(left,right).caculate());
						break;
					}
					case '*':
					{
						Number right = new Number(nextQ.pop());
						Number left = new Number(nextQ.pop());
						nextQ.add(new Mul(left,right).caculate());
						break;
					}
				}
			}
		}
		return nextQ.pop();
	}
}
