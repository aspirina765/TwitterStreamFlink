package com.github.example.util

import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.requests.common.RefreshPolicy
import com.sksamuel.elastic4s.requests.mappings.{FieldDefinition, MappingDefinition}
import com.sksamuel.elastic4s.requests.searches.SearchResponse
import org.apache.http.HttpStatus

object ElasticSearchConfig {
  private val port = 9200
  private val host = "localhost"

  val client = ElasticClient(JavaClient(ElasticProperties(s"http://${host}:${port}")))

  def insertText(str : String): Unit = {
    val resp = client.execute {
      indexInto("tweets").fields("tweet" -> str).refresh(RefreshPolicy.Immediate)
    }.await
    println(resp)
  }

  def searchTweet(str: String = ""): SearchResponse ={
    if(str == "") {
      val resp = client.execute {
        search("tweets")
      }.await
      resp.result
    }
    else {
      val resp = client.execute {
        search("tweets") query str
      }.await
      resp.result
    }

  }



  def searchAndCreateIndex() = {
    val indexName="tweets"
    if(client.execute{ indexExists(indexName)}.await.result.isExists){
      client.execute{ deleteIndex(indexName)}.await
    }
    val resp = client.execute(createIndex("tweets").mappings(mapping("_doc").fields(textField("tweet")))).await
    println(resp)
    resp.isSuccess
  }

}
