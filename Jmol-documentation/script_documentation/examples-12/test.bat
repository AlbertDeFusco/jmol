copy c:\jmol-dev\workspace\jmol\build\*.jar .
dir *.jar

if %1x == x goto exit

start %1

:exit