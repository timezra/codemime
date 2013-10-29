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
 - Groovy
 - HTML
 - Java
 - JavaScript
 - JSP
 - Maven
 - Properties
 - QVT
 - Scala
 - XML

### Examples: ###

As an example, the following code can be copied from the Eclipse Java Editor:

``` java
public interface CodeMimeGenerator {
	String generate(final IDocument document, final IRegion region);
	void setFont(final FontData font);
	void setWhitespaceAllowed(final boolean b);
	void setTabWidth(final int width);
}
```

CodeMime will generate this HTML and make it available to the system clipboard:

``` html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodeMimeGenerator.java</title>
<style type="text/css">
div.cm_source {
  overflow: auto;
  padding: 1em;
  background-color: white;
  border-style: dashed;
  border-width: thin;
  font-family: monospace;
  font-size: 10pt;
}
span.cm_b_n_n_7F0055 {
  font-weight: bold;
  color: #7F0055;
}
span.cm_n_n_n_0 {
  color: #000000;
}
</style>
</head>
<body>
<div class="cm_source">
<span class="cm_b_n_n_7F0055">public</span><span class="cm_n_n_n_0">&nbsp;</span><span class="cm_b_n_n_7F0055">interface</span><span class="cm_n_n_n_0">&nbsp;CodeMimeGenerator&nbsp;{<br />
  &nbsp;&nbsp;&nbsp;&nbsp;String&nbsp;generate(</span><span class="cm_b_n_n_7F0055">final</span><span class="cm_n_n_n_0">&nbsp;IDocument&nbsp;document,&nbsp;</span><span class="cm_b_n_n_7F0055">final</span><span class="cm_n_n_n_0">&nbsp;IRegion&nbsp;region);<br />
  &nbsp;&nbsp;&nbsp;&nbsp;</span><span class="cm_b_n_n_7F0055">void</span><span class="cm_n_n_n_0">&nbsp;setFont(</span><span class="cm_b_n_n_7F0055">final</span><span class="cm_n_n_n_0">&nbsp;FontData&nbsp;font);<br />
  &nbsp;&nbsp;&nbsp;&nbsp;</span><span class="cm_b_n_n_7F0055">void</span><span class="cm_n_n_n_0">&nbsp;setWhitespaceAllowed(</span><span class="cm_b_n_n_7F0055">final</span><span class="cm_n_n_n_0">&nbsp;</span><span class="cm_b_n_n_7F0055">boolean</span><span class="cm_n_n_n_0">&nbsp;b);<br />
  &nbsp;&nbsp;&nbsp;&nbsp;</span><span class="cm_b_n_n_7F0055">void</span><span class="cm_n_n_n_0">&nbsp;setTabWidth(</span><span class="cm_b_n_n_7F0055">final</span><span class="cm_n_n_n_0">&nbsp;</span><span class="cm_b_n_n_7F0055">int</span><span class="cm_n_n_n_0">&nbsp;width);<br />
  }</span>
</div>
</body>
</html>
```

CodeMime is based on this maven archetype: http://github.com/timezra/tycho_new_plugin_project

CodeMime can be built from the root pom with standard Maven commands, e.g.,

    $ mvn verify

If you would like to build an update site for CodeMime with signed jars use the following commands (the first to generate a self-signed cert with a 1-year validity if you do not have a cert from a CA; the second to activate the maven profile to sign the jars that are packaged in the update site):

    $ keytool -genkey -alias _keystore_alias_ -keystore /path/to/keystore -validity 365
    $ mvn -Psign -Djarsigner.keystore=/path/to/keystore -Djarsigner.storepass=_keystore_password_ -Djarsigner.alias=_keystore_alias_ clean verify

When importing these projects into Eclipse, you should be able to import them as Existing Maven Projects (with the m2e plugin). This should create the appropriate .classpath and .project files.

CodeMime can also be installed from the Eclipse Marketplace or from an update site (http://timezra.github.com/codemime)
