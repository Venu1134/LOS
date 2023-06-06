package com.trisysLOS.utilities;

import com.aventstack.extentreports.ExtentTest;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	private JiraClient Jira;
	private String project;
	private String jiraURL;
	
	public JiraServiceProvider(String JiraURL, String username, String password, String project) {
		this.jiraURL = JiraURL;
		// create basic authentication object
		BasicCredentials creds = new BasicCredentials(username, password);
		// initialize the Jira client with the url and the credentials
		Jira = new JiraClient(JiraURL, creds);
		this.project = project;
	}
	
	public void createJiraIssue(String issueType, String summary, String description, String reporterName, String attachment) {
		
		try {
			//Avoid Creating duplicate issue
			Issue.SearchResult sr = Jira.searchIssues("summary ~ \""+ summary+"\"");
			if(sr.total!=0) {
				System.out.println("Same Issue already Exists on Jira");
				return;
			}
			//Create Issue if not exists
			FluentCreate fluentCreate = Jira.createIssue(project, issueType);
			fluentCreate.field(Field.SUMMARY, summary);
			fluentCreate.field(Field.DESCRIPTION, description);
			fluentCreate.field(Field.ATTACHMENT, attachment);
			Issue newIssue = fluentCreate.execute();
			System.out.println("********************************************");
			System.out.println("New issue created in Jira with ID: "+newIssue);
			System.out.println("New issue URL is :"+jiraURL+"/browse/"+newIssue);
			System.out.println("*******************************************");
		}catch(JiraException e) {
			e.printStackTrace();
		}
	}
}
