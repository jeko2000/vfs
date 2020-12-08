package com.github.jeko2000.vfs.files

abstract class DirEntry(val parentPath: String, val name: String) {
  def isDirectory(): Boolean

  def isRegularFile(): Boolean
}
