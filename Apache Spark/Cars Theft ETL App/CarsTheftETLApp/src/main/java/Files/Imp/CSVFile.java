package Files.Imp;

import Files.IFile;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class CSVFile implements IFile {

    @Override
    public Dataset<Row> ReadFile(SparkSession spark, String filePath) {
        return spark.read().option("header",true).csv(filePath);
    }

    @Override
    public void WriteOnFile(Dataset<Row> dataset, String filePath) {
        dataset.coalesce(1).write().mode("overwrite").format("com.databricks.spark.csv").option("header", "true").csv(filePath);
    }
}
