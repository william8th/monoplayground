import java.util.concurrent.ThreadLocalRandom

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class TestSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  //  val scn = scenario("TestSimulation")
  //    .exec(http("request_1")
  //      .get("/"))
  //    .pause(5)

  //  val scn = scenario("Basic simulation").exec(Search.search)

  val users = scenario("Users").exec(Search.search, Browse.browse)
  val admins = scenario("Admins").exec(Search.search, Browse.browse, Edit.edit)

  setUp(
    users.inject(rampUsers(10) during (10 seconds)),
    admins.inject(rampUsers(2) during (10 seconds))
  ).protocols(httpProtocol)

}

object Search {
  val search = exec(http("Home")
    .get("/"))
    .pause(7)
    .exec(http("Search")
      .get("/computers?f=macbook"))
    .pause(2)
    .exec(http("Select")
      .get("/computers/6"))
    .pause(3)
}

object Browse {
  val browse = repeat(5, "n") {
    exec(http("Page ${n}")
      .get("/computers?p=${n}"))
      .pause(1)
  }
}

object Edit {
  val edit = exec(http("Form")
    .get("/computers/new"))
    .pause(1)
    .exec(http("Post")
      .post("/computers")
      .check(status.is(session => 200 + ThreadLocalRandom.current.nextInt(2))))
}
