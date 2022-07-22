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

        /*....................store Dataset as the table or convert Dataset into temporary view....................*/

        updatedThefts.createOrReplaceTempView("updated_thefts");

        /*....................left join between thefts table and updated_thefts according 3 keys from columns....................*/
        var newThefts= spark.sql("select * FROM thefts left JOIN updated_thefts ON  updated_thefts.model1 like  thefts.model And updated_thefts.State1 like thefts.State  And updated_thefts.year1 like thefts.year");
        newThefts.cache();

        /*....................filter data set with null values to except the updated rows....................*/
        newThefts = newThefts.filter(newThefts.col("state1").isNull());
        newThefts.cache();

        //Delete the extra rows to make a union between
        newThefts = newThefts.drop(newThefts.col("State1"));
        newThefts = newThefts.drop(newThefts.col("Rank1"));
        newThefts = newThefts.drop(newThefts.col("model1"));
        newThefts = newThefts.drop(newThefts.col("year1"));
        newThefts = newThefts.drop(newThefts.col("Thefts1"));

        newThefts.cache();

        var merge_thefts = newThefts.union(updatedThefts);

        merge_thefts.cache();
        System.out.println("\n................merge thefts Dataset................\n");
        merge_thefts.show(5);
        System.out.println("\n merge thefts Dataset count = " + merge_thefts.count() + "\n");


        //store thefts_with_origin_country Dataset as the table
        thefts_with_origin_country.createOrReplaceTempView("thefts_with_origin_country");

        /*.........Find top five countries from old thefts.........*/
        var topFiveCountries =spark.sql("Select Sum(thefts_with_origin_country.thefts) as s ,country_of_origin from thefts_with_origin_country Group By country_of_origin SORT BY s DESC LIMIT 5");

        topFiveCountries.cache();
        System.out.println("\n................top Five Countries................\n");
        topFiveCountries.show();

        /*.........Find top five countries from updated thefts.........*/

    }
}
