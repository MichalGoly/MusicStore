# DESCRIPTION
MusicStore is an e-commerce application written in Java using Servlets and JSPs.
It complies fully with the MVC design pattern, as Servlets are used as controllers
and JSPs take care of the presentation (view). The problem domain (model) is
described by JavaBean entities, which are mapped on tables in a MySQL relational
database, using JPA provided by the EclipseLink.

# WEB APP IN ACTION
The application has been deployed as a WAR to a Jetty container and you can
access it by visiting [http://apps.michalgoly.com/MusicStore/](http://apps.michalgoly.com/MusicStore/)

# INSTALLATION
Instructions to run the web application locally.

### PREREQUISITES
* Netbeans IDE (this project uses an Ant build script)
* Web server and Servlet container (e.g. Tomcat or Jetty)
* MySQL database

---

1. Clone the repository to your machine.
`git clone https://github.com/MichalGoly/MusicStore.git`

2. Run the **initialize.sql** script from the /db directory in order to create
and populate your MySQL database with the initial data. You could for instance
type:

`mysql -u username -p < initialize.sql`

3. Open the **/scr/conf/persistence.xml** and configure the jdbc driver which
will connect the application to your MySQL database. You should change the value
attributes of those two xml tags.

```xml
<property name="javax.persistence.jdbc.user" value="user"/>
<property name="javax.persistence.jdbc.password" value="password"/>
```
4. (Optional) Open the **/src/java/com/michalgoly/util/MailUtil.java** and edit
the instance variables on top of the class, in order to enable the web application
to send confirmation emails to customers after each purchase. You will also most
likely have to enable less secure apps to access your gmail account for this to
work [here](https://support.google.com/accounts/answer/6010255?hl=en).  

5. (Optional) Open the **web/WEB-INF/web.xml** and uncomment the code to restrict
access to the admin panel. If you are using Tomcat as your Servlet container, you
will have to add the **administrator** role into one of your users in the
**$CATALINA_HOME/conf/tomcat-users.xml**

# CREDITS

* [Murach’s Java Servlets and JSP (3rd Edition)](https://www.murach.com/shop/murach-s-java-servlets-and-jsp-3rd-edition-detail) -
an amazing book which I have used to self teach myself the subject. Contents of the
MusicStore (album titles, album covers etc) have been based on the web app presented
at the end of the book. I have however, developed my app from scratch and looked
into the source code of the book app, to make sure I follow best practices.
* [EclipseLink](http://www.eclipse.org/eclipselink/#jpa)
* [MySQL](https://www.mysql.com/)
* [JSTL 1.2.1](https://jstl.java.net/)
* [Bootstrap 3](http://getbootstrap.com/)
* [JavaMail](https://java.net/projects/javamail/pages/Home)
