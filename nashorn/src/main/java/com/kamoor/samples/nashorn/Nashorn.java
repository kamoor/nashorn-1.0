package com.kamoor.samples.nashorn;

import java.io.FileReader;
import java.net.URL;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class Nashorn {

	public static void main(String[] args) throws Exception{
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
		//loaded mustache
		scriptEngine.eval(new FileReader(ClassLoader.getSystemResource("mustache.js").getFile()));
		
		scriptEngine.eval(new FileReader(ClassLoader.getSystemResource("rest.js").getFile()));
		
		/*You need to have rest service with Content-Type application/json */
		scriptEngine.eval("var restResponse =REST.get('http://localhost:9000/v1/test/hello-world');");
		System.out.println(scriptEngine.eval("Mustache.render('Status : {{status}}',restResponse);"));
		System.out.println(scriptEngine.eval("Mustache.render('<html><body>Name: {{data.firstName}} </body></html>', restResponse); "));

		

	}

}
