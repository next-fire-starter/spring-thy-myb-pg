package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.example.demo.entity.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DBへのアクセスメソッドを呼び出すDao
 *
 */
@Repository
public class LoginUserDao {
    
    @Autowired
	EntityManager em;
	
	/**
	 * フォームの入力値から該当するユーザを検索 合致するものが無い場合Nullが返される
	 * @param userName
	 * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
	 */
	public LoginUser findUser(String userName) {
		String query = "";
		query += "SELECT * ";
		query += "FROM users ";
		query += "WHERE user_name = :userName "; //setParameterで引数の値を代入できるようにNamedParameterを利用
		
		//EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
		LoginUser logicuser = null;
		try{
			logicuser = (LoginUser)em.createNativeQuery(query, LoginUser.class).setParameter("userName", userName).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
		return logicuser;
	}
}
