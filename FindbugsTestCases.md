FindBugs test cases are stored in the SVN repository under [trunk/findbugsTestCases](http://code.google.com/p/findbugs/source/browse/#svn/trunk/findbugsTestCases)

When a bug or RFE is filed in the [bug tracker](https://sourceforge.net/tracker/?group_id=96405), and it's a useful bug, a new class is created in src/java/sfBugs.

## Annotations ##

There are special annotations just for classes in findbugsTestCases, to indicate places where warnings should and should not occur. They should be used to annotate methods. The "hard warnings" are used when a bug has been fixed - these are like unit tests. The soft warnings are more like "todo items."

Hard warnings (bugs):
  * @NoWarning
  * @ExpectWarning

Soft warnings (RFE's):
  * @DesireNoWarning
  * @DesireWarning

These annotations are checked when you enable the CheckExpectedWarnings detector.

## Unit testing ##

The DetectorsTest test case (in findbugs/src/junit) will run FindBugs and fail if any of the hard warnings are not matched.