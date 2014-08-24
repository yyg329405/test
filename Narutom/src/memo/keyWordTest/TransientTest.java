package memo.keyWordTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class TransientTest {
	private Date loggingDate = new Date();
	private String uid;
	private transient String pwd;

	TransientTest() {
		uid = "guest";
		pwd = "guest";
	}

	public String toString() {
		// same as above
		return "logon info: \n   " + "user: " + uid + "\n   logging date : "
				+ loggingDate.toString() + "\n   password: " + pwd;
	}
	
	public static void main(String[] args) {
		TransientTest tt = new TransientTest();
			System.out.println(tt.toString());
			try {
				ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
						"tt.out"));
				o.writeObject(tt);
				o.close();
			} catch (Exception e) {// deal with exception}
			}

				 try
				 {
				 ObjectInputStream in =new ObjectInputStream(
				 new FileInputStream("tt.out"));
				 tt = (TransientTest)in.readObject();
				  System.out.println(tt.toString());
				 }
				 catch(Exception ee) {//deal with exception}
				 }
		}
}
