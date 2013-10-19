codemime [![Build Status](https://travis-ci.org/timezra/codemime.png)](https://travis-ci.org/timezra/codemime)
====================================================

CodeMime is a set of Eclipse plug-ins for copying structured/syntax-colored text (e.g., Java code) to other formats (e.g., HTML), and making that text available on the system clipboard.

CodeMime is a fork of the cs4eclipse project (http://sourceforge.net/projects/cs4eclipse) with enhancements (e.g., automated testing, re-factorings of duplicated code, upgrades based on more recent eclipse standards, additional plug-ins).

The new copy commands are available in both file navigator menus (for copying entire files), and in specialized editors (for copying blocks of text). The popup menu contributions appear near the standard edit commands (Copy/Cut/Paste) in a new "Copy As" submenu.

Currently, only copying to the following formats is supported:
 - HTML

Copying from the following types is supported:
 - Ant
 - CSS
 - DTD
 - HTML
 - XML
 - Properties
 - Java
 - Groovy
 - JavaScript
 - Maven

### Examples: ###

As an example, the following code can be copied from the Eclipse Java Editor:

<code lang="java">
public interface HtmlGenerator {
&nbsp;&nbsp;String generate(final IDocument document, final IRegion region);
&nbsp;&nbsp;void setFont(final FontData font);
&nbsp;&nbsp;void setWhitespaceAllowed(final boolean b);
&nbsp;&nbsp;void setTabWidth(final int width);
}
</code>

CodeMime will generate this HTML and make it available to the system clipboard:

<code lang="html">
<span style="font-family: monaco; font-size: 11pt;"><span style="font-weight: bold; color: rgb(127,0,85);">public</span><span style="color: rgb(0,0,0);">&nbsp;</span><span style="font-weight: bold; color: rgb(127,0,85);">interface</span><span style="color: rgb(0,0,0);">&nbsp;HtmlGenerator&nbsp;{
<br />
&nbsp;&nbsp;&nbsp;&nbsp;String&nbsp;generate(</span><span style="font-weight: bold; color: rgb(127,0,85);">final</span><span style="color: rgb(0,0,0);">&nbsp;IDocument&nbsp;document,&nbsp;</span><span style="font-weight: bold; color: rgb(127,0,85);">final</span><span style="color: rgb(0,0,0);">&nbsp;IRegion&nbsp;region);
<br />
&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="font-weight: bold; color: rgb(127,0,85);">void</span><span style="color: rgb(0,0,0);">&nbsp;setFont(</span><span style="font-weight: bold; color: rgb(127,0,85);">final</span><span style="color: rgb(0,0,0);">&nbsp;FontData&nbsp;font);
<br />
&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="font-weight: bold; color: rgb(127,0,85);">void</span><span style="color: rgb(0,0,0);">&nbsp;setWhitespaceAllowed(</span><span style="font-weight: bold; color: rgb(127,0,85);">final</span><span style="color: rgb(0,0,0);">&nbsp;</span><span style="font-weight: bold; color: rgb(127,0,85);">boolean</span><span style="color: rgb(0,0,0);">&nbsp;b);
<br />
&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="font-weight: bold; color: rgb(127,0,85);">void</span><span style="color: rgb(0,0,0);">&nbsp;setTabWidth(</span><span style="font-weight: bold; color: rgb(127,0,85);">final</span><span style="color: rgb(0,0,0);">&nbsp;</span><span style="font-weight: bold; color: rgb(127,0,85);">int</span><span style="color: rgb(0,0,0);">&nbsp;width);
<br />
}</span></span>
</code>

CodeMime is based on this maven archetype: http://github.com/timezra/tycho_new_plugin_project

CodeMime can be built from the root pom with standard Maven commands, e.g.,

    $ mvn clean verify

If you would like to build an update site for CodeMime with signed jars use the following commands (the first to generate a self-signed cert with a 1-year validity if you do not have a cert from a CA; the second to activate the maven profile to sign the jars that are packaged in the update site):

    $ keytool -genkey -alias _keystore_alias_ -keystore /path/to/keystore -validity 365
    $ mvn -Psign -Djarsigner.keystore=/path/to/keystore -Djarsigner.storepass=_keystore_password_ -Djarsigner.alias=_keystore_alias_ clean verify

When importing these projects into Eclipse, you should be able to import them as Existing Maven Projects (with the m2e plugin). This should create the appropriate .classpath and .project files.
You may need to delete the timezra.eclipse.codemime tests fragments from the workspace (but not from disk) and then re-import them as a General -> Existing Project after the .classpath and .project files have been created. This might have something to do with their being fragments.

CodeMime can also be installed from the Eclipse Marketplace or from an update site (http://timezra.github.com/codemime)
