package com.github.jeko2000.vfs.file_system

import com.github.jeko2000.vfs.commands.Command

import java.util.Scanner
import com.github.jeko2000.vfs.files.Directory

object FileSystem extends App {
  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)
  while (true) {
    state.show()
    val input = scanner.nextLine()
    state = Command.from(input)(state)
  }
}
