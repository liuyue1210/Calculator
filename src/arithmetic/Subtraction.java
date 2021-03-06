package arithmetic;

import javax.swing.JTextField;

//减法类
public class Subtraction extends Operation{
	
	//带参构造函数
	public Subtraction(int numberOfDigit,JTextField showQuestion,JTextField showUserAnswer,JTextField showRightAnswer){
		super(numberOfDigit);
		
		//如果减数大于被减数，减数被减数交换位子
		if(number1<number2){
			int temp=number1;
			number1=number2;
			number2=temp;
		}
		
		this.showQuestion=showQuestion;
		this.showUserAnswer=showUserAnswer;
		this.showRightAnswer=showRightAnswer;
		OPERATION="-";
		range=(int)Math.pow(10,numberOfDigit)-1;
		setRightAnswer();
	}
	
	//父类抽象方法的具体实现：得到正确答案
  public void setRightAnswer(){
		rightAnswer=number1-number2;
		fullNumbers="题目："+number1+" - "+number2+" =  你的答案："+userAnswer+"  正确答案："+rightAnswer;
	}
	
  //父类抽象方法的具体实现：输出题目
	public void outputQuestion(int i){
		showQuestion.setText("第"+i+"题："+number1 + " - " + number2 + "= ");
	}
	
	//父类抽象方法的具体实现：输出正确答案
	public void outputAnswers(){
		showRightAnswer.setText(""+rightAnswer);
	}
}
