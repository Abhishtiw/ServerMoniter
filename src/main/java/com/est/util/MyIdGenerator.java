package com.est.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.mysql.jdbc.PreparedStatement;




public class MyIdGenerator implements IdentifierGenerator {

	
	@Override
	public Serializable generate(SessionImplementor si, Object obj) throws HibernateException {
		
		int prefix=100;
		Connection connection = si.connection();
		try{
			java.sql.PreparedStatement preparedStatement = connection.prepareStatement("select COUNT(applicationId) as id from application");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				int id = resultSet.getInt(1)+1;
				int generatedId = prefix + new Integer(id);
				System.out.println("generated Id: " + generatedId);
				return generatedId;
			}
		}catch (SQLException e) {
		}
		return null;
	
	}

}
