package com.github.jeko2000.vfs.commands

import com.github.jeko2000.vfs.file_system.State
import com.github.jeko2000.vfs.util.Util

trait Command {
  def apply(state: State): State
}

object Command {
  val MKDIR = "mkdir"
  val LS = "ls"

  def from(input: String): Command = {
    val (commandName, args) = Util.parseInput(input)
    if (commandName.isEmpty()) EmptyCommand
    else if (MKDIR.equals(commandName)) {
      if (args.isEmpty) new IncompleteCommand(MKDIR)
      else new MkDir(args(0))
    } else if (LS.equals(commandName)) Ls
    else new UnknownCommand(commandName)
  }
}
