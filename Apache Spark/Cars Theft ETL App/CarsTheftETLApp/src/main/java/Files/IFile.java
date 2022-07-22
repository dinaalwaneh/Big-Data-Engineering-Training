package Files;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface IFile {

    public Dataset<Row> ReadFile(SparkSession spark, String filePath);
    public void WriteOnFile(Dataset<Row> dataset, String filePath);
}
