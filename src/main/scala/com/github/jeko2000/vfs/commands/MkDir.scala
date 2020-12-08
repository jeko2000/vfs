package com.github.jeko2000.vfs.commands

import com.github.jeko2000.vfs.file_system.State
import com.github.jeko2000.vfs.files.Directory
import com.github.jeko2000.vfs.files.DirEntry

class MkDir(name: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    MkDir
      .validateName(wd, name)
      .map(msg => state.setOutput(msg))
      .getOrElse({
        MkDir.doMkdir(state, name)
      })
  }
}

object MkDir {
  val INVALID_DIRECTORY_NAME_PARTS =
    Set("!", "..", "@", "$", "%", "^", "&", "*")
  def validateName(wd: Directory, name: String): Option[String] = {
    if (name.isEmpty()) Option("Directory names must be non-empty")
    else if (wd.hasEntry(name)) Option(s"Working directory already contains: $name")
    else if (name.startsWith("."))
      Option("Directory names must not start with a '.'")
    else if (name.endsWith("."))
      Option("Directory names must not end with '.'")
    else if (name.contains(Directory.SEPARATOR)) Option("Multi-level directories are not supported")
    else {
      val invalidChar = INVALID_DIRECTORY_NAME_PARTS.find(c => name.contains(c))
      if (invalidChar.nonEmpty) {
        invalidChar.map(c =>
          s"Directory names must not contain $c (or any of $INVALID_DIRECTORY_NAME_PARTS)"
        )
      } else {
        None
      }
    }
  }

  def doMkdir(state: State, name: String): State = {
    val wd = state.wd
    val newDirectory = Directory.empty(wd.parentPath, name)
    val newRoot = updateStructure(Some(wd), newDirectory)
    newRoot
      .getDirectoryIn(wd.ancestorNames())
      .map(newWd => State(newRoot, newWd))
      .getOrElse(state.setOutput("Unable to reach target directory"))
  }

  def updateStructure(maybeWd: Option[Directory], directory: Directory): Directory = {
    maybeWd
      .map(wd => updateStructure(wd.parent(), wd.addEntry(directory)))
      .getOrElse(directory)
  }
}
