import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.model.DecisionTreeModel
import org.apache.spark.mllib.util.MLUtils

object DecisionTreeClassificationExample {
  def main(args: Array[String]): Unit = {
    // Initialize Spark
    val conf = new SparkConf().setAppName("DecisionTreeClassification")
    val sc = new SparkContext(conf)

    // Load and parse the LIBSVM data file
    val data = MLUtils.loadLibSVMFile(sc, "data/mllib/sample_libsvm_data.txt")

    // Split the data into training and test sets (70% training, 30% testing)
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

    // Define model parameters
    val numClasses = 2
    val categoricalFeaturesInfo = Map[Int, Int]()
    val impurity = "gini"
    val maxDepth = 5
    val maxBins = 32

    // Train a DecisionTree model
    val model = DecisionTree.trainClassifier(
      trainingData,
      numClasses,
      categoricalFeaturesInfo,
      impurity,
      maxDepth,
      maxBins
    )

    // Evaluate the model on test instances and compute test error
    val labelAndPreds = testData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    val testErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / testData.count()
    println(s"Test Error = $testErr")

    // Print the learned classification tree model
    println("Learned classification tree model:\n" + model.toDebugString)

    // Save and load the model
    model.save(sc, "target/tmp/myDecisionTreeClassificationModel")
    val sameModel = DecisionTreeModel.load(sc, "target/tmp/myDecisionTreeClassificationModel")

    // Stop the Spark context
    sc.stop()
  }
}
