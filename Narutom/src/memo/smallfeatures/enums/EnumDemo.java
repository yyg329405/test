package memo.smallfeatures.enums;

public class EnumDemo {
	public enum Gender{
		
		MAN {
			@Override
			String getType() {
				return "big";
			}
		}, WOMAN("女") {
			@Override
			String getType() {
				return "big";
			}
		},	//注意顺序(最前面)
		BABY{
			String getType(){
				return "small";
			}
		};
		private String name;
		
		private Gender(){}	//注意，枚举的构造方法必须是private，不管是有参还是无参
		private Gender(String name){
			this.name = name;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
		
		abstract String getType();		//抽象方法
	}
	
	public static void main(String[] args) {
		Gender.MAN.setName("BOY");
		System.out.println(Gender.MAN.getName()+","+Gender.MAN.getType());
		System.out.println(Gender.WOMAN.getName());
	}
	
}
