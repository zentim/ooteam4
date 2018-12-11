## Version
* [oo teamwork 1](https://github.com/zentim/ooteam4/releases/tag/v0.4.5)
* [oo teamwork 2](https://github.com/zentim/ooteam4/releases/tag/v0.4.7)


## How To Start
1. use "XAMPP" to start MySQL
2. use "HeidiSQL" to operate MySQL (password is empty)
  * network type: MySQL(TCP/IP)
  * host name/IP: 127.0.0.1
  * user: root
  * password:
  * port: 3306
3. execute SQL file (choose file "[mysql_shoppingcart.sql](https://github.com/zentim/ooteam4/blob/master/mysql_shoppingcart.sql) (only table)" or "[ooteam4_db_snapshot.sql](https://github.com/zentim/ooteam4/blob/master/ooteam4_db_snapshot.sql) (with data)")
4. use "Eclipse Jee Photon" to open this project
* install "Maven" ([How to install Maven on Windows](https://www.mkyong.com/maven/how-to-install-maven-in-windows/))
* setting system variables
  - "JAVA_HOME": "C:\Program Files\Java\jdk1.8.0_171"
  - "JUNIT_HOME": "D:\junit4"
  - "Path": add "D:\apache-maven-3.5.3\bin;"
* use "tomcat 8.5" as server
5. startup project on the server, then open in the browser :
* font-end page: [http://localhost:8080/ooteam4/](http://localhost:8080/ooteam4/)
* back-end page: [http://localhost:8080/ooteam4/admin](http://localhost:8080/ooteam4/admin)
