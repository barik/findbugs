# Client request - 2.0.0 #

```
<?xml version="1.0" encoding="UTF-8"?>

<findbugs-invocation 
    version="2.0.0" 
    app-name="FindBugs TextUI" 
    app-version="" 
    entry-point="edu.umd.cs.findbugs.workflow.FB" 
    os="Mac OS X" 
    java-version="1.6" 
    language="en"
    country="US"
    uuid="-7278433aeb625ca1">

    <plugin 
      id="edu.umd.cs.findbugs.plugins.core" 
      name="Core FindBugs plugin" 
      version="2.0.0" 
      release-date="1324433820000"/>

    <plugin 
      id="edu.umd.cs.findbugs.plugins.findbugsCommunalCloud" 
      name="FindBugs Cloud Plugin" 
      version=""/>

</findbugs-invocation>
```

# Client request - SVN trunk #
```

<?xml version="1.0" encoding="UTF-8"?>

<findbugs-invocation 
    version="2.0.1-dev-20120113" 
    app-name="FindBugs GUI" 
    app-version="2.0.1-dev-20120113" 
    entry-point="edu.umd.cs.findbugs.gui2.Driver" 
    os="Mac OS X" 
    java-version="1.6" 
    language="en" 
    country="US" 
    uuid="-7278433aeb625ca1">

  <plugin 
      id="edu.umd.cs.findbugs.plugins.core" 
      name="Core FindBugs plugin" 
      version="2.0.1-dev-20120113" 
      release-date="1326485520000"/>

  <plugin 
      id="edu.umd.cs.findbugs.plugins.findbugsCommunalCloud" 
      name="FindBugs Cloud Plugin" 
      version=""/>

</findbugs-invocation> 
```

# Server response #

```
<?xml version="1.0" encoding="UTF-8"?>
<fb-plugin-updates> 
    <plugin id="edu.umd.cs.findbugs.plugins.core"> 
        <release 
            date="12/21/2011 10:00 am EST" 
            version="2.0.0" 
            url="http://findbugs.cs.umd.edu/"> 
            <message>FindBugs 2.0.0 has been released</message>
        </release> 
    </plugin> 

</fb-plugin-updates>
```
# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages