package com.github.jeko2000.vfs.util

object Util {
  def parseInput(input: String): (String, List[String]) = {
    if (input.isEmpty()) ("", List())
    else {
      val tokens = input.split(" ").toList
      (tokens.head, tokens.tail)
    }
  }
}
