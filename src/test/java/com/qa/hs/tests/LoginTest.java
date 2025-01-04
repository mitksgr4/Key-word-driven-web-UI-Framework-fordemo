package com.qa.hs.tests;
import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeyWordEngine;

public class LoginTest {
	
	public KeyWordEngine keyWordEngine;
	
	@Test
	public void logintest()
	{
		keyWordEngine = new KeyWordEngine();
		
		keyWordEngine.startExecution("login");
	}
	
	@Test
	public void SignUpTest()
	{
		keyWordEngine = new KeyWordEngine();
		
		keyWordEngine.startExecution("signup");
	}
	

}
