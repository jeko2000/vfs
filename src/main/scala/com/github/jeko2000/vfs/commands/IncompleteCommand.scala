package com.github.jeko2000.vfs.commands

import com.github.jeko2000.vfs.file_system.State

class IncompleteCommand(val name: String) extends Command {
  override def apply(state: State): State =
    state.setOutput(s"Incomplete command for $name")
}
