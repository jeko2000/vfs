package com.github.jeko2000.vfs.commands

import com.github.jeko2000.vfs.file_system.State
import com.github.jeko2000.vfs.files.DirEntry

object Ls extends Command {
  override def apply(state: State): State = {
    state.setOutput(state.wd.contents.mkString("\n"))
  }
}
