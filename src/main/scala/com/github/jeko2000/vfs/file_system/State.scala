package com.github.jeko2000.vfs.file_system

import com.github.jeko2000.vfs.files.Directory

class State(val root: Directory, val wd: Directory, val output: String) {
  def show(): Unit = {
    println(output)
    print(State.PROMPT)
  }

  def setOutput(output: String): State = {
    State(this.root, this.wd, output)
  }
}

object State {
  val PROMPT: String = "$ "

  def apply(root: Directory, wd: Directory, output: String = ""): State =
    new State(root, wd, output)
}
