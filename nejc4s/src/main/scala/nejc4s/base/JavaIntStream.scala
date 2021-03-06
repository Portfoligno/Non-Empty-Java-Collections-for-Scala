package nejc4s.base

import java.util.IntSummaryStatistics
import java.util.function._
import java.util.stream.IntStream

object JavaIntStream {
  trait Refined extends JavaIntStream with JavaBaseStream.Refined[Integer, JavaIntStream] {
    override def filter(predicate: IntPredicate): JavaIntStream.Refined
    override def map(mapper: IntUnaryOperator): JavaIntStream.Refined
    override def mapToObj[B](mapper: IntFunction[_ <: B]): JavaStream.Refined[B]
    override def mapToLong(mapper: IntToLongFunction): JavaLongStream.Refined
    override def mapToDouble(mapper: IntToDoubleFunction): JavaDoubleStream.Refined
    override def flatMap(mapper: IntFunction[_ <: IntStream]): JavaIntStream.Refined
    override def distinct: JavaIntStream.Refined
    override def sorted: JavaIntStream.Refined
    override def peek(action: IntConsumer): JavaIntStream.Refined
    override def limit(maxSize: Long): JavaIntStream.Refined
    override def skip(n: Long): JavaIntStream.Refined
    //override def forEach(action: IntConsumer): Unit
    //override def forEachOrdered(action: IntConsumer): Unit
    //override def toArray(): Array[Int]
    //override def reduce(identity: Int, op: IntBinaryOperator): Int
    //override def reduce(op: IntBinaryOperator): OptionalInt
    //override def collect[B](supplier: Supplier[B], accumulator: ObjIntConsumer[B], combiner: BiConsumer[B, B]): B
    //override def sum(): Int
    //override def min(): OptionalInt
    //override def max(): OptionalInt
    override def count(): NaturalLong
    //override def average(): OptionalDouble
    override def summaryStatistics(): IntSummaryStatistics
    //override def anyMatch(predicate: IntPredicate): Boolean
    //override def allMatch(predicate: IntPredicate): Boolean
    //override def noneMatch(predicate: IntPredicate): Boolean
    //override def findFirst(): OptionalInt
    //override def findAny(): OptionalInt
    override def asLongStream: JavaLongStream.Refined
    override def asDoubleStream: JavaDoubleStream.Refined
    override def boxed: JavaStream.Refined[Integer]
    override def sequential(): JavaIntStream.Refined
    override def parallel(): JavaIntStream.Refined
    //override def iterator(): JavaPrimitiveIterator.OfInt
    override def spliterator(): Spliterator.OfInt.Refined

    override def unordered(): JavaIntStream.Refined
  }

  trait UnsafeProxy extends JavaBaseStream.UnsafeProxy[Integer, JavaIntStream] with Refined {
    protected
    override def delegate: JavaIntStream

    override def filter(predicate: IntPredicate): JavaIntStream.Refined =
      new JavaIntStream.UnsafeWrapper(delegate.filter(predicate))
    override def map(mapper: IntUnaryOperator): JavaIntStream.Refined =
      new JavaIntStream.UnsafeWrapper(delegate.map(mapper))
    override def mapToObj[B](mapper: IntFunction[_ <: B]): JavaStream.Refined[B] =
      new JavaStream.UnsafeWrapper(delegate.mapToObj(mapper))
    override def mapToLong(mapper: IntToLongFunction): JavaLongStream.Refined =
      new JavaLongStream.UnsafeWrapper(delegate.mapToLong(mapper))
    override def mapToDouble(mapper: IntToDoubleFunction): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.mapToDouble(mapper))
    override def flatMap(mapper: IntFunction[_ <: IntStream]): JavaIntStream.Refined =
      new JavaIntStream.UnsafeWrapper(delegate.flatMap(mapper))
    override def distinct: JavaIntStream.Refined = new JavaIntStream.UnsafeWrapper(delegate.distinct)
    override def sorted: JavaIntStream.Refined = new JavaIntStream.UnsafeWrapper(delegate.sorted)
    override def peek(action: IntConsumer): JavaIntStream.Refined =
      new JavaIntStream.UnsafeWrapper(delegate.peek(action))
    override def limit(maxSize: Long): JavaIntStream.Refined =
      new JavaIntStream.UnsafeWrapper(delegate.limit(maxSize))
    override def skip(n: Long): JavaIntStream.Refined = new JavaIntStream.UnsafeWrapper(delegate.skip(n))
    override def forEach(action: IntConsumer): Unit = delegate.forEach(action)
    override def forEachOrdered(action: IntConsumer): Unit = delegate.forEachOrdered(action)
    override def toArray(): Array[Int] = delegate.toArray()
    override def reduce(identity: Int, op: IntBinaryOperator): Int = delegate.reduce(identity, op)
    override def reduce(op: IntBinaryOperator): OptionalInt = delegate.reduce(op)
    override def collect[B](supplier: Supplier[B], accumulator: ObjIntConsumer[B], combiner: BiConsumer[B, B]): B =
      delegate.collect(supplier, accumulator, combiner)
    override def sum(): Int = delegate.sum()
    override def min(): OptionalInt = delegate.min()
    override def max(): OptionalInt = delegate.max()
    override def count(): NaturalLong = NaturalLong.unsafeFromLong(delegate.count())
    override def average(): OptionalDouble = delegate.average()
    override def summaryStatistics(): IntSummaryStatistics = delegate.summaryStatistics()
    override def anyMatch(predicate: IntPredicate): Boolean = delegate.anyMatch(predicate)
    override def allMatch(predicate: IntPredicate): Boolean = delegate.allMatch(predicate)
    override def noneMatch(predicate: IntPredicate): Boolean = delegate.noneMatch(predicate)
    override def findFirst(): OptionalInt = delegate.findFirst()
    override def findAny(): OptionalInt = delegate.findAny()
    override def asLongStream: JavaLongStream.Refined = new JavaLongStream.UnsafeWrapper(delegate.asLongStream)
    override def asDoubleStream: JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.asDoubleStream)
    override def boxed: JavaStream.Refined[Integer] = new JavaStream.UnsafeWrapper(delegate.boxed)
    override def sequential(): JavaIntStream.Refined = new JavaIntStream.UnsafeWrapper(delegate.sequential())
    override def parallel(): JavaIntStream.Refined = new JavaIntStream.UnsafeWrapper(delegate.parallel())
    override def iterator(): JavaPrimitiveIterator.OfInt = delegate.iterator()
    override def spliterator(): Spliterator.OfInt.Refined =
      new Spliterator.OfInt.UnsafeWrapper(delegate.spliterator())

    override def unordered(): JavaIntStream.Refined = new JavaIntStream.UnsafeWrapper(delegate.unordered())
  }

  class UnsafeWrapper(override protected val delegate: JavaIntStream) extends UnsafeProxy
}
