package com.fiesta.util;

public class RandomPassword {
	public static String randomPassword(int length){

		int index = 0;

		char[] charset = new char[] {
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M'
				,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','j','k','l','m'
				,'n','o','p','q','r','s','t','u','v','w','x','y','z'};

		// 소문자 변환은 블럭 지정후 ctrl + shift + y
		// 대문자 변환은 블럭 지정후 ctrl + shift + x

		StringBuffer sb = new StringBuffer();

		for(int i = 0 ; i<length ; i++){
			index = (int) (charset.length * Math.random());
			sb.append(charset[index]);
		}

		return sb.toString();

	}

	public static void main(String[] args){
		String password = randomPassword(10);
		System.out.println("임시 비밀번호 : " + password);
	}
}
