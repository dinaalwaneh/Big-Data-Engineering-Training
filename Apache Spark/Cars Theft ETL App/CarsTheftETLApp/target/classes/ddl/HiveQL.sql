
<!--create database -->
CREATE DATABASE [IF NOT EXISTS] car_sheft

<!--use database-->
use car_sheft

<!--create cars table-->
CREATE TABLE IF NOT EXISTS dina_car (
 car_brand string,
 Country_of_origin string)
 COMMENT 'Car Table'
 ROW FORMAT SERDE
   'org.apache.hadoop.hive.ql.io.orc.OrcSerde'
 STORED AS INPUTFORMAT
   'org.apache.hadoop.hive.ql.io.orc.OrcInputFormat'
 OUTPUTFORMAT
   'org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat'
 LOCATION
   's3://taks/dina/table';

<!--Load cars.csv File from HDFS to car table-->
LOAD DATA INPATH 's3://taks/dina/data.csv' INTO TABLE car;

<!--confirm data loaded successfully to car table-->
SELECT * FROM car

<!--create thefts table-->
CREATE TABLE IF NOT EXISTS dina_thefts(
 state string,
 rank int,
 model string,
 year int,
 Thefts long)
 COMMENT 'Thefts'
 ROW FORMAT SERDE
   'org.apache.hadoop.hive.ql.io.orc.OrcSerde'
 STORED AS INPUTFORMAT
   'org.apache.hadoop.hive.ql.io.orc.OrcInputFormat'
 OUTPUTFORMAT
   'org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat'
 LOCATION
   's3://taks/dina/table';

<!--Save the result dataset as a partitioned internal hive table.-->

CREATE TABLE IF NOT EXISTS dina_thefts_partitioned (
 model string,
 Country_of_origin string,
 year int,
 Thefts long)
 COMMENT 'Thefts Partitioned'
 ROW FORMAT SERDE
   'org.apache.hadoop.hive.ql.io.orc.OrcSerde'
 STORED AS INPUTFORMAT
   'org.apache.hadoop.hive.ql.io.orc.OrcInputFormat'
 OUTPUTFORMAT
   'org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat'
 LOCATION
   's3://taks/dina/table';


<!--List the most 5 states based on the number of thefted cars.-->
Select Sum(dina_thefts_partitioned.thefts) as thefts , state  from car_theft.thefts_with_origin  Group By state SORT BY s DESC LIMIT 5

