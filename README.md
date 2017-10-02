# LongestWord

This project finds the longest words in txt files
Put list of files into hadoop
 hadoop fs -put input/ inputDir

Command to execute jar
 hadoop jar LongestWordMR.jar MapReduceDriver inputDir OutputFile

Move output file to txt format 
 hadoop fs -cat OutputFile/part-r-00000 > /*/LongestWordMR/outputFile.txt


