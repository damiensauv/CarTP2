# CarTP2

[Sauvalle - Parmentier]

Tout est present

pour run : 

1) Installer le projet Maven : mvn install

2) Build le projet : mvn package

3) Lancer la Version Sequentiel : 
mvn exec:java@Sequentiel -Dexec.args="FileName"

4) Lancer la Version MultiThread :
mvn exec:java@ThreadCpt -Dexec.args="NbThread FileName"

5) Lancer le Serveur :
mvn exec:java@Server -Dexec.args="NbThread"

6) Lanver Un Client (Le serveur est en localhost) : 
mvn exec:java@Client -Dexec.args="FileName"