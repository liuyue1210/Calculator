package arithmetic;

import javax.swing.JTextField;

//抽象类Operation的定义
public abstract class Operation {
	protected int number1,number2;//定义被运算的两个数字
	protected int numberOfDigit;//定义运算的位数
	protected int rightAnswer,userAnswer;//定义正确答案和用户答案
	protected int range;//定义运算的结果范围
	protected String fullNumbers;//定义整个题目，正确答案，用户答案；
	protected String OPERATION; //定义符号
	protected JTextField showQuestion;//定义输出题目的文本框
	protected JTextField showUserAnswer;//定义显示用户答案的文本框
	protected JTextField showRightAnswer;//定义显示正确答案的文本框
	
	//带参构造函数
	public Operation(int numberOfDigit){
		this.numberOfDigit=numberOfDigit;
		number1=(int)(Math.random()*(int)Math.pow(10,numberOfDigit));//随机生成两个数
		number2=(int)(Math.random()*(int)Math.pow(10,numberOfDigit));
		setRightAnswer();//得到正确答案
	}
	
	//输入答案
	public void putinAnswer()throws NumberTooBigException,NumberFormatException,Exception{
		showUserAnswer.setEditable(true);
		userAnswer=Integer.parseInt(showUserAnswer.getText());//读取文本框中的内容并转化为int类型
		//如果输入的答案超出范围，则抛出异常
		if(userAnswer>range){
			throw new NumberTooBigException("你输入的答案可能超出了范围！");
		}
	}
	
	public abstract void setRightAnswer();//计算得到正确答案
	public abstract void outputQuestion(int i);//输出题目
	public abstract void outputAnswers();//输出正确答案
	
	//判断题目是否做对
	public boolean tellTruth(){
		if(userAnswer==rightAnswer)
		  return true;
		else
		  return false;	  
	}
}
