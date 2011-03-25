This project contains tools related to Jmol working with Folding@Home
http://folding.stanford.edu

The tools are described below.
To run them, you need to have Java installed on your computer.

==> CheckXYZ <==
This tools checks if you have "current.xyz" files for projects that are missing on http://www.jmol.org/fah.
To run it, either :
- Double-click on JmolFah.jar
- Execute the command java -jar JmolFah.jar

==> ProjectInformation <==
This tools checks the project list in Jmol-web.
It is used to update Jmol-web/source/doc/fah/fah-projects.xml.
It compares it with :
- Stanford project list (psummary.html)
- EM 3 project list (http://www.em-dc.com)
- QD project list (http://boston.quik.com/rph/fah.html)
- FCI project list (http://fci.fatalerrorgroup.com/xml-data/project-summary.xml)

==> Contribution <==
A small tool to gather detailed informations on user contributions
