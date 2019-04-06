package nejc4s

import java.util.function.Consumer

object JavaIterable {
  trait Base[A] extends JavaIterable[A] {
    //override def iterator: JavaIterator[A]

    //override def forEach(action: Consumer[_ >: A]): Unit = super.forEach(action)
    //override def spliterator: JavaSpliterator[A] = super.spliterator
  }

  trait UnsafeProxy[A] extends Base[A] {
    protected
    def delegate: JavaIterable[A]

    override def iterator: JavaIterator[A] = delegate.iterator

    override def forEach(action: Consumer[_ >: A]): Unit = delegate.forEach(action)
    override def spliterator: JavaSpliterator[A] = delegate.spliterator
  }
}
