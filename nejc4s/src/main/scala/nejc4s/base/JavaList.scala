package nejc4s
package base

object JavaList {
  trait Refined[A] extends JavaList[A] with JavaCollection.Refined[A] {
    //override def get(index: Int): A
    override def indexOf(o: Any): NaturalIntX
    override def lastIndexOf(o: Any): NaturalIntX
    override def listIterator: JavaListIterator.Refined[A]
    override def listIterator(index: Int): JavaListIterator.Refined[A]
    override def subList(fromIndex: Int, toIndex: Int): JavaList.Refined[A]

    override def spliterator: Spliterator.Refined[A] = new Spliterator.UnsafeWrapper(super[List].spliterator)
  }

  trait UnsafeProxy[A] extends JavaCollection.UnsafeProxy[A] with Refined[A] {
    protected
    override def delegate: JavaList[A]

    override def get(index: Int): A = delegate.get(index)
    override def indexOf(o: Any): NaturalIntX = NaturalIntX.unsafeFromInt(delegate.indexOf(o))
    override def lastIndexOf(o: Any): NaturalIntX = NaturalIntX.unsafeFromInt(delegate.lastIndexOf(o))
    override def listIterator: JavaListIterator.Refined[A] =
      new JavaListIterator.UnsafeWrapper(delegate.listIterator)
    override def listIterator(index: Int): JavaListIterator.Refined[A] =
      new JavaListIterator.UnsafeWrapper(delegate.listIterator(index))
    override def subList(fromIndex: Int, toIndex: Int): JavaList.Refined[A] =
      new UnsafeWrapper(delegate.subList(fromIndex, toIndex))

    override def spliterator: Spliterator.Refined[A] = new Spliterator.UnsafeWrapper(delegate.spliterator)

    override def addAll(index: Int, c: JavaCollection[_ <: A]): Boolean =
      throw new UnsupportedOperationException("addAll")
    override def set(index: Int, element: A): A = throw new UnsupportedOperationException("set")
    override def add(index: Int, element: A): Unit = throw new UnsupportedOperationException("add")
    override def remove(index: Int): A = throw new UnsupportedOperationException("remove")
  }

  class UnsafeWrapper[A](
    override protected val delegate: JavaList[A]
  ) extends UnsafeProxy[A]


  import syntax.seqView._

  def apply[A](xs: A*): JavaList.Refined[A] =
    new UnsafeWrapper(xs.asJava)

  def unapplySeq[A](xs: JavaList[A]): Some[collection.Seq[A]] =
    Some(xs.asScala)
}
