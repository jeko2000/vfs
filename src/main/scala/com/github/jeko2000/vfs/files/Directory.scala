package com.github.jeko2000.vfs.files

import scala.annotation.tailrec

class Directory(
    override val parentPath: String,
    override val name: String,
    val contents: Set[DirEntry]
) extends DirEntry(parentPath, name) {
  def isDirectory(): Boolean = true

  def isRegularFile(): Boolean = false

  def getEntry(entryName: String): Option[DirEntry] =
    contents.find(entry => entry.name.equals(entryName))

  def hasEntry(entryName: String): Boolean =
    getEntry(entryName).nonEmpty

  def addEntry(entry: DirEntry): Directory = new Directory(parentPath, name, contents + entry)

  def getDirectory(directoryName: String): Option[Directory] =
    getEntry(directoryName)
      .flatMap(entry => Directory.asDirectory(entry))

  def getEntryIn(paths: List[String]): Option[Directory] =
    if (paths.isEmpty) Some(this)
    else getDirectory(paths.head).flatMap(dir => getEntryIn(paths.tail))

  def getDirectoryIn(paths: List[String]): Option[Directory] =
    getEntryIn(paths).flatMap(entry => Directory.asDirectory(entry))

  def ancestorNames(): List[String] =
    if (parentPath.isEmpty()) List()
    else parentPath.substring(1).split(" ").toList

  def parent(): Option[Directory] =
    if (parentPath.isEmpty()) None
    else getEntryIn(ancestorNames().dropRight(1))

  override def toString(): String =
    s"Directory($parentPath${Directory.SEPARATOR}$name)"
}

object Directory {

  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, Set())

  def asDirectory(entry: DirEntry): Option[Directory] =
    if (entry.isDirectory()) Some(entry.asInstanceOf[Directory])
    else None
}
