@echo off
rem somedir must be set to the full path to the actual jmol.jar file
rem java.exe must be on the path (probably in C:\Program Files\Java\jre1.6.0\bin, or the like)
java -Xmx512m -jar Jmol.jar %1 %2 %3 %4 %5 %6 %7 %8 %9
