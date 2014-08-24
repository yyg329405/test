package com.other.synchronize;

public class SynBlock {
	public static void main(String[] args) {
		
		//sysnchrosized block
		for(int i=0; i<100; i++){
			Integer ind = SynBlock.getRandomNum(3);
			System.out.println("=="+ind);
			synchronized (ind) {
				System.out.println(">>"+ind);
			}
		}
	}
	
	
	static int getRandomNum(int maxRegion){
		double rd = Math.random();
		int result = (int) Math.rint(rd*maxRegion);
		return result;
	}
	
}
