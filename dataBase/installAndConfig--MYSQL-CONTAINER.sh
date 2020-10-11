sudo docker pull mysql
sudo docker run --name DockerSQLDB -e MYSQL_ROOT_PASSWORD= -p 3306:3306 -d mysql