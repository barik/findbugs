The FindBugs Cloud plugin update mechanism, when queried by a user's copy of FindBugs, cross-references the user's installed plugins with a list of plugin releases stored on the Cloud server itself. Using "channels" and release dates, the Cloud server sends the client a list of relevant updates that the user may wish to get.

# The plugin release database #

All changes to the plugin version database can be made easily through the App Engine Data Viewer interface.

Simply open the App Engine dashboard, open the Data Viewer, and select AppEngineDbPluginUpdateXml.

(Or click this link: [AppEngineDbPluginUpdateXml](https://appengine.google.com/datastore/explorer?&app_id=findbugs-cloud&kind=AppEngineDbPluginUpdateXml))

NOTE: Ignore the entries that have a bunch of XML in the `text` column; these are present for legacy reasons. Each other entry represents one released version of one plugin.

## Adding a new plugin ##

Adding a new plugin is simple - just create an entry for the latest release of the plugin and the Cloud server will handle the rest.

## Adding a new release ##

Simply click the **Create** tab at the top of the Data Viewer. Leave _Namespace_ blank, and fill in:
  * _date_ – right now, not the release date
  * _pluginId_ – the unique ID for your plugin, like `edu.cmu.cs.findbugs.core`
  * _releaseDate_ – users will be encouraged to upgrade to the version with the latest release date
  * _version_ – the version number
  * _channel_ – can be anything – special channels are `dev`, `stable`, `rc`, `beta`
  * _message_ – the message users will see if they need to upgrade
  * _url_ – a web address that users can click on to learn more about the release

You should leave _text_ and _user_ blank.

# Channels #

Each release has a _channel_ field which indicates what type of release it is. Stable releases like 2.0.0 and 2.0.1 are called `stable`; beta releases are called `beta`; alpha releases are called `alpha`; RC's are called `rc`; and dev (SVN) versions are called `dev`.

These channel tags are "special" – but actually any string can be used as a channel. So, for example, users on the `new-gui-branch` channel would only see updates on that channel, and no other updates.

## Channel Flowchart ##

When a user performs an update check, they will receive update notifications for the channel they are currently using. However, they will also see updates for channels which are more stable than their channel.

Thus, RC users will be notified of new stable versions. Beta users will be notified of new RC's and stable versions. Alpha users will see betas, RC's, and stable releases. And finally, dev users will see alphas, betas, RC's, and stable releases.

There is no "flow" between custom channels like `my-custom-channel`.

| **Users with this:** | **Will see updates to:** |
|:---------------------|:-------------------------|
| `dev`                | `dev`, `alpha`, `beta`, `rc`, `stable` |
| `alpha`              | `alpha`, `beta`, `rc`, `stable` |
| `beta`               | `beta`, `rc`, `stable`   |
| `rc`                 | `rc`, `stable`           |
| `stable`             | `stable`                 |
| `my-custom-channel`  | `my-custom-channel`      |