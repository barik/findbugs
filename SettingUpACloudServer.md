

When you use FindBugs with the _FindBugs Cloud_, by default it connects to http://findbugs-cloud.appspot.com. However, if you want to use the Cloud for your company's code, you probably don't want your code shared with the public. But don't worry...

This guide explains how to set up a private _FindBugs Cloud_ server, which can run on a server on your local intranet. The guide also describes how to connect to this private Cloud from within FindBugs, Ant, Eclipse, and IntelliJ.

# The FindBugs Cloud Server #

The Cloud server is built upon Jetty, DataNucleus, and HSQLDB, and shares most of its code with the public Cloud at _findbugs-cloud.appspot.com_.

It's very easy to use - simply download [FindBugsCloudServer.zip](http://code.google.com/p/findbugs/downloads/list?q=label:CloudServer), unarchive it, and run either `fbcloud.bat` (Windows) or `./fbcloud.sh` (Unix/Mac).

## Building from source (optional) ##

If you prefer a more recent version of the Cloud Server, you can check it out and build it using Subversion and Ant:

```
svn checkout http://findbugs.googlecode.com/svn/trunk/
cd sandbox/localCloud
ant dist
```

This will take a while, but when it finishes, run it by typing:

```
cd dist
fbcloud (Windows) - ./fbcloud.sh (Unix/Mac)
```

# Creating a Cloud Plugin #

To connect to your new Cloud server, you need to create a FindBugs plugin. Luckily, the code you need is already in the FindBugs codebase, so all you need is to create some configuration files.

## findbugs.xml ##

First, create a file called _findbugs.xml_. Here's an example file:

```
<!--
  Custom Cloud plugin configuration file.
-->

<FindbugsPlugin xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:noNamespaceSchemaLocation="findbugsplugin.xsd"
                pluginid="com.example.cloud"
                defaultenabled="true"
                provider="Example Services"
                website="http://example.com">

    <Cloud id="com.example.cloud"
           cloudClass="edu.umd.cs.findbugs.cloud.appEngine.WebCloudClient"
           usernameClass="edu.umd.cs.findbugs.cloud.username.WebCloudNameLookup"
           onlineStorage="true">
        <Property key="webcloud.host">http://cloud.example.com</Property>
    </Cloud>

</FindbugsPlugin>
```

## messages.xml ##

Next, create a file called _messages.xml_. Example:

```
<!--
  Example Cloud plugin messages file.
-->

<MessageCollection xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:noNamespaceSchemaLocation="messagecollection.xsd">
    <Plugin>
        <ShortDescription>Example Cloud</ShortDescription>
        <Details>
            <![CDATA[
<p>
Provides the Example FindBugs Cloud plugin from Example, Inc.
</p>
]]>
        </Details>
    </Plugin>


    <Cloud id="com.example.cloud">
        <Description>Example Cloud</Description>
        <Details>Comments are stored on the Example FindBugs Cloud at http://cloud.example.com</Details>
    </Cloud>

</MessageCollection>
```

## Creating the plugin jar ##

Finally you need to package these into a jar file, which is just a zip file with a different file extension. For example, on Linux:

```
zip exampleCloudPlugin.jar findbugs.xml messages.xml
```

# Using your new plugin #

Using the plugin with the FindBugs command-line client, FindBugs GUI, and the FindBugs Ant task is simple. Simply drop your jar in `$FINDBUGS_HOME/plugin`.

You can make your plugin the _default Cloud plugin_ by setting a system property - for example `-Dfindbugs.cloud.default=com.example.cloud`

## FindBugs GUI ##

In the FindBugs GUI, simply choose your custom plugin in the "Store comments in:" section of the New Project and Reconfigure dialogs.

## Ant ##

As the FindBugs Ant task requires that you specify a FindBugs Home, you can drop your plugin jar into `$FINDBUGS_HOME/plugin` as described above. This will allow the Ant task to use your Cloud to include evaluations and comments in the "withMessages" XML output. Enabling the Cloud here also adds special XML tags for the [Hudson](http://wiki.hudson-ci.org/display/HUDSON/FindBugs+Plugin) continuous integration system.

To enable Cloud comments in your FindBugs XML output:

```
<findbugs home="."
          output="xml:withMessages"
          cloud="com.example.cloud"
          jvmargs="-Dfindbugs.failIfCloudNotFound=true"
          outputFile="build/findbugs-with-cloud.xml" >
    <class location="classes" />
    <sourcePath path="src/java:src/junit"/>
</findbugs>
```

## Eclipse ##

## IntelliJ ##

# Backing up your Cloud data #

The private Cloud does not currently store any backups of users' Cloud comments and evaluations. Thus, this is up to you. The Cloud data is stored in the `clouddb` subfolder of the FindBugs Cloud working directory.