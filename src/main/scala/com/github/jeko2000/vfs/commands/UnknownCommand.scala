package com.github.jeko2000.vfs.commands

import com.github.jeko2000.vfs.file_system.State

class UnknownCommand(val input: String) extends Command {
  override def apply(state: State): State =
    state.setOutput(s"No such command: $input")
}
