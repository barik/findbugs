The IntelliJ FindBugs plugin ([FindBugs-IDEA](http://findbugs-idea.dev.java.net)) currently needs a patch applied to allow it to use the FindBugs Cloud.

  1. Check out https://findbugs-idea.dev.java.net/svn/findbugs-idea/trunk/src - you may need to [register for a java.net account](https://www.dev.java.net/servlets/Join) to do this
  1. Apply this [patch](http://findbugs.googlecode.com/svn/wiki/fb-idea-oct-22.patch)
  1. In findbugs-idea/lib, delete asm-`*`.jar bcel.jar commons-lang-`*`.jar dom4j-`*`.jar findbugs.jar jaxen-`*`.jar jFormatString.jar jsr305.jar
  1. Add [findbugs-full-2.0.0-dev-\*.jar](http://findbugs.googlecode.com/svn/wiki/findbugs-full-2.0.0-dev-20101022.jar) and [webCloudClient.jar](http://findbugs.googlecode.com/svn/wiki/webCloudClient.jar) to lib/
  1. Open the project (FindBugs-IDEA.ipr) in IntelliJ
  1. [Add an IntelliJ plugin development SDK](http://www.jetbrains.com/idea/webhelp/setting-up-intellij-idea-for-writing-plugins.html)
  1. Click "Run" or "Debug" in the Run menu. This will open a debug instance of IntelliJ.

To test the Cloud features, open (or create) an IntelliJ project and click the FindBugs button in the toolbar. The cloud comments will show up on the bottom right of the screen. You can enable the FindBugs cloud by clicking the ">> change" link.