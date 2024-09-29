package com.atm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.atm.factory.AtmFactory;
import com.atm.persistence.PersistenceLogic;

public class TestProj {
	
	
	public static void main(String[] args) {
		
		ApplicationContext context = new FileSystemXmlApplicationContext("src/com/atm/cfgs/applicationContext.xml");
		
		AtmFactory atmFactory  = context.getBean("factory",AtmFactory.class);
		
		atmFactory.AtmMachine();
	}

}
