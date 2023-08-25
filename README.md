# Spark-PageRank

# **PageRank Analysis on GCP**

## **Objective**
PageRank algorithm assigns a numerical importance score to each webpage based on its connectivity and the significance of its linking pages. This project aims to evaluate this score for various pages to determine their relevance in a web graph.

## **Background**
Developed by Google's co-founders, Larry Page and Sergey Brin, PageRank forms a cornerstone of web search, helping determine the relevance and prominence of web pages. The score allocated to a webpage symbolizes its authority in the interconnected web landscape. The more recognized and authoritative pages linking to a page, the higher its assigned PageRank.

## **Design and Logic**
- **Initialization**: Every page starts with a rank of 1.0.
- **Iterative Contribution**: Each page 'p' divides its rank by its number of links, sending that contribution to its linked pages.
- **Rank Update**: Using contributions from its linking pages, a page's rank is recalculated as: `rank = 0.15 + 0.85 * contributionsReceived`.

**Notes**:
- A damping factor of 0.85 helps counterbalance the influence of highly connected pages.
- A greater number of input web pages enhances the accuracy.
- A higher PageRank for an input page generally yields better results.

## **Implementations**
### **1. Using PySpark**
**Requirements**: Google Cloud's DataProc

**Steps**:
1. **Cloud Shell Activation**: Activate the cloud shell & open its editor.
2. **File Creation**: Create a new Python file. Sample input: 
```
A B
A C
B C
C A
```
3. **PySpark Job Submission**:
    ```bash
    gcloud dataproc jobs submit pyspark pagerank.py --cluster=<cluster-name> --region=<region-of-cluster> -- <path-of-input-file-from-bucket> <number-of-iterations>
    ```
4. **Output Retrieval**: View the output on GCP.

### **2. Using Scala**
**Steps**:
1. **SSH Connection**: Navigate to the Dataproc Cluster details page and connect via SSH.
2. **Environment Setup**:
    ```bash
    curl -fL https://github.com/coursier/coursier/releases/latest/download/cs-x86_64-pc-linux.gz | gzip -d > cs && chmod +x cs && ./cs setup
    export SCALA_HOME=/usr/local/share/scala
    export PATH=$PATH:$SCALA_HOME/
    ```
3. **HDFS Setup**: Create an input directory and upload the input file.
4. **Spark Shell Execution**: Launch the Spark shell and run the `pagerank.scala` content.

## **Results**
PageRank outputs the relevance score for each evaluated webpage.

## **Additional Resources**
- Link to Google Slides: https://docs.google.com/presentation/d/1QOSXqZg2dFzxuQ34TtlFRG8l0VwQFXjygpLO_xNFf3g/edit#slide=id.g23ed2c52efe_0_72

**Tips**:
1. Consider replacing placeholders like `LINK_TO_THE_SLIDES` with actual URLs or resources.
2. Adding visuals (screenshots, diagrams) can make the README more engaging and informative. 
3. A "Contribution" or "Feedback" section can invite collaboration and suggestions to improve the project further.
