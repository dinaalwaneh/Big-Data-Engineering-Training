package com.cars.thefts;

import Files.IFile;
import Files.Imp.CSVFile;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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

        IFile csvFile = new CSVFile();

        /*.............................Read cars.csv file.............................*/

        final String CARS_FILE_PATH = "src/main/resources/Files/cars.csv";

        Dataset<Row> cars = csvFile.ReadFile(spark,CARS_FILE_PATH);
        cars = cars.withColumnRenamed("Car Brand","car_brand");
        cars = cars.withColumnRenamed("Country of Origin","country_of_origin");

        cars.cache();
        System.out.println("..........Cars Dataset..........\n");
        cars.show(5);

        /*.............................Read 2015_State_Top10Report_wTotalThefts.csv file.............................*/

        final String THEFTS_FILE_PATH = "src/main/resources/Files/2015_State_Top10Report_wTotalThefts.csv";

        Dataset<Row> thefts = csvFile.ReadFile(spark,THEFTS_FILE_PATH);
        thefts =  thefts.withColumnRenamed("Make/Model","model");
        thefts = thefts.withColumnRenamed("Model Year","year");

        thefts.cache();
        System.out.println("\n................Thefts Dataset................\n");
        thefts.show(5);

        /*....................store Dataset as the table or convert Dataset into temporary view....................*/

        cars.createOrReplaceTempView("cars");
        thefts.createOrReplaceTempView("thefts");

        /*....................Second Req....................*/
        Dataset<Row> join = spark.sql("select * FROM thefts left join cars on thefts.model like concat (cars.car_brand,'%')");

        Dataset<Row> thefts_with_origin_country = join.select(join.col("model"), join.col("Country_of_origin"),join.col("year"),join.col("Thefts"));
        thefts_with_origin_country = thefts_with_origin_country.na().drop();

        thefts_with_origin_country.cache();
        System.out.println("\n................Thefts with origin country Dataset................\n");
        thefts_with_origin_country.show(5);

        /*....................thefts with origin country partitioning....................*/
        Dataset<Row> thefts_with_origin_country_partioning =  thefts_with_origin_country.repartition(join.col("year"));

        thefts_with_origin_country_partioning.cache();
        System.out.println("\n................thefts with origin country partioning................\n");
        thefts_with_origin_country_partioning.show(20);

    }
}
