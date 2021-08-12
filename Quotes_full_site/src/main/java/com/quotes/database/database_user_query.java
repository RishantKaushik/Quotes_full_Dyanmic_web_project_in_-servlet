package com.quotes.database;


import com.quotes.encapsulation.user_data;



public class database_user_query {
	private static database_user_query userDao = new database_user_query();

	private  database_user_query(){
		}

	public static  database_user_query getUserDao() {
		return userDao;
	}

	public user_data login(String email, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(user_data user) {
		// TODO Auto-generated method stub
		return 0;
	}
}
