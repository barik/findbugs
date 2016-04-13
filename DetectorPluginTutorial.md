The new FindBugs plugin API in 2.0 allows for easily deploying your custom plugins within your team or organization. Plugins can perform a variety of tasks within FindBugs. The plugin in this tutorial will detect a new kind of bug in bytecode.




# Creating Our Plugin #

_NOTE: Though I'm an avid IntelliJ user, for the sake of this tutorial I'll use Eclipse. As a result, there may be faster or easier ways to do certain things that I just don't know about._

## Setting Up Eclipse ##

http://findbugs.googlecode.com/svn/wiki/tutorial-img/1-new-java-project.PNG

First we open Eclipse, and create a new project by going to _File->New Java Project_. I'll call it _FB Plugin Tutorial_. As FindBugs still runs on Java 5 as well as Java 6, we'll use the _J2SE-1.5_ JRE to compile - that way we can ensure that we don't accidentally use any Java 6 API's, and our plugin will run on every FindBugs installation.

You can then click Finish, as we'll be adding sourcepaths and libraries manually.

### Setting Up the Classpath ###

To develop a plugin, you first need to add FindBugs itself to the classpath, so you can extend its detector classes and so on. The easiest way to do this is to simply copy the needed jars from the FindBugs distribution's _lib_ folder.

First I'll create a _lib_ folder in our new project.

http://findbugs.googlecode.com/svn/wiki/tutorial-img/2-new-folder.PNG

Now I download the latest FindBugs release at http://findbugs.sourceforge.net/downloads.html, extract it, and copy the two files we'll need - _findbugs.jar_ and _bcel.jar_ - into our new _lib_ folder.

