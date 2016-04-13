# For 2.0 release #

Misc:
  * ~~Survey (Bill)~~
  * Documentation
    * Cloud (Keith)
      * ~~User guide to using the plugin~~ - FindBugsCloudTutorial
      * ~~Setting up your own local cloud~~ - [SettingUpACloudServer](SettingUpACloudServer.md)
      * ~~Detector plugin text+video tutorial~~ - DetectorPluginTutorial
    * plugins (Bill)
    * bug rank (Bill)
  * ~~Bug filing~~
  * ~~Unify cloud UI & terminology across platforms~~
  * Phone home
    * Usage & feature stats
      * ~~basic stats reporting~~
      * ~~stats collection servlet~~
      * ~~enable/disable on command line - env var (Keith)~~
      * ~~plugin can disable/redirect all tracking~~
      * ~~Do Not Track plugin~~
    * Plugin update check
      * ~~findbugs -version~~
      * ~~GUI~~
      * ~~Server side~~
    * HTTPS? (Bill - survey question)
  * ~~Merge CloudDetails and Cloud~~
  * ~~Two rows of checkboxes in plugin settings UI~~
  * Final testing - use FindBugs a lot
  * ~~Fix local server tests~~
  * ~~Update FindBugsCloudServer.zip on Google Code downloads~~

Hudson:

IntelliJ:

Cloud Server:

Misc:
  * ~~Cloud selection dialog (on load, on comment)~~

# Later #

  * Continuous builds for opensource projects?
  * Stack traces / analysis errors
  * Project-to-package mapping
  * getBugIsUnassigned and getWillNotBeFixed
  * Launch FindBugs - generate jnlp
  * Continuous builds for Community Review projects

# Done #

Hudson:
  * ~~Show comments in iframe~~
  * ~~Automatic upload of new issues~~

IntelliJ:
  * ~~Show all annotations~~
  * ~~Group by rank~~
  * ~~Load plugins~~
  * ~~Clean up configuration UI~~
  * ~~Clean up comments UI~~
  * ~~Show bug annotations~~

Cloud Server:
  * ~~Optimize get-recent-evals query~~

Misc:
  * ~~Send client ID (eclipse, FB GUI, textui, etc) and version to cloud~~
  * ~~Retry cloud requests~~
  * ~~Local cloud tutorial~~ - [SettingUpACloudServer](SettingUpACloudServer.md)
  * ~~Prevent uploading sensitive code~~ (not necessary)
  * ~~Bug filing component selector (JIRA)~~
  * ~~Move web cloud client into main codebase~~
  * ~~Convert bug filing stuff to separate plug-ins~~
  * ~~Sort out isInCloud issues on Ant/Hudson~~

# Progress #

| **Feature** | **Progress** |
|:------------|:-------------|
| Check issues against DB | Done         |
| Upload issues | Done         |
| Upload issues in background | Done         |
| Show progress during cloud operations | Done         |
| Check for updated evaluations | Done         |
| Submit evaluation | Done         |
| Store session ID locally | Done         |
| Source links | Done         |
| Check issues in background | Done         |
| Upload issues in background | Done         |
| Check issues in parallel | Done         |
| Upload issues in parallel | Done         |
| Session expiration | Done         |
| Checkbox for storing session between launches | Done         |
| Ability to sign out | Done         |
| Multi-threaded hash check | Done         |
| Timestamps in UTC | Done         |
| Backup DB   | Done         |
| Restore DB from backup |
| File bug on Google Code | Done         |
| File bug on JIRA | Done         |
| Standalone deployment | Done         |
| Bug filing configuration in project | Done         |
| Standalone deployment w/cron jobs |
| Wait for initial sync & sync callback | Done         |
| Hudson plugin | In progress  |
| Eclipse plugin | In progress  |
| Add User column to DB | Done         |
| Add property for explicit login for command-line |
| Improve XML evals upload / sync | Done         |
| Auto sign-out | Done         |