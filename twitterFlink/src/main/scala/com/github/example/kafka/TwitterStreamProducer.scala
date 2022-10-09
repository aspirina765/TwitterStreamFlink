package com.github.example.kafka

import twitter4j.{FilterQuery, TwitterStreamFactory}
import com.github.example.util.utils.{config,simpleStatusListener}

object TwitterStreamProducer extends App {
  var finishingLoop = true

  //Create a twitter connect
  val twitterStream = new TwitterStreamFactory(config).getInstance
  twitterStream.addListener(simpleStatusListener)

  //Create a loop to send msgs to kafka
  while(finishingLoop){
    twitterStream.filter("Bolsonaro", "Moro" )
    Thread.sleep(200000)
    finishingLoop = false
  }
  twitterStream.cleanUp
  twitterStream.shutdown
}
