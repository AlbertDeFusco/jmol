<?php



# Bob Hanson hansonr@stolaf.edu and Integrated Graphics
# 
# 9/19/2007
#
# 

$VERS = 1.1;

define('PEAR_FOLDER',$_SERVER['DOCUMENT_ROOT'].'/PEAR/');
require_once PEAR_FOLDER.'Mail_Mime/mime.php';


  $mime = null;
  
  $to = $_REQUEST[whoto]; 
  $from = $_REQUEST[whofrom];
  $subject = $_REQUEST[subject];
  $text = $_REQUEST[text]."\n\n";
  $crlf = "\r\n";
  $mime = new Mail_mime($crlf);
  $hdrs = Array('From'=>$from,'Subject'=>$subject);
  $mime->setTXTBody($text);
  $htmlData = "mydata"
  $mime->addAttachment($htmlData, 'text/html', $fname, false);

  $body = $mime->get();
  $hdrs = $mime->headers($hdrs);
  $str = "";
  foreach($hdrs as $key=>$value) {
    if($key !== "Subject")
      $str .=$key.":".$value."\r\n";
  }
  mail($to, $hdrs[Subject], $body, $str);
	
?>

message sent.
