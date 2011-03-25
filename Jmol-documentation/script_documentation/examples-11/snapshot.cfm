<cfset stateInfo = form.theScript>

<html>
<head>
<script type="text/javascript" src="Jmol.js"></script>
<script type="text/javascript" src="snap.js"></script>
</head>
<body>
<table><tr><td valign=top>
<script>
jmolApplet(600,'set echo top left;echo working;script "javascript:getScript()";echo done')
</script>
</td><td>
This page has been sent to you as a demonstration of a new way to 
work with the <a href=http://www.stolaf.edu/academics/chemapps/jmol>Jmol</a> applet. 
<br /><br />
Shown below is a script that describes the state of the applet when you 
last clicked "live snapshot".
<br /><br />
<a href="javascript:randomMoveTo()" title="randomMoveTo()">random moveTo</a>
<a href="javascript:getState()" title="getState()">get the state</a>
<a href="javascript:loadModel()" title="loadModel()">run the script</a>
<a href="javascript:void(setTimeout('createSnapshot()',100))" title="createSnapshot()">live snapshot</a>
<a href="javascript:sendEmail()" title="sendEmail()">email</a>
<br>
<table id=border1 cellpadding=2><tr><td>
<form id=theForm method=post action=http://www.stolaf.edu/academics/chemapps/jmol/docs/examples-11/snapshot.cfm target=_blank>
<textarea rows=25 cols=40 name=theScript id=theScript wrap=off>
<cfoutput>#stateInfo#</cfoutput>
</textarea>
</form>
</td></tr></table>
</td></tr></table>
</body>
</html>