package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	JsonPath js;
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null) //this is added to ensure that below code is run once and logging.txt file is not created again.
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
		
	}
		
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\achgupta\\eclipse\\java-2020-03\\eclipse\\RestApi\\src\\test\\java\\Resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String resp=response.asString();
	    js = new JsonPath(resp);
	    return js.get(key).toString();
	}

}
