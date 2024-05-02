# scc-akka-app

This scaffolded project contains:
- [Akka Http](https://doc.akka.io/docs/akka-http/current/index.html)
- [Akka Actor](https://doc.akka.io/docs/akka/current/typed/index.html)
- [Akka Stream](https://doc.akka.io/docs/akka/current/stream/index.html)
- [circe](https://circe.github.io/circe/)
- [pureconfig](https://pureconfig.github.io)
- [slick](https://scala-slick.org)

To run this project first run docker-compose to start PostgreSql with configured settings.
Then execute `sbt build run` to start server on the default 8080 port — or edit `application.conf` and provide your desired port number instead.


Also you might want to check another template from Epam Scala Competency Center with Http4s + ZIO + Doobie stack: https://github.com/EpamLifeSciencesTeam/scc_mentoring_be_zio.g8