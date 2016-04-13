# Introduction #

## System properties ##
| Property | Example | Description |
|:---------|:--------|:------------|
| findbugs.cloud.default | edu.umd.cs.findbugs.cloud.appengine.findbugs-cloud |
| findbugs.failIfCloudNotFound | true / false | makes FindBugs quit if the specified cloud is not found |
| findbugs.cloud.token | 238b6fc80cec17ec | allows you to upload new issues directly from Ant or the command line. see http://findbugs-cloud.appspot.com/token |

## Cloud properties ##

| Property |
|:---------|
| appengine.local | true or false |
| appengine.host.local | URL for local cloud server | only used if appengine.local=true |
| appengine.host | the URL for the cloud | only if appengine.local=false |
| appengine.never\_save\_session | true or false | if "true", prevents session info from being saved between launches |
| cloud.bugTrackerType | GOOGLE\_CODE or JIRA |
| cloud.bugTrackerUrl | URL for Google Code project or JIRA installation | ex. http://code.google.com/p/findbugs or http://jira.atlassian.com/secure/Dashboard.jspa |