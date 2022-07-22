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
        final String FILE_PATH = "src/main/resources/Files/cars.csv";

        Dataset<Row> cars = csvFile.ReadFile(spark,FILE_PATH);
        cars = cars.withColumnRenamed("Car Brand","car_brand");

        cars.cache();
        cars.show();

    }
}
