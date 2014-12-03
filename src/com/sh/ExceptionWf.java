package com.sh;

public class ExceptionWf {
	public static void sayHello() throws Exception{
		String str=null;
		//str.toString();
		throw new Exception("NullPointerFormWF");
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("sayhello_1");
			sayHello();
			System.out.println("sayhello_2");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("exception");
		} finally{
			System.out.println("aa");
		}
		System.out.println("end");
	}
}
