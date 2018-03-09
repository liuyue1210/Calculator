package arithmetic;

//排名时的用户类的定义
public class User implements Comparable<User>{
	protected String Name; //用户名
	protected int cj;      //成绩
	
	public User(String Name,int cj){  //构造方法
		this.Name=Name;
		this.cj=cj;
	}
	
	public String getName(){    //输出字符串：用户名以及成绩
		return Name+":"+cj;
	}
	
	public int compareTo(User o1){  //比较器重新定义
		if(this.cj>o1.cj)
		return -1;
	if(this.cj<o1.cj)
		return 1;
	else
		return 0;
	}
	
}
