package arithmetic;

import java.util.Random;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//主类的定义
public class Application {
	
	JFrame frm1,frm2,frm4;//定义4个窗口
	JTextField Questions[];//定义输出题目的文本框为数组
	JTextField Uanswer[],Ranswer[];//定义输入答案和输出答案的文本框为数组
	JTextField userName,Type,Num,Score,mistake,time,usertime;//定义输入用户名，运算类型，运算位数，输出成绩，错误信息和时间的文本框
	JTextArea help,rank;//用来显示程序用法说明,成绩排名
	JButton start,handup,again,docontinue,look,findRank;//定义开始，提交，重做，继续做,查看题目和排名的按钮
	JLabel a[];//显示用户输入的题目是第几题
	JPanel jp1,jp2,jp4;//用来存放上述组件的面板
	Time t=null;//倒计时线程
	ArrayList<User> users = new ArrayList<User>();//存放多个用户的列表
	Operation questions[]=new Operation[10];//实例化10道题目对象 
	String s="*运行界面出现后，会有三个窗口的跳出，输入姓名，运算位数，运算类型以及你想限定的时间；\n"+
			 "*点击开始答题，在输入答案的窗口输入答案；\n"+
			 "*答题时间以分钟为单位设定，默认为1分钟；\n"+
			 "*若在规定的时间内没有完成题目，则将无法继续答题，然后只能点击提交；\n"+
			 "*点击提交，则显示正确答案和成绩，并保留数据在文件中；\n"+
			 "*点击重做一次，再点击开始答题，则可以重新答现在显示的10道题目；\n"+
			 "*若想继续做新的练习，点击继续答题后，再点击开始答题即可，其余步骤与上述相同；\n"+
			 "*点击查看，可以打开保存数据的文件夹；\n"+
			 "*点击排名，可以显示当前所有用户的成绩排名；\n"+
			 "*运算类型为+，-，X，/，R；\n"+
			 "*遇到除法时，先输入商，再输入英文状态下的逗号，再输入余数；\n";
	
