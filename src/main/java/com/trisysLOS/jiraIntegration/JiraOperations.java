package com.trisysLOS.jiraIntegration;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JiraOperations {

	String jiraURL = PropertiesOperations.getPropertyValueByKey("JiraURL");
	String jiraUserName = PropertiesOperations.getPropertyValueByKey("JiraUserName");
	String jiraAccessKey = PropertiesOperations.getPropertyValueByKey("JiraAccessToken");

	public String createJiraIssue(String issueSummary, String issueDescription)
			throws ClientProtocolException, IOException, ParseException {

		String issueId = null; // to store bug id.

		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = jiraURL + "/rest/api/3/issue";
		HttpPost postRequest = new HttpPost(url);
		postRequest.addHeader("content-type", "application/json");

		String encoding = Base64.getEncoder().encodeToString((jiraUserName + ":" + jiraAccessKey).getBytes());
		
		postRequest.setHeader("Authorization", "Basic " + encoding);
		postRequest.setHeader("X-Atlassian-Token","nocheck");
        
		StringEntity params = new StringEntity(createPayloadForCreateJiraIssue(issueSummary, issueDescription));
		postRequest.setEntity(params);
		HttpResponse response = httpClient.execute(postRequest);

		// convert httpresponse to string
		String jsonString = EntityUtils.toString(response.getEntity());
		System.out.println("jsonString : "+jsonString);

		// convert sring to Json
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		issueId = (String) json.get("key");

		System.out.println("issueId : "+issueId);
		return issueId;

	}
	
	public void addAttachmentToJiraIssue(String issueId, String filePath) throws ClientProtocolException, IOException 
	{
		String pathname= filePath; 
		File fileUpload = new File(pathname);

		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = jiraURL+"/rest/api/3/issue/"+issueId+"/attachments";
		HttpPost postRequest = new HttpPost(url);

		String encoding = Base64.getEncoder().encodeToString((jiraUserName+":"+jiraAccessKey).getBytes());

		postRequest.setHeader("Authorization", "Basic " + encoding);
		postRequest.setHeader("X-Atlassian-Token","nocheck");

		MultipartEntityBuilder entity=MultipartEntityBuilder.create();
		entity.addPart("file", new FileBody(fileUpload));
		postRequest.setEntity( entity.build());
		HttpResponse response = httpClient.execute(postRequest);
		System.out.println(response.getStatusLine());

		if(response.getStatusLine().toString().contains("200 OK")){
			System.out.println("Attachment uploaded");
		} else{
			System.out.println("Attachment not uploaded");
		}
	}

	private static String createPayloadForCreateJiraIssue(String issueSummary, String issueDescription) {
		
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"assignee\": {\r\n"
				+ "            \"accountId\": \"62d7cfa310c44eb6e32188ba\"\r\n"
				+ "        },\r\n"
				+ "        \"components\": [\r\n"
				+ "            {\r\n"
				+ "                \"id\": \"10007\"\r\n"
				+ "            }\r\n"
				+ "        ],\r\n"
				+ "        \"description\": {\r\n"
				+ "            \"content\": [\r\n"
				+ "                {\r\n"
				+ "                    \"content\": [\r\n"
				+ "                        {\r\n"
				+ "                            \"text\": \""+issueDescription+"\",\r\n"
				+ "                            \"type\": \"text\"\r\n"
				+ "                        }\r\n"
				+ "                    ],\r\n"
				+ "                    \"type\": \"paragraph\"\r\n"
				+ "                }\r\n"
				+ "            ],\r\n"
				+ "            \"type\": \"doc\",\r\n"
				+ "            \"version\": 1\r\n"
				+ "        },\r\n"
				+ "        \"environment\": {\r\n"
				+ "            \"content\": [\r\n"
				+ "                {\r\n"
				+ "                    \"content\": [\r\n"
				+ "                        {\r\n"
				+ "                            \"text\": \"UAT\",\r\n"
				+ "                            \"type\": \"text\"\r\n"
				+ "                        }\r\n"
				+ "                    ],\r\n"
				+ "                    \"type\": \"paragraph\"\r\n"
				+ "                }\r\n"
				+ "            ],\r\n"
				+ "            \"type\": \"doc\",\r\n"
				+ "            \"version\": 1\r\n"
				+ "        },\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        },\r\n"
				+ "        \"labels\": [\r\n"
				+ "            \"FunctionalAutomationDefect\"\r\n"
				+ "        ],\r\n"
				+ "        \"priority\": {\r\n"
				+ "            \"id\": \"5\"\r\n"
				+ "        },\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"LOS\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \""+issueSummary+"\"\r\n"
				+ "    }\r\n"
				+ "}";
	}
}
