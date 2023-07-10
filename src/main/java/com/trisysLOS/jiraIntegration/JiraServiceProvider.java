package com.trisysLOS.jiraIntegration;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {
	
	public JiraClient jira;
	public String project;
	
	public JiraServiceProvider(String jiraURL, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraURL, creds);
		this.project = project;
	}
	
	public void createJiraTicket(String issueType, String summary, String description, String reporter) {
		try {
			FluentCreate fluentCreate = jira.createIssue(project, issueType);
			fluentCreate.field(Field.SUMMARY, summary);
			fluentCreate.field(Field.DESCRIPTION, description);
			fluentCreate.field(Field.REPORTER, reporter);
			Issue newIssue = fluentCreate.execute();
			System.out.println("New issue got created in jira with ID : "+newIssue);
		} catch (JiraException e) {
			e.printStackTrace();
		}
	}
	

}
