package Essentials

import java.io.File
import java.nio.file.Files

case class Voice(wavContent: Array[Byte], filename: String){
  def create(filename: String): Voice={
    Voice(Files.readAllBytes(new File(filename).toPath), new File(filename).getName)
  }
}
case class Photo(photoContent: Array[Byte], filename: String)
