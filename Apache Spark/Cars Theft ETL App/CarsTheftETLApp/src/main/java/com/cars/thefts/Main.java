package com.cars.thefts;

import Files.IFile;
import Files.Imp.CSVFile;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class Main {

    public static void main(String[] args) {

        Logger.getLogger("org").setLevel(Level.OFF);

        SparkSession spark = SparkSession
            .builder()
            .appName("Application Name")
            .master("local[*]")
            .getOrCreate();

        /*
        SparkSession spark = SparkSession
                .builder()
                .appName("Application Name")
                .enableHiveSupport()
                .getOrCreate();
        */

        IFile csvFile = new CSVFile();

        /*.............................Read cars.csv file.............................*/

        final String CARS_FILE_PATH = "src/main/resources/Files/cars.csv"; //s3://Thtfts cars/cars.csv

        Dataset<Row> cars = csvFile.ReadFile(spark,CARS_FILE_PATH);
        cars = cars.withColumnRenamed("Car Brand","car_brand");
        cars = cars.withColumnRenamed("Country of Origin","country_of_origin");

        cars.cache();
        System.out.println("..........Cars Dataset..........\n");
        cars.show(5);
        /*
        spark.sql("CREATE DATABASE [IF NOT EXISTS] car_sheft");
        spark.sql("use car_sheft");
        spark.sql("CREATE TABLE IF NOT EXISTS dina_car (
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
               's3://taks/dina/table';");
         spark.sql("LOAD DATA INPATH 's3://Thtfts cars/cars.csv' INTO TABLE car_sheft.dina_car;");
         cars.write().mode("overwrite").saveAsTable("car_theft.dina_car");
        */
        /*.............................Read 2015_State_Top10Report_wTotalThefts.csv file.............................*/

        final String THEFTS_FILE_PATH = "src/main/resources/Files/2015_State_Top10Report_wTotalThefts.csv"; //s3://Thtfts cars/2015_State_Top10Report_wTotalThefts.csv

        Dataset<Row> thefts = csvFile.ReadFile(spark,THEFTS_FILE_PATH);
        thefts =  thefts.withColumnRenamed("Make/Model","model");
        thefts = thefts.withColumnRenamed("Model Year","year");

        thefts.cache();
        System.out.println("\n................Thefts Dataset................\n");
        thefts.show(5);

                /*
        spark.sql("CREATE DATABASE [IF NOT EXISTS] car_sheft");
        spark.sql("use car_sheft");
        spark.sql("CREATE TABLE IF NOT EXISTS dina_thefts(
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
               's3://taks/dina/table';");
         spark.sql("LOAD DATA INPATH 's3://Thtfts cars/2015_State_Top10Report_wTotalThefts.csv' INTO TABLE car_sheft.dina_thefts;");
         thefts.write().mode("overwrite").saveAsTable("car_theft.dina_thefts");
        */
        /*....................store Dataset as the table or convert Dataset into temporary view....................*/

        cars.createOrReplaceTempView("cars");
        thefts.createOrReplaceTempView("thefts");

        /*....................Thefts with origin....................*/
        Dataset<Row> join = spark.sql("select * FROM thefts left join cars on thefts.model like concat (cars.car_brand,'%')");

        Dataset<Row> thefts_with_origin_country = join.select(join.col("state"),join.col("model"), join.col("Country_of_origin"),join.col("year"),join.col("Thefts"));
        thefts_with_origin_country = thefts_with_origin_country.na().drop();

        thefts_with_origin_country.cache();
        System.out.println("\n................Thefts with origin country Dataset................\n");
        thefts_with_origin_country.show(5);

        /*....................thefts with origin country partitioning....................*/
        Dataset<Row> thefts_with_origin_country_partioning =  thefts_with_origin_country.repartition(join.col("year"));

        thefts_with_origin_country_partioning.cache();
        System.out.println("\n................thefts with origin country partioning................\n");
        thefts_with_origin_country_partioning.show(20);

        /*.............................Read Updated - Sheet1.csv file.............................*/

        final String UPDATED_THEFTS_FILE_PATH = "src/main/resources/Files/Updated - Sheet1.csv";

        Dataset<Row> updatedThefts = csvFile.ReadFile(spark,UPDATED_THEFTS_FILE_PATH);
        updatedThefts = updatedThefts.withColumnRenamed("Make/Model","model1");
        updatedThefts = updatedThefts.withColumnRenamed("Model Year","year1");
        updatedThefts = updatedThefts.withColumnRenamed("State","State1");
        updatedThefts = updatedThefts.withColumnRenamed("Rank","Rank1");
        updatedThefts = updatedThefts.withColumnRenamed("Thefts","Thefts1");

        updatedThefts.cache();
        System.out.println("\n................Updated Thefts Dataset................\n");
        updatedThefts.show();
        // updatedThefts.write().mode("overwrite").saveAsTable("car_theft.dina_thefts");

        /*....................store Dataset as the table or convert Dataset into temporary view....................*/

        updatedThefts.createOrReplaceTempView("updated_thefts");

        /*....................left join between thefts table and updated_thefts according 3 keys from columns....................*/
        var newThefts= spark.sql("select * FROM thefts left JOIN updated_thefts ON  updated_thefts.model1 like  thefts.model And updated_thefts.State1 like thefts.State  And updated_thefts.year1 like thefts.year");
        newThefts.cache();
        /*....................store Dataset as the table or convert Dataset into temporary view....................*/

        newThefts.createOrReplaceTempView("new_thefts");
        /*....................filter data set with null values to except the updated rows....................*/
        newThefts = spark.sql("select * from new_thefts where new_thefts.state1 IS NULL");
        // newThefts = newThefts.filter(newThefts.col("state1").isNull());
        newThefts.cache();
        System.out.println("\n................merge thefts Dataset................\n");


        //Delete the extra rows to make a union between
        newThefts = newThefts.drop(newThefts.col("State1"));
        newThefts = newThefts.drop(newThefts.col("Rank1"));
        newThefts = newThefts.drop(newThefts.col("model1"));
        newThefts = newThefts.drop(newThefts.col("year1"));
        newThefts = newThefts.drop(newThefts.col("Thefts1"));

        newThefts.cache();
        /*....................store Dataset as the table or convert Dataset into temporary view....................*/
        newThefts.createOrReplaceTempView("new_thefts");
        updatedThefts.createOrReplaceTempView("updated_thefts");
        var merge_thefts =spark.sql("( select * from new_thefts ) UNION ( select * from updated_thefts)");
        //var merge_thefts = newThefts.union(updatedThefts);

        merge_thefts.cache();
        System.out.println("\n................merge thefts Dataset................\n");
        merge_thefts.show(5);
        System.out.println("\n merge thefts Dataset count = " + merge_thefts.count() + "\n");

        //store thefts_with_origin_country Dataset as the table
        thefts_with_origin_country.createOrReplaceTempView("thefts_with_origin_country");

        //store thefts_with_origin_country Dataset as the table
        merge_thefts.createOrReplaceTempView("merge_thefts");

        //Left join between merge_thefts and cars
        Dataset<Row> updateTheftsCars = spark.sql("select * FROM merge_thefts left join cars on merge_thefts.model like concat (cars.car_brand,'%')");

        Dataset<Row> updatedTheftsJoin = updateTheftsCars.select(updateTheftsCars.col("state"),updateTheftsCars.col("model"), updateTheftsCars.col("Country_of_origin"),updateTheftsCars.col("year"),updateTheftsCars.col("Thefts"));
        updatedTheftsJoin= updatedTheftsJoin.na().drop();
        updatedTheftsJoin.cache();
        System.out.println("\n................updated Thefts Join................\n");
        updatedTheftsJoin.show(5);

        /*.........Find top five countries from updated thefts with origin.........*/
        updatedTheftsJoin.createOrReplaceTempView("updatedTheftsJoin");
        var updatedTheftsWithOriginTopFiveCountries =spark.sql("Select Sum(updatedTheftsJoin.thefts) as thefts,country_of_origin from updatedTheftsJoin Group By country_of_origin SORT BY thefts DESC LIMIT 5");
        updatedTheftsWithOriginTopFiveCountries.cache();
        System.out.println("\n................updated Thefts With Origin Top Five Countries................\n");
        updatedTheftsWithOriginTopFiveCountries.show();

        //Save top five countries in csv file
        final String TOP_FIVE_COUNTRIES_FILE_PATH = "src/main/resources/Files/Top_Five_Countries";
        csvFile.WriteOnFile(updatedTheftsWithOriginTopFiveCountries,TOP_FIVE_COUNTRIES_FILE_PATH);

        //List the most 5 thefted models in U.S :
        var updatedTheftsWithOriginTopFiveTheftsModels =spark.sql("Select Sum(updatedTheftsJoin.thefts) as thefts,model from updatedTheftsJoin Group By model SORT BY thefts DESC LIMIT 5");
        updatedTheftsWithOriginTopFiveTheftsModels.cache();
        System.out.println("\n................updated Thefts With Origin Top Five Thefts Models................\n");
        updatedTheftsWithOriginTopFiveTheftsModels.show();

        //List the most 5 states based on the number of thefted cars :
        var updatedTheftsWithOriginTopFiveStates =spark.sql("Select Sum(updatedTheftsJoin.thefts) as thefts, state from updatedTheftsJoin Group By state SORT BY thefts DESC LIMIT 5");
        updatedTheftsWithOriginTopFiveStates.cache();
        System.out.println("\n................updated Thefts With Origin Top Five Countries................\n");
        updatedTheftsWithOriginTopFiveStates.show();
    }
}
