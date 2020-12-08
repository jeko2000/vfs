package com.github.jeko2000.vfs.file_system

import com.github.jeko2000.vfs.commands.Command

import java.util.Scanner
import com.github.jeko2000.vfs.files.Directory

object FileSystem extends App {
  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)
  var line = ""
  while (line != "exit") {
    state.show()
    line = scanner.nextLine()
    state = Command.from(line)(state)
  }
}
