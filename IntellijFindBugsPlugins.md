

# Reviews #

Here's a quick run-down before we get to the individual reviews. There are three ways to run FindBugs within IntelliJ IDEA, via three separate plugins.

|                      | **QAPlug-FindBugs**  | **FindBugs**    | **FindBugs-IDEA**    |
|:---------------------|:---------------------|:----------------|:---------------------|
| **Open-source**        | <font color='#880000'>No</font>                | [Yes](http://code.google.com/p/idea-findbugs/)           | [Yes](https://findbugs-idea.dev.java.net/)    |
| **Ease of use**        | <font color='#008800'>Good</font>                | <font color='#008800'>Good</font>           | <font color='#880000'>Cluttered, buggy</font>    |
| **Integration / L&F**  | <font color='#00ff00'>Great</font>               | <font color='#880000'>Poor</font>           | <font color='#00ff00'>Great</font>               |
| **Bug information**    | <font color='#00ff00'>Great</font>               | <font color='#880000'>Poor</font>           | <font color='#00ff00'>Great</font>               |
| **Result filtering**   | <font color='#008800'>Some filters</font>        | <font color='#880000'>Poor</font>           | <font color='#00ff00'>Lots of filters</font>     |
| **Analysis scope**     | <font color='#00ff00'>Lots of options</font>     | <font color='#880000'>Only project/module</font> | <font color='#00ff00'>Lots of options</font>     |
| **Configuration**      | <font color='#00ff00'>Detectors, analysis</font> | <font color='#888800'>Analysis only</font>  | <font color='#00ff00'>Detectors, analysis</font>  |
| **Custom FindBugs**   | <font color='#880000'>No</font>                  | <font color='#880000'>No</font>             | <font color='#880000'>No</font>                  |
| **Analysis progress**  | <font color='#00ff00'>Great</font>               | <font color='#880000'>None</font>           | <font color='#00ff00'>Great</font>               |
| **Runs in background** | <font color='#880000'>No</font>                  | <font color='#008800'>Yes</font>            | <font color='#008800'>Yes</font>                 |
| **Load/save analysis** | <font color='#880000'>No</font>                  | <font color='#880000'>No</font>             | <font color='#880000'>No</font> (broken)         |
|                      |                      |                 |                      |
| **Downloads**        | 2,700                | 4,700           | 13,600               |
| **Home Page**        | [link](http://qaplug.com/) | [link](http://code.google.com/p/idea-findbugs/) | [link](https://findbugs-idea.dev.java.net/) |
| **Plugin Link**      | [link](http://plugins.intellij.net/plugin/?&id=4597) | [link](http://plugins.intellij.net/plugin/?id=3801) | [link](http://plugins.intellij.net/plugin/?id=3847) |

These plugins can all be downloaded through IntelliJ's built-in plugin manager.

## QAPlug-FindBugs ##

QAPlug provides code analysis for CheckStyle, FindBugs, and PMD.

Running FindBugs is easy and straightforward. To start, you click the main Analyze menu and select Analyze Code. You are presented with a standard IntelliJ dialog asking about the scope of the analysis. You can choose to analyze the whole project, specific files or folders, or one of IntelliJ's pre-defined scopes.

![http://findbugs.googlecode.com/svn/wiki/qaplug-scope.png](http://findbugs.googlecode.com/svn/wiki/qaplug-scope.png)

During analysis, a helpful progress bar shows you what's happening. Unfortunately the analysis _cannot_ be run in the background, and clicking the Cancel button doesn't do anything.

![http://findbugs.googlecode.com/svn/wiki/qaplug-scanning.png](http://findbugs.googlecode.com/svn/wiki/qaplug-scanning.png)

The results window looks and acts like other IntelliJ result tabs (search, code inspection, etc). It allows you to optionally categorize bugs by severity (warning vs. error) and by package.

![http://findbugs.googlecode.com/svn/wiki/qaplug-results.png](http://findbugs.googlecode.com/svn/wiki/qaplug-results.png)

The detectors are configurable by clicking the configuration button on the results:

![http://findbugs.googlecode.com/svn/wiki/qaplug-config.png](http://findbugs.googlecode.com/svn/wiki/qaplug-config.png)

Note - one possible point of confusion is that the project must be built for this plugin to show any results. And any changes will not be reflected until building. This may confuse IntelliJ users since the built-in code analysis always operates on the source code.

Pro:
  * Simple interface
  * Tight integration, IntelliJ look&feel
  * Shows detailed information about bugs in the results window
  * Shows detailed progress
  * Allows custom analysis scope
  * Allows enabling/disabling individual detectors

Con:
  * Cannot provide own version of FindBugs, must use built-in version
  * Cannot run analysis in background
  * Analysis cannot be cancelled
  * Cannot load or save analyses

## FindBugs plugin ##

The plugin called simply "FindBugs" provides a quick & dirty way to run FindBugs. To run it, you simply click the toolbar icon:

![http://findbugs.googlecode.com/svn/wiki/findbugs-toolbar-icon.png](http://findbugs.googlecode.com/svn/wiki/findbugs-toolbar-icon.png)

And configure the analysis:

![http://findbugs.googlecode.com/svn/wiki/findbugs-scope.png](http://findbugs.googlecode.com/svn/wiki/findbugs-scope.png)

The plugin then runs everything in the background using IntelliJ's background tasks feature. You can click the task in the status bar to see a progress bar for "copying resources":

![http://findbugs.googlecode.com/svn/wiki/findbugs-progress1.png](http://findbugs.googlecode.com/svn/wiki/findbugs-progress1.png)

Followed by the real FindBugs analysis progress bar, which doesn't move. And despite the little red X button to the right, FindBugs execution cannot be cancelled!

![http://findbugs.googlecode.com/svn/wiki/findbugs-progress2.png](http://findbugs.googlecode.com/svn/wiki/findbugs-progress2.png)

The results are shown in an IntelliJ compilation results window, which leaves you with some inappropriate terminology and toolbar buttons such as "Exclude from compilation":

![http://findbugs.googlecode.com/svn/wiki/findbugs-results.png](http://findbugs.googlecode.com/svn/wiki/findbugs-results.png)

Pro:
  * Allows configuration of bug priority and analysis effort
  * Allows analyzing individual module or project
  * Runs analysis in background

Con:
  * Cannot analyze just one file or folder
  * Analysis shows no progress
  * Analysis results cannot be sorted, filtered, or grouped
  * Cannot provide own version of FindBugs, must use built-in version
  * Does not show any details or documentation about bugs
  * Does not allow enabling or disabling individual detectors
  * Does not allow load/save of analyses

## FindBugs-IDEA ##

FindBugs-IDEA is surely the most mature and robust FindBugs plugin for IntelliJ. It lacks the ease of use of QAPlug but provides much more in the way of configuration options and view customization.

First you click the Tools menu:

![http://findbugs.googlecode.com/svn/wiki/fbidea-menu.png](http://findbugs.googlecode.com/svn/wiki/fbidea-menu.png)

And you see a somewhat cluttered dialog for configuring the analysis. It includes a tab for the detectors (with a description of each one):

![http://findbugs.googlecode.com/svn/wiki/fbidea-dialog1.png](http://findbugs.googlecode.com/svn/wiki/fbidea-dialog1.png)

(I had to click "Restore Defaults" for the detectors to show up - bug!)

as well as a tab for filtering the results:

![http://findbugs.googlecode.com/svn/wiki/fbidea-dialog2.png](http://findbugs.googlecode.com/svn/wiki/fbidea-dialog2.png)

The analysis shows a somewhat helpful progress window _and_ can run in the background _and_ it can be cancelled!

![http://findbugs.googlecode.com/svn/wiki/fbidea-progress.png](http://findbugs.googlecode.com/svn/wiki/fbidea-progress.png)

When the analysis is complete, the results are shown in an toolwindow very much like IntelliJ's built-in results windows:

![http://findbugs.googlecode.com/svn/wiki/fbidea-results.png](http://findbugs.googlecode.com/svn/wiki/fbidea-results.png)

Here you can group by any combination of category, severity, priority, rank, and Java package.

The results window has a button to "export to XML/HTML", but it didn't work for me. First the Browse button didn't work, then when I typed the folder name by hand, I got this error message:

![http://findbugs.googlecode.com/svn/wiki/fbidea-export-error.png](http://findbugs.googlecode.com/svn/wiki/fbidea-export-error.png)

Yes, my desktop is writable. Using forward or back slashes didn't seem to help.

Pro:
  * Allows custom analysis scope
  * Allows enabling/disabling individual detectors
  * Allows configuring FindBugs analysis options
  * Shows detailed information about bugs, both in the configuration panel and the results window
  * Tight integration, IntelliJ look&feel
  * Shows detailed progress
  * Analysis can be run in background and can be cancelled

Con:
  * Sacrifices usability for configurability
  * Buggy - came across several bugs in the 20 minutes it took to write this review
  * Cannot provide own version of FindBugs, must use built-in version
  * Cannot load past analysis, and saving analysis doesn't work