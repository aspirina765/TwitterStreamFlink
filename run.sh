#!/bin/bash
sudo yum install figlet -y;
figlet Docker!;
sudo sysctl -w vm.max_map_count=262144 ;
docker-compose up -d;
gnome-terminal  -- bash -c "figlet -c Kafka;cd twitterFlink; sbt \"runMain com.github.example.kafka.TwitterStreamProducer\"";

FROMHERE=5;

echo "Configurando ElasticSearch"
for ((i=FROMHERE; i>=1; i--))
do
	echo "Iniciando em $i";
	sleep 1;
done;
gnome-terminal  -- bash -c "figlet -c Consumidor Flink;cd twitterFlink; sbt \"runMain com.github.example.flinkStreamApplication\""
