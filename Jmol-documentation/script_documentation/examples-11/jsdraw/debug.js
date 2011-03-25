slast=null
depth=0
depthmax=2
nmax=300
nlines=0
function showall(objName,snot){
 if(nlines>nmax || depth==depthmax)return ""
 nlines++
 depth++
 var s = ""
 var sList = ""
 var obj=eval(objName)
 for(var i in obj){
 sList += i + "\n"
	var st=obj[i]+"\n"
	if(!snot || st.indexOf(snot)<0)s+=objName+(isNaN(i)?"."+i:"["+i+"]")+" = "+st.replace(/</g,"&lt;")
	if(i!=0 && st.indexOf("[")>=0){
		if(isNaN(i)){if(i!="all" 
			&& i!="document"
			&& i.indexOf("top")<0
			&& i.indexOf("arent")<0
			&& i.indexOf("self")<0
			&& i.indexOf("ibling")<0
			&& i.indexOf("window")<0
			&& i.indexOf("frame")<0
			&& i.indexOf("next")<0
			&& i.indexOf("previous")<0
			&& i.indexOf("below")<0
			&& i.indexOf("above")<0

		){
			s+=showall(objName+"."+i,snot)
		}}else{if(objName.indexOf(".all")<0){
			s+=showall(objName+"["+i+"]",snot)
		}}
	}
 }
 depth--
 return sList
// return s
}

function showobj(s){
 isnn4 = true
 depthmax=2
 if(s!=null)slast=s
 slast=(slast==null?(isnn4?"window,document":"document.all[0],document.all"):slast)
 if(s==null)s=prompt("What do you want to see?",slast)
 if(s==null)return
 slast=s
 var sm=""+Math.random()
 sm=sm.substring(3,10)
 var w=open("","GR_"+sm,"scrollbar,scrollbars,height=300,width=600,left=20,top=20")
 var d=w.document
 d.open()
 d.write("<title>"+s+"</title>")
 var A=s.split(",")
 for(var j=0;j<A.length;j++){
  s=showall(A[j],"function")
  var S=s.split("\n")
  d.write("<pre>")
  for(var i=0;i<S.length;i++)d.writeln(S[i])
  d.write("</pre>")
 }
 d.close()
}

