FindBugs is primarily developed in Eclipse, but it is possible to develop with IntelliJ IDEA. We have an open-source license for IntelliJ IDEA 9.0 Ultimate - ask Bill or Keith for details.

# Setting up the project #

## Using the existing IntelliJ project files in Subversion (Option 1) ##

  1. Check out the code - https://findbugs.googlecode.com/svn/trunk/ - I checked out findbugs/ and sandbox/
  1. Open trunk/ as a project in IntelliJ (there is no .ipr file, we use directory mode which uses the .idea directory)

## Importing the FindBugs Eclipse project files (Option 2) ##

IntelliJ's Eclipse project import function seems to work OK. Here's how I used it:

  1. Create project from existing sources - choose Eclipse of course
  1. Import modules findbugs, findbugsTestCases, appEngineCloud, appEngineCloudClient, appEngineCloudProtocol
  1. Set the project JDK to my 1.5 JDK, and the findbugsTestCses JDK to my 1.6 JDK
  1. Remove the "ECLIPSE" library from the findbugs module dependencies (causes ASM 3.1 to be loaded which creates build problems)
  1. Add the findbugs module as a dependency of findbugsTestCases
  1. Add the appEngineCloudClient module as a dependency of findbugs

It will complain on startup about FINDBUGS\_ANNOTATIONS missing. Just click OK.

For App Engine Cloud I installed the App Engine integration plugin, then:

  1. Add Web facet
  1. Add App Engine facet under the Web facet
  1. Set sandbox/appEngineCloud/war/WEB-INF/web.xml as web.xml
  1. Set sandbox/appEngineCloud/war as the web root
  1. Check "Run Enhancer" for src/java in the App Engine facet settings

See more App Engine Cloud instructions below.

# App Engine Cloud #

You may need to add the AppEngineCloudClient module as a dependency of the findbugs module if you wish to use the App Engine Cloud in FindBugs. Make sure not to commit this change, as Eclipse does not support cyclic dependencies like IntelliJ does.

If you wish to run the AppEngine Cloud server, you will need the IntelliJ Google App Engine plugin. On top of that you may need to add extra libraries to the App Engine SDK entry:

**NOTE:** you may also need the geronimo jars in lib/user/orm!

![http://findbugs.googlecode.com/svn/wiki/appengine%20dev%20library.png](http://findbugs.googlecode.com/svn/wiki/appengine%20dev%20library.png)