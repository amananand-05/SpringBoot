docker command

    docker pull mysql:latest
    docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=mydb -p 3306:3306 -d mysql
    docker ps
    docker exec -it mysql-container mysql -u root -p

    # if you want to do a custom mount 
    docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root \
    --mount type=bind,source=/path/to/my.cnf,target=/etc/mysql/my.cnf \
    -v /path/to/host/directory:/var/lib/mysql \
    -p 3306:3306 -d mysql:latest


    # inspect command for container
    docker inspect mysql-container
    
    # inspect command for an image
    docker inspect mysql:latest




--- 
    like this you can find content froma an command output
    =============
    docker inspect mysql-container | grep [options] "mount"
    options = -n for line numbers
    options = -c for occurance count 
    options = -i for ignaore search content case

    from a file
    =============
    grep [options] "cyx" aFileName.txt

---

    docker stop 3d8cd97ccf86
    docker stop some-mysql
    or
    docker start 3d8cd97ccf86
    docker start some-mysql

---

    # spring boot connection conf

    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/mydb
    spring.datasource.username=root
    spring.datasource.password=root123

---

    mysql reminal comnand;
    show databases;
    use db_name;
    show table;
    desc table_name;
    show create table t_orders_order_line_items_list
    
