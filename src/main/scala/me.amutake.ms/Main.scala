package me.amutake.ms

import com.typesafe.config.ConfigFactory
import scala.collection.JavaConversions._

import arisa.backend.microsoftacademic.api

object Main {

  def main(args: Array[String]) = {

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
    val str = paperIds.toSeq.sorted.mkString("[", ",", "]")

    if (args.isEmpty) {
      println("[NOTICE] If you want to save to a file, please specify filename as argument.")
      println(str)
    } else {

      import java.io.PrintWriter

      val filename = args(0)
      val file = new PrintWriter(filename)
      file.println(str)
      file.close
    }
  }
}
