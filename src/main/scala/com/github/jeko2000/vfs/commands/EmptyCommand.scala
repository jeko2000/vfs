package com.github.jeko2000.vfs.commands

import com.github.jeko2000.vfs.file_system.State

object EmptyCommand extends Command {
  override def apply(state: State): State = state.setOutput("")
}
