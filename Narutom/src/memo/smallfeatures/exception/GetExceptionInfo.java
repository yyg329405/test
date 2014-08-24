package memo.smallfeatures.exception;

public class GetExceptionInfo {
	int ak ;
	
	public static void main(String[] args) {
		
		try {
			int a = 2/0;
			System.out.println(a);
		} catch (Exception e) {
			Class clazz = e.getClass();
			String className = clazz.getName();
			String simpleName = clazz.getSimpleName();
			System.out.println(className + ",,,," + simpleName);
			e.printStackTrace();
		}
	}
	
}
