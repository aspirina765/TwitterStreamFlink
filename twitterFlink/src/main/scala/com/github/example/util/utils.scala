package com.github.example.util

import com.github.example.kafka.KafkaProducer
import twitter4j.{StallWarning, Status, StatusDeletionNotice, StatusListener, TwitterObjectFactory}

object utils {
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_TOKEN_KEY"))
    .setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_TOKEN_SECRET"))
    .setOAuthAccessToken(System.getenv("TWITTER_ACCESS_TOKEN_KEY"))
    .setOAuthAccessTokenSecret(System.getenv("TWITTER_ACCESS_TOKEN_SECRET"))
    .setTweetModeExtended(true)
    .setJSONStoreEnabled(true)
    .build


  def simpleStatusListener = new StatusListener() {
    def onStatus(status: Status): Unit = {
      KafkaProducer.run(TwitterObjectFactory.getRawJSON(status))    }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }
}
