docker rm -f mariadb
docker run -d --rm \
    --name mariadb \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=password \
    mariadb
