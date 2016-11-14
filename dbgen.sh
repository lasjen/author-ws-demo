cd src/main/resources/dbuser/
sql -s system/oracle@//localhost:1502/orcl @drop_dev_users.sql 12 DEV
sql -s system/oracle@//localhost:1502/orcl @create_dev_users.sql 12 DEV
cd $OLDPWD
