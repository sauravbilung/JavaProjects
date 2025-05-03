#!/bin/bash
cd ~/IdeaProjects/LetsGoJava/src/main/java
javac FileHandling/_2_streamprocessing/_2_StreamingFromStdIn.java
java FileHandling._2_streamprocessing._2_StreamingFromStdIn < ~/IdeaProjects/LetsGoJava/src/main/resources/data/1800.csv
