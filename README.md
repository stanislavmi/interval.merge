# Merge overlapping intervals
**Description**: Merge overlapping intervals with 2 solutions: Java List through Collections.sort otherwise TreeMap

**Dependencies**: Apache Maven

**Installation**: implemented in Java 11  
1. Download Apache Maven http://ftp.halifax.rwth-aachen.de/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip  
2. Extract apache-maven-3.6.3-bin.zip and set environment variables ```M2_HOME``` and ```MAVEN_HOME``` with maven path in system - [set environment variables](https://ronasoft.de/einrichtung-von-apache-maven-unter-microsoft-windows-10/)  
3. Clone repository https://github.com/stanislavmi/interval.merge and run in project folder 
```mvn clean package -f pom.xml```.  

Or clone repository in IntelliJ and run Main class or IntervalMergerTest test class.

**Test**
To run the Unit Test ```mvn clean package -f pom.xml```.

**Usage**: Jar execute in target folder  
* ```java -jar interval.merge-1.0-SNAPSHOT-jar-with-dependencies.jar <number parameter>```
  - ```number parameter - number of random generated intervals or without parameters to use default test values (see Main class)```
  
**Output screenshot:**
  ![](usage.png)

**Fragen**
1. Laufzeit:  
Lösung 1 benutzt eine Liste mit der Methode Collections.sort (merge sort) und hat die Komplexität für Collections.sort ```O(n*log(n))``` und anschlißend für die FOR Schleife - ```O(n)```, also ```O(n*log(n))+O(n)```  
Lösung 2 funktioniert mit TreeMap (red-black tree) und hat die Komplexität beim Einfügen in TreeMap ```O(log(n)) + O(n)``` (inklusive FOR Schleife).

TreeMap basiert auf red-black tree. Es werden Duplikate nicht gespeichert und die Schlüssel werden beim Insert bereits aufsteigend sortiert. Somit wird die Zeit für Sortierung gespart.
Aus diesem Grund ist die Laufzeit zweiter Lösung optimaler.

2. Robustheit und Speicherverbrauch:  
Die Robustheit wird durch typisierte Eingangsparameter und optimaler Datenverarbeitung gewährleistet.
Der Speicherverbrauch hängt von der Größe der Eingabe und ausgewählter Datenstruktur ab. 
Die Speicher- und Prozessorauslastung bei 10 Millionen Intervallen ist auf folgendem Beispiel dargestellt.

4. Test Ergebnisse  

| Interval Eingabe | List Lösung Dauer| TreeMap Lösung Dauer|
| --- | ------------- | ------------- |
| aus dem Beispiel| 35ms  | 2ms  |
| 1000 zufällig generierte Intervalle | 38ms  | 2ms  |
| 100.000 zufällig generierte Intervalle | 145ms  | 117ms  |
| 10.000.000 zufällig generierte Intervalle | 599207ms  | 7277ms  |

