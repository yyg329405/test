package com.other;

public class RunObject {
	int t = 6;
	Object obj = new Object();
	StringBuilder sb = new StringBuilder("asd");

	public static void main(String[] args) {
		RunObject a = new RunObject();
		a.func(a.t, a.obj, a.sb);			// 问t和obj的值是否变化，答案：不变化
		System.out.println(a.t);
		System.out.println(a.obj);
		System.out.println(a.sb);
		
//		RunObject a2 = new RunObject();
//		a2.func2(a2);			// 问t和obj的值是否变化，答案：不变化
//		RunObject a = new RunObject();
//		System.out.println(a.t);
//		System.out.println(a.obj);
	}

	public void func(int t, Object obj,StringBuilder sb) {
		t = 7;
		obj = null;
		sb.append("xx");
	}
	public void func2(RunObject rr) {
		rr.t = 7;
		rr.obj = null;
	}
}