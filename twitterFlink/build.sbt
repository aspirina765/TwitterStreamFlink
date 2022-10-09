name := "twitterFlink"

version := "0.1"

scalaVersion := "2.12.11"


resolvers += Resolver.sonatypeRepo("releases")


libraryDependencies += "org.apache.flink" %% "flink-clients" % "1.10.0"
libraryDependencies += "org.apache.flink" %% "flink-scala" % "1.10.0"
libraryDependencies += "org.apache.flink" %% "flink-streaming-scala" % "1.10.0"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.1"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "2.5.0"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30"
libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "4.0.7"
libraryDependencies += "org.apache.flink" %% "flink-connector-kafka" % "1.10.0"
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-client-esjava" % "7.6.1"

// set the main class for packaging the main jar
mainClass in (Compile, packageBin) := Some("com.github.example.kafka.TwitterStreamProducer")

// set the main class for the main 'sbt run' task
mainClass in (Compile, run) := Some("com.github.example.kafka.TwitterStreamProducer")

//  [1] com.github.example.flinkStreamApplication
//  [2] com.github.example.kafka.TwitterStreamProducer