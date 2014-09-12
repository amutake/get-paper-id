package me.amutake.ms

import com.typesafe.config.ConfigFactory
import scala.collection.JavaConversions._

import arisa.backend.microsoftacademic.api

object Main extends App {

  val config = ConfigFactory.load()

  val confIds = config.getIntList("papers.conf")
  val jourIds = config.getIntList("papers.journal")

  val paperIdsFromConfs = confIds.map { id =>
    api.Paper(Map("ConfID" -> id)).map(_.id)
  }.flatten.toSet
  val paperIdsFromJournals = jourIds.map { id =>
    api.Paper(Map("JourID" -> id)).map(_.id)
  }.flatten.toSet

  val paperIds: Set[Int] = paperIdsFromConfs ++ paperIdsFromJournals

  println(paperIds.toSeq.sorted.mkString("[", ",", "]"))
}
