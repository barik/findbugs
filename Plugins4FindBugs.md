# Introduction #

This documentation  describes how plugins for FindBugs 2.0 work. The plugin architecture is an extension of the 1.0 plugin architecture, but has a number of significant extensions.
  * Plugins are loaded from:
    * ~/.findbugs/plugin
    * ~/.findbugs/optionalPlugin
    * FINDBUGS\_HOME/plugin
    * FINDBUGS\_HOME/optionalPlugin
  * Plugins loaded from an optionalPlugin directory are disabled by default.
  * Individual projects or analysis runs can change which plugins are enabled.
  * On the command line or when invoked via ant, additional plugins to load can be specified by providing a path to the plugin jar file.
  * There is also a global plugin configuration. This plugin configuration can provide additional file locations from which plugins are loaded, and change which plugins are enabled/disabled by default. This global configuration is available in the FindBugs GUI, in Eclipse, and in IntelliJ. Each tool uses a distinct global configuration, which is persistented using a tool specific mechanism.
  * The XML output from an analysis records which plugins were enabled. A plugin is listed if it is enabled, or it is disabled and the plugin is enabled by default.

# Details #

The findbugs.xml file for each plugin contains several important bits of data about the plugin:
  * The plugin id
  * A version number (optional). Can be USE\_FINDBUGS\_VERSION, to indicate that the version number associated with FindBugs should be used (only intended for use with plugins packages with the FindBugs distribution). The version number is only descriptive, FindBugs does not use it, for example, to decide which of two versions of a plugin to enable.
  * an optional flag named "cannotDisable"
  * A short description and details about the plugin

Only one plugin with a specific plugin id can be loaded. If multiple plugins are found, only the first is used (e.g., if a plugin with id foo.bar is in both  ~/.findbugs/plugin and  FINDBUGS\_HOME/plugin, the version from ~/.findbugs/plugin is used. Exception: if a plugin with cannotDisable set to true exists in FINDBUGS\_HOME/plugin, that version is alway used.

The global enabled/disabled setting for a plugin can be changed using the` Plugin.setGloballyEnabled(boolean)` and queried using `Plugin.isGloballyEnabled()`.

If `cannotDisable` is set on a plugin and it is loaded from a location where plugins are normally enabled, that plugin is always enabled. If the plugin is installed in an optional plugin location, then normal methods can be used to enable/disable the plugin.

The information associated with a Project contains information about which plugins have enabled status for that plugin that is different than the globally enabled status for the plugin. This information is persisted to the XML for a project, using the plugin ids.

# Questions #