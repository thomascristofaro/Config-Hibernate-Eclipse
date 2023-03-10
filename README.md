# Configure Hibernate in Eclipse
## Index
- [Prerequisites](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#prerequisites)
- [MySQL Driver creation](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#mysql-driver-creation)
- [Project creation](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#project-creation)
- [Hibernate dependencies](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#hibernate-dependencies)
- [Persistance file](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#persistance-file-hibernatecfgxml)
- [Try code](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#try-code)
- [Database Reverse Engineering](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#database-reverse-engineering)

## Prerequisites
- MySQL: https://dev.mysql.com/downloads/installer/ using all default options

- Last JDK: https://www.oracle.com/java/technologies/downloads

- Eclipse: https://www.eclipse.org/downloads/
  using the option "Eclipse IDE for Enterprise Java and Web Developers" during the installation

- Hibernate installation: Help &rarr; Eclipse Marketplace &rarr; JBOSS TOOLS (Install ONLY Hibernate Tools) 

- Change Perspective: Window &rarr; Perspective &rarr; Open Perspective &rarr; Other &rarr; Hibernate

## MySQL Driver creation

This driver is a prerequisite for the creation of the [Persistance file](https://github.com/thomascristofaro/Config-Hibernate-Eclipse#persistance-file-hibernatecfgxml).

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

Create two Java package in src/main/java: `com.hibernate.model` (for table-classes) and `com.hibernate.app` (for manager-classes)

<img src="https://user-images.githubusercontent.com/11760847/221430430-7fbb2943-ac2b-4fd8-915d-59076fd9529f.png" width="50%" height="50%">

## Hibernate dependencies

`pom.xml` is the file of the project configuration of Maven.

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

This is a file that allow hibernate to connect with the database.

File &rarr; New &rarr; Hibernate configuration file:

<img src="https://user-images.githubusercontent.com/11760847/221440499-902582e5-4448-4a67-b249-00b1500b0908.png" width="35%" height="35%">

Next &rarr; "Get values from connection" &rarr; if you don't have a profile &rarr; New &rarr; MySQL &rarr; Driver created before
Test the connection and go next:

<img src="https://user-images.githubusercontent.com/11760847/221440683-a19fd72a-cf6a-45fd-b827-4cbb14d6ba6f.png" width="50%" height="50%">

Then change in Annotations and finish the setup:

<img src="https://user-images.githubusercontent.com/11760847/221440691-eda9cdd3-ef19-4204-bdcc-b524b418b513.png" width="50%" height="50%">

Insert inside `Hibernate.cfg.xml` and save it:
```
<property name="hibernate.hbm2ddl.auto">create</property>
<property name="hibernate.show_sql">true</property>
```
If is all ok, we must see the database connection with tables inside the tab "Hibernate Configurations":

<img src="https://user-images.githubusercontent.com/11760847/221441164-e451499a-fe0e-445f-bfd2-7c619bccd0a3.png" width="35%" height="35%">

## Try code

Now we can try some code for testing connection.

Database table:
```
CREATE TABLE `courses` (
  `COURSE_ID` bigint NOT NULL,
  `COURSE_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`COURSE_ID`)
);
```

Create and copy/paste these 3 java classes: 
- [Course.java](./Course.java) in `com.hibernate.model` &rarr; it contains the definition of the table
- [CourseManager.java](./CourseManager.java) in `com.hibernate.app` &rarr; it contains CRUD operations on the table 
- [App.java](./App.java) in `com.hibernate.app` &rarr; it contains the main

Make attention at packages, jakarta importing and CFGFILE path.

Insert in `hibernate.cfg.xml` the connection between hibernate and java classes: 

`<mapping class="com.hibernate.model.Course" />`

Right click on CourseManager &rarr; Run As &rarr; Java Application &rarr; you must see red (INFO) and white (SQL) code in output terminal.

Check if there is some data inside `courses` table.

## Database Reverse Engineering

With this setup, hibernate can create java class from database tables.

Run &rarr; Hibernate Code Generation &rarr; Hibernate Code Generation Configurations &rarr; New Configuration:

<img src="https://user-images.githubusercontent.com/11760847/221443894-64d16356-bffe-4f57-8ce8-d668580b06ae.png" width="60%" height="60%">

<img src="https://user-images.githubusercontent.com/11760847/221443997-3c39b637-3a6c-43c4-ac2b-e02f6c5e44b1.png" width="60%" height="60%">

In reveng.xml click on Setup &rarr; Create New:

<img src="https://user-images.githubusercontent.com/11760847/221444110-f0b6a3d9-cd8b-4786-8881-3a08ee97af07.png" width="50%" height="50%">

Then next, include just a table for testing creation and finish.

<img src="https://user-images.githubusercontent.com/11760847/221444167-142c222b-c678-4949-8fc7-05789412bc63.png" width="50%" height="50%">

Return in Hibernate Code Generation Configurations and be sure that reveng.xml is compiled and then Run the Code Generation:

<img src="https://user-images.githubusercontent.com/11760847/221444280-0d9c0f8d-5ae6-451d-89f7-ccbb510c9deb.png" width="60%" height="60%">

The generated files will be created in the package previously set:

<img src="https://user-images.githubusercontent.com/11760847/221444676-9dcf451e-10e6-4610-9901-4b5719f10abb.png" width="25%" height="25%">

Unfortunately the class generated is without @annotations:

<img src="https://user-images.githubusercontent.com/11760847/221445121-666f0495-1957-4f2c-a7ee-eef7539a3cc6.png" width="50%" height="50%">
