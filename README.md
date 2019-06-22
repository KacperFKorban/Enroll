# Enroll

Postres installation(Ubuntu):
1) sudo apt-get update
2) sudo apt-get install postgresql postgresql-contrib

Setting postgres password:
1) sudo -u postgres psql
2) \password postgres
-- type password
-- recommended: 123
3) \q

Install pgadmin:
1) sudo apt install pgadmin3

Setting up db server:
1) open pgadmin3
2) create a new server
-- owner -> postgres
-- recommended name localhost
3) create new db
-- owner -> postgres
-- recommended name enroll

Running:
1) git clone https://github.com/KacperFKorban/Enroll
2) cd Enroll
3) in conf/application.conf fill in propertiies url, user and password if using other than recommended
4) go to app/tables/DBConfig.scala and run main to create schemas and populate exampke courses
4) sbt run
