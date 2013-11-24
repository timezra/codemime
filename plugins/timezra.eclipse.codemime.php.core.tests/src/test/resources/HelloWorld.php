<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Hello, World!</title>
</head>
<body>
<!-- Hello, World! -->
<?php
define ( 'greeting', "Hello" );
$GLOBALS ['greetee'] = "World";
class Greeter {
  var $my_greeting;
  public function getSalutation() {
    $this->my_greeting = Greeter::composeGreeting ( greeting, $GLOBALS ['greetee'] );
    return $this->my_greeting;
  }
  static function composeGreeting($greeting, $greetee) {
    return "{$greeting}, {$greetee}!";
  }
}
/**
 * @deprecated
 * @throws Exception
 */
function getGreeter() {
  if (func_num_args () != 0) {
    throw new Exception (); // TODO: message?
  }
  return new Greeter ();
}
$salutation = getGreeter ()->getSalutation ();
$paragraph = <<<P
  <p>
    $salutation
  </p>
P;
print $paragraph
?> 
</body>
</html>