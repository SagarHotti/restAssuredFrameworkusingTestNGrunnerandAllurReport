package testclasspackage;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctionPackage.API_common_Function;
import commonFunctionPackage.Utility_commonFunction;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.post_req_repository;

public class post_tc_2 {
	@Test
	public static void execute() throws IOException {
		for(int i=0 ; i<5 ;i++) 
		{
		String baseURI=post_req_repository.base_URI();
		String requestBody=post_req_repository.post_req_tc2();
		String resource=post_req_repository.post_resource();
		
		
	    int statusCode=API_common_Function.response_statuscode(baseURI, resource, requestBody);
	    System.out.println("statusCode");
	    if(statusCode == 201)
		{
		String res_Body = API_common_Function.response_Body(baseURI, resource, requestBody);	
		post_tc_2.validator(res_Body,statusCode);
		Utility_commonFunction.evidencefilecreator("post_tc_2", requestBody, res_Body);
		break;
		}
		else 
		{
		System.out.println("correct states code is not found hence retrying the API");
		}
		
		}
			
} 
	 
	public static void validator(String res_Body,int statescode) throws IOException  { 
			// step 3 parse the response body
			JsonPath jsp = new JsonPath(res_Body);
			
			String res_name = jsp.get("name");
			String res_job = jsp.get("job");
			String res_id = jsp.get("id");
			String res_createdAt = jsp.get("createdAt");
			
			System.out.println(res_name);
			System.out.println(res_job);
			System.out.println(res_id);
			System.out.println(res_createdAt);
			
			String trim_date=res_createdAt.substring(0,10);
			
			//generate date
			LocalDateTime date=LocalDateTime.now();
			String exp_date=date.toString().substring(0,10);
			
			// parse request body and its parameters
			JsonPath jspreq=new JsonPath (post_req_repository.post_req_tc2());
			String req_name=jspreq.getString("name");
			String req_job=jspreq.getString("job");		
			
			 // step 4 : validate responsebody param
			Assert.assertEquals(statescode,201);
			Assert.assertEquals(req_name,res_name);
			Assert.assertEquals(req_job,res_job);
			Assert.assertEquals(req_job, res_job);
			Assert.assertNotNull(res_id);
			Assert.assertEquals(trim_date, exp_date);
			
			
			}
		
	}


