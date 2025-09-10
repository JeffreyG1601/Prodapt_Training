package com;

public class D34j1 {
	public int add(int a, int b) {
		return a+b;
	} 
	public int sub(int a,int b) {
		if(a>b) {
			return a-b;
		}
		else {
			return b-a;
		}
	}
	public int fact(int n) {
		int i,result =1;
		for(i=1;i<=n;i++) {
			result*=i;
		}
		return result;
	}
	public static void main(String[] args) {
		D34j1 d = new D34j1();
		System.out.println(d.add(100,200));
		System.out.println(d.sub(100,200));
		System.out.println(d.fact(5));
	}
}
