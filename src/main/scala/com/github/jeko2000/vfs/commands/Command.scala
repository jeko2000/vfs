package com.github.jeko2000.vfs.commands

import com.github.jeko2000.vfs.file_system.State

trait Command {
  def apply(state: State): State
}

object Command {
  def from(input: String): Command = {
    new UnknownCommand(input)
  }
}
