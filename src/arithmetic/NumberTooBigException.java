package arithmetic;

//超出范围异常类的定义
public class NumberTooBigException extends Exception{
	String message;
	public NumberTooBigException(String ErrorMessage){
		message=ErrorMessage;
	}
	public String getMessage(){
		return message;
	}
}