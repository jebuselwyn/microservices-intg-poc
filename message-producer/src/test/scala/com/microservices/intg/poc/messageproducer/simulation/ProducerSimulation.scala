package com.microservices.intg.poc.messageproducer.simulation

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

/**
  * Created by jebuselwyn.martin on 10/10/2018.
  */
class ProducerSimulation extends Simulation{

  val logger =  LoggerFactory.getLogger(this.getClass)
  val envConfig = ConfigFactory.load().getConfig("env.dev")
  val common_header = Map("SOAPAction" -> """""""")
  val fxRateUri = envConfig.getString("producer-url")+"/fxRate"
  val contentType = "application/json"
  val testDataSetupScenario =
    scenario("Test Data Setup")
    .repeat(100){
      feed(csv("fxrate.csv").circular)
      .exec(http("Load Fx Rates")
      .post(fxRateUri)
      .body(ELFileBody("fxRate.json"))
      .headers(common_header)
      .header("Content-Type", contentType)
      .check(status.is(200)))
    }
    .inject(atOnceUsers(100))
  setUp(testDataSetupScenario)

}
