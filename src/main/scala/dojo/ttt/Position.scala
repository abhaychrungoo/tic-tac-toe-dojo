package dojo.ttt

class Position(val row: Int, val column: Int) {

  override def equals(obj: Any) =
    obj != null && obj.isInstanceOf[Position] && {
      val that = obj.asInstanceOf[Position]
      this.row == that.row && this.column == that.column
    }

}