![http://findbugs.googlecode.com/svn/wiki/tutorial-img/3-copy-jars.png](http://findbugs.googlecode.com/svn/wiki/tutorial-img/3-copy-jars.png)

Finally, select the two jars in Eclipse, right click, and choose _Build Path->Add to Build Path_

## Writing the Bug Detector ##

Now, if you're reading this tutorial, you may already an idea for a detector in mind. For the purpose of this tutorial I'm going to implement a detector that is already a part of FindBugs.

**QUIZ: What will this code print?**

```
BigDecimal a = new BigDecimal("0.1");
BigDecimal b = a.add(new BigDecimal(0.1));
System.out.println(b);
```

Any reasonable person might assume it would print `0.2` but in fact it prints:

```
0.2000000000000000055511151231257827021181583404541015625
```

Uh oh! What a perfect opportunity for a bug detector! (Well, almost perfect - I'll explain later.)

Well - unfortunately this isn't the time or place to explain how to write a detector. (See [IBM Developerworks](http://www.ibm.com/developerworks/library/j-findbug2/) for some help there.) But suffice to say here's the detector code:

```
package tutorial;

import java.math.BigDecimal;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.OpcodeStack;
import edu.umd.cs.findbugs.bcel.OpcodeStackDetector;

public class DetectorTutorial extends OpcodeStackDetector {
    private BugReporter bugReporter;

    public DetectorTutorial(BugReporter bugReporter) {
        this.bugReporter = bugReporter;
    }

    @Override
    public void sawOpcode(int seen) {
        if (seen == INVOKESPECIAL && getClassConstantOperand().equals("java/math/BigDecimal")
                && getNameConstantOperand().equals("<init>") && getSigConstantOperand().equals("(D)V")) {
            OpcodeStack.Item top = stack.getStackItem(0);
            Object value = top.getConstant();
            if (value instanceof Double) {
                double arg = ((Double) value).doubleValue();
                String dblString = Double.toString(arg);
                String bigDecimalString = new BigDecimal(arg).toString();
                boolean ok = dblString.equals(bigDecimalString) || dblString.equals(bigDecimalString + ".0");

                if (!ok) {
                    boolean scary = dblString.length() <= 8 && dblString.toUpperCase().indexOf("E") == -1;
                    bugReporter.reportBug(new BugInstance(this, "TUTORIAL_BUG", scary ? NORMAL_PRIORITY : LOW_PRIORITY)
                            .addClassAndMethod(this).addString(dblString).addSourceLine(this));
                }
            }
        }
    }
}
```

Basically, it reports calls to `new BigDecimal(double)` where the `toString` representations of the `Double` and the `BigDecimal` differ. To prevent heaps of false positives, the detector only reports these instances at low priority unless the number being passed is very large or has a large fractional component.

Anyway...now that we've written our code, it's time to deploy and test it!

# Deployment & Testing #

## Building ##

FindBugs plugins are simply jar files which contain, at minimum, two files: `findbugs.xml` and `messages.xml`. For now, I'll put these in the root project folder (`FB Plugin Tutorial`), to allow us to use the Eclipse jar exporter later on in the tutorial.

### findbugs.xml ###

`findbugs.xml` tells FindBugs what your plugin does and how to load it. Here's mine:

```
<FindbugsPlugin xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:noNamespaceSchemaLocation="findbugsplugin.xsd" 
	pluginid="edu.umd.cs.findbugs.pluginTutorial"
	provider="FindBugs Tutorial" 
	website="http://findbugs.sourceforge.net">

        <Detector class="tutorial.DetectorTutorial" reports="TUTORIAL_BUG" />
        <BugPattern type="TUTORIAL_BUG" abbrev="TU" category="CORRECTNESS"/>

</FindbugsPlugin>
```

So what is all this junk?

  * `FindbugsPlugin`
    * the `xmlns`/`xsi` attributes are for XML Schema validation. If you like, you can run this file through an XML validator to be sure it validates as part of your build process.
    * `pluginid` is a string that is used to identify your plugin. It must be unique - users cannot install two plugins with the same _pluginid_. The ID can be any string you like - but we encourage Java-like names, suhc as _edu.umd.cs.findbugs.pluginTutorial_.
    * `provider` and `website` describe you or your organization. The user will see this in the plugin settings dialog.
  * `Detector` - specify one of these tags for each detector class you'd like this plugin to include.
    * `class` is the fully qualified name of your detector class. It must implement the edu.umd.cs.findbugs.Detector interface
    * `reports` is a comma-separated list of all the bug patterns your detector reports via [BugReporter.reportBug](http://findbugs.sourceforge.net/api/edu/umd/cs/findbugs/BugReporter.html#reportBug(edu.umd.cs.findbugs.BugInstance))

### messages.xml ###

The second file in the jar is `messages.xml`, which contains English descriptions of your plugin, its detectors, and the bug patterns it reports. You can also add `messages_fr.xml`, `messages_ja.xml`, etc to support other languages.

```
<?xml version="1.0" encoding="UTF-8"?>
<MessageCollection xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:noNamespaceSchemaLocation="messagecollection.xsd">

	<Plugin>
		<ShortDescription>FindBugs Plugin Tutorial Plugin</ShortDescription>
		<Details>Provides detectors as part of the FindBugs detector plugin
			tutorial.</Details>
	</Plugin>

        <Detector class="tutorial.DetectorTutorial">
                <Details>
                        Finds instances of BigDecimals being created
                        with doubles.
                </Details>

        </Detector>

        <BugPattern type="TUTORIAL_BUG">
                <ShortDescription>BigDecimal created from double</ShortDescription>
                <LongDescription>BigDecimal created from double in {1}</LongDescription>
                <Details>
<![CDATA[
<p>Due to the way double-precision floating point values are represented in Java, creating a new BigDecimal from a double is unreliable and may produce surprising results.</p>
]]>
                </Details>
        </BugPattern>

        <BugCode abbrev="TU">Tutorial</BugCode>
</MessageCollection>
```

Any of the `Details` sections can contain basic HTML - but remember to wrap it in a [CDATA](http://www.w3schools.com/xml/xml_cdata.asp) section to avoid seeing XML quoting errors.

You might notice the use of the placeholder `{1}` - actually, you have access to a variety of variables in the `LongDescription`. These placeholders (`{0}`, `{1}`, etc.) refer to [BugAnnotation](http://findbugs.sourceforge.net/api/edu/umd/cs/findbugs/BugAnnotation.html)s attached to the BugInstance reported by your detector. You may also use constructs like `{1.name}` or `{1.returnType}`. **TODO: MORE INFO??**

### Building in Eclipse ###

Now that we have all the configuration files we need, it's time to build the jar. You can do this on the command line pretty easily. Here's a way to set up a one-click method of building the jar from Eclipse.

http://findbugs.googlecode.com/svn/wiki/tutorial-img/4-export-jar.PNG

Eclipse provides a quick way to build the plugin jar using its _Export_ tool. Simply click _File->Export_ and choose _JAR file_.

http://findbugs.googlecode.com/svn/wiki/tutorial-img/5-export-jar-page2.PNG

On the following page, un-check all the boxes, then check _findbugs.xml_ and _messages.xml_. Check the _Export all output folders for checked projects_ and _Export java source files and resources_. This tells Eclipse to include our detector classes ("all output folders") as well as the two XML files ("resources").

http://findbugs.googlecode.com/svn/wiki/tutorial-img/6-export-jar-page3.PNG

Choose a destination for the jar (I just called it _TutorialDetectorPlugin.jar_ and placed it in the project root) and click _Next_. If you like, check the box to save this configuration for future use. Now click _Finish_.

## Loading Our Plugin ##

FindBugs 2.0 makes it easy to load plugins. Here are a couple of ways:

  1. Throw it in _FINDBUGS\_HOME/plugin_ where you unzipped or installed FindBugs
  1. Throw it in _~/.findbugs/plugin_ in your home directory
  1. Load it via the GUI

## Debugging ##