	//主类的构造方法
	public Application(){
		frm1=new JFrame("小学生四则运算练习器");                 //实例化窗口
		frm1.setSize(600,350);                                  //设置窗口的大小
		frm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置close时的默认操作
		frm1.setLocation(0, 0);                                 //设置窗口显示在电脑屏幕上的位子
		frm2=new JFrame("练习题和正确答案");
		frm2.setSize(400,300);
		frm2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm2.setLocation(0, 400);
		//frm3=new JFrame("输入答案");
		//frm3.setSize(400,300);
		//frm3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frm3.setLocation(500, 400);
		frm4=new JFrame("排名");
		frm4.setSize(400, 300);
		frm4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm4.setLocation(600, 400);
		Questions=new JTextField[10];//实例化对象
		Uanswer=new JTextField[10];
		Ranswer=new JTextField[10];
		a=new JLabel[10];
		userName=new JTextField("柳月",10);//实例化对象并设置刚开始时的默认值
		Type=new JTextField("-",10);
		Num=new JTextField("1",10);
		Score=new JTextField(30);
		mistake=new JTextField("错误信息：",50);
		usertime=new JTextField("1",15);
		time=new JTextField(20);
		rank=new JTextArea("当前排名为：\n");
		jp1=new JPanel();
		jp2=new JPanel();
		//jp3=new JPanel();
		jp4=new JPanel();
		jp1.setLayout(new FlowLayout());//面板1采用流式布局
		jp2.setLayout(new GridLayout(10,4));//面板2采用网格布局，10行，2列
		//jp3.setLayout(new GridLayout(10,2));//面板3采用网格布局，10行，2列
		jp1.add(new JLabel("用户名："));//按次序将下面几个标签添加到jp1面板
		jp4.setLayout(new FlowLayout());
		jp4.add(rank);
		jp1.add(userName);
		jp1.add(new JLabel("选择类型： "));
		jp1.add(Type);
		jp1.add(new JLabel("运算位数："));
		jp1.add(Num);
		jp1.add(new JLabel("规定时间："));
		jp1.add(usertime);
		jp1.add(new JLabel("剩余时间："));
		jp1.add(time);
		jp1.add(new JLabel("成绩："));
		jp1.add(Score);
		jp1.add(mistake);
		
		for(int i=0;i<10;i++){                        //依次将显示题目，显示正确答案的文本框添加到面板2
			Questions[i]=new JTextField("0",20);      //此次将显示题号和输入答案的组件添加到面板3
			jp2.add(Questions[i]);                   
		    //Ranswer[i]=new JTextField(10);
			//jp2.add(Ranswer[i]);
			Uanswer[i]=new JTextField(10);
			//a[i]=new JLabel("第"+i+"题正解：");
			//jp2.add(a[i]);
			jp2.add(Uanswer[i]);
			Uanswer[i].addKeyListener(new KeyAdapter(){//监听输入答案的文本框是否有点击enter的事件发生
				public void keyReleased(KeyEvent e){
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						e.consume();//如果检测到输入了Enter键
						KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();//光标移动到下一个文本框
					}
				}
			});
			a[i]=new JLabel("第"+i+"题正解：");
			jp2.add(a[i]);
			Ranswer[i]=new JTextField(10);
			jp2.add(Ranswer[i]);
		}
		start=new JButton("开始做题");
		handup=new JButton("提交");
		again=new JButton("重做一次");
		docontinue=new JButton("继续练习");
		look=new JButton("查看");
		findRank=new JButton("排名");
		help=new JTextArea(s);
		help.setEditable(false);//帮助信息的文本框定义为不可编辑
		jp1.add(start);//将下列按钮按顺序加入到面板1
		jp1.add(handup);
		jp1.add(again);
		jp1.add(docontinue);
		jp1.add(look);
		jp1.add(findRank);
		jp1.add(help);
		start.addActionListener(new MyActionListener1());//按钮的监听器
		handup.addActionListener(new MyActionListener2());
		again.addActionListener(new MyActionListener3());
		docontinue.addActionListener(new MyActionListener4());
		look.addActionListener(new MyActionListener5());
		findRank.addActionListener(new MyActionListener6());
		frm1.add(jp1);//将面板1加到窗口1
		frm2.add(jp2);//将面板2加到窗口2
		//frm3.add(jp3);//将面板3加到窗口3
		frm4.add(jp4);//将面板4加到窗口4
		frm2.pack();//窗口2,3自动调节大小
		//frm3.pack();
		frm1.setVisible(true);//窗口1,2,3都可见
		frm2.setVisible(true);
		//frm3.setVisible(true);
		frm4.setVisible(false);//窗口四不可见
	}
	
	//线程类(内部类)的定义
	class Time extends Thread{
		public void run(){
			int min =Integer.parseInt(usertime.getText())-1;
			int second;
			for(second=59;second>=0;second--)
			{
				if(second<10){
					time.setText("0"+min+":"+"0"+second);
				}
				else
				    time.setText("0"+min+":"+second);
				try{
					sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				if(min==0&&second==0){
					break;
				}
				if(second==0){
					second=60;
					min-=1;
				}
			}
			for(int i=0;i<10;i++){
				Uanswer[i].setEditable(false);
			}
		}
	}
	
	//定义监听器1（按钮开始做题）
	class MyActionListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
	        if(e.getSource()==start){                                  //如果点击了开始做题按钮
	        	int num=Integer.parseInt(Num.getText());               //读入运算位数
		        int type=0;
	        
		        //实例化随机数发生器
	            Random numberGenerator=new Random(new Date().getTime());
	    
	            for(int i=0;i<10;i++){
	                //判断运算类型
    	            if(Type.getText().equals("+"))
    		            type=0;
    	            else if(Type.getText().equals("-"))
    		            type=1;
    	            else if(Type.getText().equals("X"))
    		            type=2;
    	            else if(Type.getText().equals("/"))
    		            type=3;
    	            else if(Type.getText().equals("R"))
    		            type=numberGenerator.nextInt(4);
    	            else{
    	        	    Type.setText("运算类型为+，-，X，/，R之一");
    		            return;
    	            }
    	            
    	            //显示题目
    	            switch(type){
    	            case 0:
    		            questions[i]=new Addition(num,Questions[i],Uanswer[i],Ranswer[i]);
    		            questions[i].outputQuestion(i);
    		            break;
    	            case 1:
    		            questions[i]=new Subtraction(num,Questions[i],Uanswer[i],Ranswer[i]);
    		            questions[i].outputQuestion(i);
    		            break;
    	            case 2:
    		            questions[i]=new Multiplication(num,Questions[i],Uanswer[i],Ranswer[i]);
    		            questions[i].outputQuestion(i);
    		            break;
    	            case 3:
    	        	    questions[i]=new Division(num,Questions[i],Uanswer[i],Ranswer[i]);
    		            questions[i].outputQuestion(i);
    		            break;
    	            }
	            }
	            questions[0].showUserAnswer.requestFocus();//显示完所有的题目，光标移到输入答案的第一个文本框
	            if (t != null)  
                t.stop();  
              t = new Time();  
              t.start(); 
	        }
	    } 
	}
	
	//定义监听器2（按钮提交）
	class MyActionListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int score=0;                            //用于存放用户的得分
			String name,timeused,time1;             //用于存放用户的姓名和做题所花的时间
		    name=userName.getText();                //从用户姓名的文本框中读取用户姓名
		    timeused=time.getText();
		    time1=usertime.getText();
		    Date date=new Date();                   //生成当前做题时间
	        long time=date.getTime();
	        
			if(e.getSource()==handup){              //如果点击了提交按钮
				for(int i=0;i<10;i++){
	  	            try {
						questions[i].putinAnswer(); //错误异常输出在错误信息文本框
					} catch (NumberFormatException e1) {
						mistake.setText("输入数据类型错误！你必须输入数值数据！");
					} catch (NumberTooBigException e1) {
						mistake.setText("答案应小于"+questions[i].range);
					} catch (Exception e1) {
						mistake.setText("错误信息：");
					}
	  	        	questions[i].setRightAnswer();//设置每题的答案
	  	        	questions[i].outputAnswers();//输出每题的正确答案
	  	        	if(questions[i].tellTruth()){//判断正确性，计算用户得分
	  	        		score+=10;
	  	        	}
	  	        }
	  	        Score.setText(""+score);//输出得分
	  	        User u=new User(name,score);
	  	        users.add(u);
	  	        
	  	        //生成目录
	  		  	String fileName1="C:\\"+name;
	  		  	File file1 = new File(fileName1);
	  		  	if(!file1.exists()){
	  		  	    file1.mkdirs();
	  		  	}
	  		  	    	    
	  		  	//生成text
	  		  	String fileName2="C:\\"+name+"\\cj"+time+".his.txt";
	  		  	File file = new File(fileName2);
	  		  	if(!file.exists()){
	  		  	    try{
	  					file.createNewFile();
	  				}catch(IOException e1) {
	  					e1.printStackTrace();
	  				}
	  		  	}
	  		  	    	    
	  		  	try{
	  		  	    //在生成文件中写入
	  		  	  	FileWriter fw = new FileWriter(fileName2, true);	
	  		  	    PrintWriter pw = new PrintWriter(fw);
	  		  	  	for(int i=0;i<10;i++){
	  		  	        pw.println(questions[i].fullNumbers);  
	  		  			pw.flush();
	  		  	    }
	  		  	  		        
	  		  	  	pw.println();
	  		  	  	pw.flush();
	  		  		pw.println("你的成绩是："+score);
	  		  		pw.flush();
	  		  	    pw.println("你的规定用时为："+time1+"分钟");
		  		    pw.flush();
		  		    pw.println("你的剩余时间是："+timeused);
		  		    pw.flush();        
	  		  	    pw.close();
	  		  	}
	  		  	catch(IOException e2){
	  		  	   e2.printStackTrace();
	  		  	}
	        }
		}
	}
	
	//定义监听器3（按钮重做一次）
  class MyActionListener3 implements ActionListener{
  	public void actionPerformed(ActionEvent e){
  		if(e.getSource()==again){//如果点击了重做按钮
  			for(int i=0;i<10;i++){                               //清空两个答案的所有文本框
  				questions[i].showUserAnswer.setText(null);
  				questions[i].showRightAnswer.setText(null);
  				questions[i].showUserAnswer.setEditable(true);
  			}
  			Score.setText(null);
  			questions[0].showUserAnswer.requestFocus();          //把光标移到第一个输入答案的文本框
  		}
  	}
	}
  
  //定义监听器4（按钮继续练习）
  class MyActionListener4 implements ActionListener{
  	public void actionPerformed(ActionEvent e){
  		if(e.getSource()==docontinue){                           //如果点击了继续练习的按钮
  			for(int i=0;i<10;i++){                               //清空所有文本框
  				questions[i].showQuestion.setText(null);
  				questions[i].showRightAnswer.setText(null);
  				questions[i].showUserAnswer.setText(null);
  				questions[i].showUserAnswer.setEditable(true);
  			}
  			Score.setText(null);
  			questions[0].showUserAnswer.requestFocus();          //把光标移到第一个输入答案的文本框
  		}
  	}
	}
  
  //定义监听器5（按钮查看）
  class MyActionListener5 implements ActionListener{
  	public void actionPerformed(ActionEvent e){
  		String name;
		    name=userName.getText();
		    String commandText="cmd /c start C:/"+name;
		    try{
		    	Runtime.getRuntime().exec(commandText);//利用cmd去打开文件所在的文件夹
		    }catch (IOException e1){
		    	e1.printStackTrace();
		    }
  	}
  }
  
  //定义监听器6（排名按钮）
  class MyActionListener6 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Collections.sort(users);
			for(int i=0;i<users.size();i++){
				rank.append((String)users.get(i).getName());
				rank.append("\n");
			}
			frm4.setVisible(true);
		}
  }
	
  public static void main(String args[]){
		new Application();
	}
}
