package memo.smallfeatures.enums;

public class EnumDemo {
	public enum Gender{
		
		MAN {
			@Override
			String getType() {
				return "big";
			}
		}, WOMAN("Ů") {
			@Override
			String getType() {
				return "big";
			}
		},	//ע��˳��(��ǰ��)
		BABY{
			String getType(){
				return "small";
			}
		};
		private String name;
		
		private Gender(){}	//ע�⣬ö�ٵĹ��췽��������private���������вλ����޲�
		private Gender(String name){
			this.name = name;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
		
		abstract String getType();		//���󷽷�
	}
	
	public static void main(String[] args) {
		Gender.MAN.setName("BOY");
		System.out.println(Gender.MAN.getName()+","+Gender.MAN.getType());
		System.out.println(Gender.WOMAN.getName());
	}
	
}
