package arithmetic;

import javax.swing.JTextField;

//加法类
public class Addition extends Operation{
	//带参构造函数
		public Addition(int numberOfDigit,JTextField showQuestion,JTextField showUserAnswer,JTextField showRightAnswer){
			super(numberOfDigit);
			this.showQuestion=showQuestion;
			this.showUserAnswer=showUserAnswer;
			this.showRightAnswer=showRightAnswer;
			OPERATION="+";
			range=2*(int)Math.pow(10,numberOfDigit)-2;
			setRightAnswer();
		}
		
		//父类抽象方法的具体实现：得到正确答案
		public void setRightAnswer(){
			rightAnswer=number1+number2;
			fullNumbers="题目："+number1+" + "+number2+" =  你的答案："+userAnswer+"  正确答案："+rightAnswer;
		}
		
		//父类抽象方法的具体实现：输出题目
		public void outputQuestion(int i){
			showQuestion.setText("第"+i+"题："+number1 + " + " + number2 + "= ");
		}
		
		//父类抽象方法的具体实现：输出正确答案
		public void outputAnswers(){
			showRightAnswer.setText(""+rightAnswer);
		}
}
