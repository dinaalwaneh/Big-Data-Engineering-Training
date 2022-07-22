
<!--create database -->
CREATE DATABASE [IF NOT EXISTS] car_sheft

<!--use database-->
use car_sheft

<!--create cars table-->
CREATE TABLE IF NOT EXISTS car (
 id long,
 car_brand string,
 Country_of_origin string)
 COMMENT 'Car Table'
 ROW FORMAT DELIMITED
 FIELDS TERMINATED BY ',';

<!--Create an HDFS directory-->
hdfs dfs -mkdir warehouse

<!-- Import cars.csv file into HDFS-->
hdfs dfs -put [original-file-location] [hdfs-directory-name]

<!--verify that the file is in the HDFS folder-->
hdfs dfs -ls warehouse
