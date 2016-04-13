

# What is the FindBugs Cloud? #

The FindBugs Cloud is a way to collaboratively prioritize and evaluate issues reported by FindBugs. By ranking and discussing bugs on the Cloud, a dev team can reduce duplicate work while solving difficult problems in tandem.

This tutorial will go through how to set up your project to use the **_public_** FindBugs Cloud service, so you can share bug information among your teammates.

**_NOTE: This method involves submitting sensitive information about your code to public servers! Do not use on your company's proprietary code!_**


# Browsing Bug Comments #

When you first open the FindBugs analysis file you generated for your project, you'll see an empty Comments section.

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/browse-cloud-disabled.PNG

"Why doesn't it load comments automatically??" you might ask. Well, to use the Cloud, you must share the FQ classnames of any classes that contain bug annotations. For most companies, this is private information, so, by default, FindBugs does not enable the Cloud to prevent any accidental uploads of sensitive information.

So, if you work on an open-source project, or a project where classnames are not proprietary information, you can enable the Cloud with a few clicks:

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/cloud-picker.PNG

Now once it connects and syncs, you should see Cloud information for the bugs you select in the tree.

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/browse-cloud-enabled.PNG

# Signing In to Add Comments #

To add a comment or classify a bug ("should fix," "not a bug," etc), you first need to click the _Sign In_ button in the Comments area. You _don't need to register_ or anything, as long as you have an account with any OpenID provider.

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/openid.PNG

Once you complete the sign-in process in your web browser, you can make comments and your peers will see them almost immediately. Your comments will show up in your Hudson builds (if your Ant/Maven build is configured for it) as well!

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/browse-signedin.PNG

# Making Comments #

The FindBugs Cloud provides seven categories for classifying your project's bugs:

  * Needs further study
  * Not a bug`*`
  * Mostly harmless
  * Should fix
  * Must fix
  * I will fix`**`
  * Bad analysis
  * Obsolete/unused (will not fix)

`*` If a bug is overwhelmingly (or solely) classified as "Not a bug," it will be hidden from FindBugs output in certain situtations.

`**` The user who marked the issue "I will fix" is committing to fix this bug.

To classify a bug, or to change your classification, simply click the pulldown menu in the Comments area. Your change will be saved immediately to the Cloud.

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/designation.PNG

But sometimes labeling a bug is not enough - there's more information you want to convey. Perhaps you tried fixing it, or you have an idea of why the bug is there. In this case you can add a comment to the bug that all your teammates will see:

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/commenting.PNG

# Bulk Processing #

FindBugs also allows categorizing and adding comments to whole segments of bugs, based on their categorization in the bug tree. For example, in this screenshot I added a comment for all of the _Masked Field_ bugs in the codebase.


http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/batch-edit.PNG
# Hudson #

The [Hudson continuous integration system](http://hudson-ci.org/) can show Cloud information as part of its [FindBugs analysis report](http://wiki.hudson-ci.org/display/HUDSON/FindBugs+Plugin).

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/hudson-menu.PNG

Just add the following arguments to your `<findbugs>` call in the Ant build file that Hudson invokes:

  * `output="xml:withMessages"`
  * `cloud="edu.umd.cs.findbugs.cloud.appengine.findbugs-cloud"`
  * `jvmargs="-Dfindbugs.failOnCloudError=true"`

For example:
```
<findbugs home="."
              output="xml:withMessages"
              cloud="edu.umd.cs.findbugs.cloud.appengine.findbugs-cloud"
              jvmargs="-ea -Xmx900m -Dfindbugs.failOnCloudError=true"
              excludeFilter="findbugsExclude.xml"
              projectName="FindBugs"
              maxRank="20"
              timeout="1800000"
              outputFile="${build.dir}/findbugscheck.xml" >
```

This will insert Cloud information for each bug in the report XML file. Hudson will then take this information and show the latest Cloud info in the Details pane of the FindBugs Warnings report.

http://findbugs.googlecode.com/svn/wiki/cloud-tutorial/hudson.PNG