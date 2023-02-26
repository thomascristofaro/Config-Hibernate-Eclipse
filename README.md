# Configure Hibernate app in Eclipse
## Prerequisites
- Last JDK: https://www.oracle.com/java/technologies/downloads

- Eclipse: https://www.eclipse.org/downloads/
  using the option "Eclipse IDE for Enterprise Java and Web Developers" during the installation

- Hibernate installation: Help &rarr; Eclipse Marketplace &rarr; JBOSS TOOLS (Install ONLY Hibernate Tools) 

- Change Perspective: Window &rarr; Perspective &rarr; Open Perspective &rarr; Other &rarr; Hibernate

## MySQL Driver creation

Window &rarr; Preferences &rarr; Data Management &rarr; Connectivity &rarr; Driver Definitions

<img src="https://user-images.githubusercontent.com/11760847/221429388-71572919-9a9c-48cf-84e3-d4d56416467b.png" width="50%" height="50%">
<img src="https://user-images.githubusercontent.com/11760847/221429454-950cc635-d381-4dc1-b7d8-82c4c3828919.png" width="50%" height="50%">

In JAR List remove all and add the JAR that you installed with MySQL (maybe in C:\Program Files (x86)\MySQL\Connector J 8.0):
<img src="https://user-images.githubusercontent.com/11760847/221429514-078477af-3a0e-4eab-9a57-1786ee148a85.png" width="50%" height="50%">

Continue in tab Properties and then save it.

## Project creation

File &rarr; New &rarr; Project &rarr; Maven Project:

<img src="https://user-images.githubusercontent.com/11760847/221429973-769e3e4d-4ba5-46f4-82de-b2727a928d5e.png" width="50%" height="50%">
<img src="https://user-images.githubusercontent.com/11760847/221429980-0f7835f9-87a6-40b9-92ba-ecc4315974f6.png" width="50%" height="50%">

Call Group Id e Artifact Id as you prefer. Finish it and then wait until you have this folder structure

<img src="https://user-images.githubusercontent.com/11760847/221430049-eb5a5746-6132-41e2-8e45-9256a72b2edd.png" width="25%" height="25%">

Right click on JRE System Library &rarr; Properties &rarr; Change the enviroment with the JRE that you have in your computer

<img src="https://user-images.githubusercontent.com/11760847/221430141-8bcce705-c70a-4d6c-a556-40be655f3ae3.png" width="50%" height="50%">

If you click on "enviroment" button you can see with wich version is matched with the installed JRE (for me is JavaSE-18):

<img src="https://user-images.githubusercontent.com/11760847/221430351-a148b6b2-66f8-4d8a-bd6a-639070a70361.png" width="50%" height="50%">

Create two Java package in src/main/java: `com.hibernate.model` and `com.hibernate.app`

<img src="https://user-images.githubusercontent.com/11760847/221430430-7fbb2943-ac2b-4fd8-915d-59076fd9529f.png" width="50%" height="50%">

## Hibernate dependencies

Paste at the end of `pom.xml` (before `</project>`)
```
<dependencies>
  <dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.1.7.Final</version>
  </dependency>
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.32</version>
  </dependency>
</dependencies>
```
Save the file. You must see the dependences downloaded inside "maven dependencies"

## Persistance file (hibernate.cfg.xml)

## Proviamo il codice del Professore
