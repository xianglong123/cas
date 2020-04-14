package com.cas.zother;

import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * 枚举 + 拉姆达表达式 实现策略模式
 */
public class Test {
 
	private Operate operate; 
	
	@org.junit.Test
	public void test() {
		setOperate(OperateImpl.ADD);
		Optional.ofNullable(operate.operate(1, 2))
				  .ifPresent(System.out :: println);
		
		setOperate(OperateImpl.SUB);
		Optional.ofNullable(operate.operate(1, 2))
				  .ifPresent(System.out :: println);
	}
	
	public interface Operate{
		float operate(float x, float y);
	}
 
	public enum OperateImpl implements Operate{
		ADD((x,y)->{return x+y;}),
		SUB((x,y)->{return x-y;});
		
		private BinaryOperator<Float> operator;
		
		OperateImpl(BinaryOperator<Float>  operator) {
			this.operator = operator;
		}
		
		@Override
		public float operate(float x, float y) {
			return this.operator.apply(x, y);
		}
	}
 
	public Operate getOperate() {
		return operate;
	}
 
	public void setOperate(Operate operate) {
		this.operate = operate;
	}
	
}