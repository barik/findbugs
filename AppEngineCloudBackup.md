  1. Download the [App Engine SDK for Python](http://code.google.com/appengine/downloads.html#Google_App_Engine_SDK_for_Python)
  1. Download and install [Python 2.5](http://www.python.org/download/releases/2.5/) (if you don't already have it)

Then do this (example is for Windows):

```
cd <APPENGINE_PYTHON_FOLDER>
set MODELS_FILE=<FINDBUGS_CHECKOUT>/sandbox/appEngineCloud/etc/models.py

bulkloader.py --url=http://theflybush.appspot.com/remote_api --download --config_file=%MODELS_FILE% --kind=AppEngineDbIssue --filename=DbIssue.csv

bulkloader.py --url=http://theflybush.appspot.com/remote_api --download --config_file=%MODELS_FILE% --kind=AppEngineDbEvaluation --filename=DbEvaluation.csv

bulkloader.py --url=http://theflybush.appspot.com/remote_api --download --config_file=%MODELS_FILE% --kind=AppEngineDbInvocation --filename=DbInvocation.csv

bulkloader.py --url=http://theflybush.appspot.com/remote_api --download --config_file=%MODELS_FILE% --kind=AppEngineDbUser --filename=DbUser.csv
```

The columns in the CSV file are as follows:

| **DbIssue.csv** | hash | bugPattern | priority | primaryClass | firstSeen | lastSeen | bugLink |
|:----------------|:-----|:-----------|:---------|:-------------|:----------|:---------|:--------|
| **DbEvaluation.csv** | who (openid) | designation | comment  | issue hash   | when      | invocation id |
| **DbInvocation.csv** | id   | who (openid) | startTime | endTime      |
| **DbUser.csv**  | openid | email      |