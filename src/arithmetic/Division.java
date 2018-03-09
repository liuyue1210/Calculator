package arithmetic;

import javax.swing.JTextField;

//除法类
public class Division extends Operation{
	int a,b;//存放正确答案商和余数的两个临时量
	String temp1,temp2;//存放用户答案和正确答案的临时量
	
	//带参构造函数
	public Division(int numberOfDigit,JTextField showQuestion,JTextField showUserAnswer,JTextField showRightAnswer){
		super(numberOfDigit);
		if(number2==0){
			number2=1;//如果除数为零，则除数为改1
		}
		this.showQuestion=showQuestion;
		this.showUserAnswer=showUserAnswer;
		this.showRightAnswer=showRightAnswer;
		OPERATION="÷";
		range=(int)Math.pow(10,numberOfDigit);
		setRightAnswer();
	}
	
	//父类抽象方法的具体实现：得到正确答案
	public void setRightAnswer(){
		a=number1/number2;
		b=number1%number2;
		temp2=""+a+","+b;
		fullNumbers="题目："+number1+" ÷ "+number2+" =  你的答案："+temp1+"  正确答案："+temp2;
	}
	
	//父类抽象方法的具体实现：输出题目
	public void outputQuestion(int i){
		showQuestion.setText("第"+i+"题："+number1 + " ÷ " + number2 + "= ");
	}
	
	//父类抽象方法的具体实现：输除正确答案
	public void outputAnswers(){
		showRightAnswer.setText(""+temp2);
	}
	
	//覆盖父类的putinAnswer()方法
public void putinAnswer()throws Exception{
		showUserAnswer.setEditable(true);
		temp1=showUserAnswer.getText();//读取文本框中的内容
	}

//覆盖父类的tellTruth()方法
	public boolean tellTruth(){
		if(temp1.equals(temp2))
		  return true;
		else
		  return false;	  
	}
}