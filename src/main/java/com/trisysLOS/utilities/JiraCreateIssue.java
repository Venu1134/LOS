package com.trisysLOS.utilities;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JiraCreateIssue {

	boolean isCreateIssue();
}
