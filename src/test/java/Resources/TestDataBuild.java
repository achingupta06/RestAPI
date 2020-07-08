package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.addPlace;
import pojo.location;

public class TestDataBuild {
	
	public addPlace addPlacePayload(String name, String language, String address)
	{	
		addPlace p=new addPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String placeid)
	{
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
	}

}
