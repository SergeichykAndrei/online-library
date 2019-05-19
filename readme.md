Online-library
About
The online-library application is a web application that provides the user with a list of literature, which can be searched for according to various criteria, and also provides an opportunity to take the book of interest for use. The application also allows you to administer the online-library.
Tools/libraries
The application has a modular structure (dao, services, web). 
Dao: 
As a DBMS to use PostgreSQL; implemented optimistic locking entity; provides full transactionality by integrating Hibernate and Spring (@Transactional); to create queries using Spring-data; for reference entities implemented Spring-caching; implemented queries with limited selection (pagination). 
Services:
Logging is done using aspects of Spring (AOP); encrypting the user's password and storing it in encrypted form. 
Web:
For the implementation of the presentation layer uses a bunch of Spring MVC + Thymeleaf; editing entity implemented on the page, given the optimistic lock; implemented the user registration page using Spring Security; implemented the internationalization messages; validation is used when filling in forms on pages.

Installation guideline (for windows)
- to install the online-library application, you need to download it from the remote repository via a link using a third-party application (Git Bash...) or download ZIP-archive, in the second case you need to unpack the archive after downloading; 
- you need to install apache-maven (link: https://maven.apache.org/); 
- to download online-library, go to the root directory of the project from the command line (.../online-library) and enter the command (mvn clean package);
- after you need to move the file web/target/online-library.war in pre-downloaded (link: https://tomcat.apache.org/download-80.cgi) Apache-tomcat servlet container in the webapps directory; -then you need to load the server (bin -> startup);
- for the correct operation of the application, you must download the database script (PostgreSQL) located also in the remote repository;
- you can also run the application in any development environment.

email
sergeichykandrei@gmail.com
