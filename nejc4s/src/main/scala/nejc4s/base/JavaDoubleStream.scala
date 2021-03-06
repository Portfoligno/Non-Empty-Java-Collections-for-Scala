package nejc4s.base

import java.lang
import java.util.DoubleSummaryStatistics
import java.util.function._
import java.util.stream.DoubleStream

object JavaDoubleStream {
  trait Refined extends JavaDoubleStream with JavaBaseStream.Refined[lang.Double, JavaDoubleStream] {
    override def filter(predicate: DoublePredicate): JavaDoubleStream.Refined
    override def map(mapper: DoubleUnaryOperator): JavaDoubleStream.Refined
    override def mapToObj[B](mapper: DoubleFunction[_ <: B]): JavaStream.Refined[B]
    override def mapToInt(mapper: DoubleToIntFunction): JavaIntStream.Refined
    override def mapToLong(mapper: DoubleToLongFunction): JavaLongStream.Refined
    override def flatMap(mapper: DoubleFunction[_ <: DoubleStream]): JavaDoubleStream.Refined
    override def distinct: JavaDoubleStream.Refined
    override def sorted: JavaDoubleStream.Refined
    override def peek(action: DoubleConsumer): JavaDoubleStream.Refined
    override def limit(maxSize: Long): JavaDoubleStream.Refined
    override def skip(n: Long): JavaDoubleStream.Refined
    //override def forEach(action: DoubleConsumer): Unit
    //override def forEachOrdered(action: DoubleConsumer): Unit
    //override def toArray(): Array[Double]
    //override def reduce(identity: Double, op: DoubleBinaryOperator): Double
    //override def reduce(op: DoubleBinaryOperator): OptionalDouble
    //override def collect[B](supplier: Supplier[B], accumulator: ObjDoubleConsumer[B], combiner: BiConsumer[B, B]): B
    //override def sum(): Double
    //override def min(): OptionalDouble
    //override def max(): OptionalDouble
    override def count(): NaturalLong
    //override def average(): OptionalDouble
    override def summaryStatistics(): DoubleSummaryStatistics
    //override def anyMatch(predicate: DoublePredicate): Boolean
    //override def allMatch(predicate: DoublePredicate): Boolean
    //override def noneMatch(predicate: DoublePredicate): Boolean
    //override def findFirst(): OptionalDouble
    //override def findAny(): OptionalDouble
    override def boxed: JavaStream.Refined[lang.Double]
    override def sequential(): JavaDoubleStream.Refined
    override def parallel(): JavaDoubleStream.Refined
    //override def iterator(): JavaPrimitiveIterator.OfDouble
    override def spliterator(): Spliterator.OfDouble.Refined

    override def unordered(): JavaDoubleStream.Refined
  }

  trait UnsafeProxy extends JavaBaseStream.UnsafeProxy[lang.Double, JavaDoubleStream] with Refined {
    protected
    override def delegate: JavaDoubleStream

    override def filter(predicate: DoublePredicate): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.filter(predicate))
    override def map(mapper: DoubleUnaryOperator): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.map(mapper))
    override def mapToObj[B](mapper: DoubleFunction[_ <: B]): JavaStream.Refined[B] =
      new JavaStream.UnsafeWrapper(delegate.mapToObj(mapper))
    override def mapToInt(mapper: DoubleToIntFunction): JavaIntStream.Refined =
      new JavaIntStream.UnsafeWrapper(delegate.mapToInt(mapper))
    override def mapToLong(mapper: DoubleToLongFunction): JavaLongStream.Refined =
      new JavaLongStream.UnsafeWrapper(delegate.mapToLong(mapper))
    override def flatMap(mapper: DoubleFunction[_ <: DoubleStream]): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.flatMap(mapper))
    override def distinct: JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeWrapper(delegate.distinct)
    override def sorted: JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeWrapper(delegate.sorted)
    override def peek(action: DoubleConsumer): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.peek(action))
    override def limit(maxSize: Long): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.limit(maxSize))
    override def skip(n: Long): JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeWrapper(delegate.skip(n))
    override def forEach(action: DoubleConsumer): Unit = delegate.forEach(action)
    override def forEachOrdered(action: DoubleConsumer): Unit = delegate.forEachOrdered(action)
    override def toArray(): Array[Double] = delegate.toArray()
    override def reduce(identity: Double, op: DoubleBinaryOperator): Double = delegate.reduce(identity, op)
    override def reduce(op: DoubleBinaryOperator): OptionalDouble = delegate.reduce(op)
    override def collect[B](supplier: Supplier[B], accumulator: ObjDoubleConsumer[B], combiner: BiConsumer[B, B]): B =
      delegate.collect(supplier, accumulator, combiner)
    override def sum(): Double = delegate.sum()
    override def min(): OptionalDouble = delegate.min()
    override def max(): OptionalDouble = delegate.max()
    override def count(): NaturalLong = NaturalLong.unsafeFromLong(delegate.count())
    override def average(): OptionalDouble = delegate.average()
    override def summaryStatistics(): DoubleSummaryStatistics = delegate.summaryStatistics()
    override def anyMatch(predicate: DoublePredicate): Boolean = delegate.anyMatch(predicate)
    override def allMatch(predicate: DoublePredicate): Boolean = delegate.allMatch(predicate)
    override def noneMatch(predicate: DoublePredicate): Boolean = delegate.noneMatch(predicate)
    override def findFirst(): OptionalDouble = delegate.findFirst()
    override def findAny(): OptionalDouble = delegate.findAny()
    override def boxed: JavaStream.Refined[lang.Double] = new JavaStream.UnsafeWrapper(delegate.boxed)
    override def sequential(): JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeWrapper(delegate.sequential())
    override def parallel(): JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeWrapper(delegate.parallel())
    override def iterator(): JavaPrimitiveIterator.OfDouble = delegate.iterator()
    override def spliterator(): Spliterator.OfDouble.Refined =
      new Spliterator.OfDouble.UnsafeWrapper(delegate.spliterator())

    override def unordered(): JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeWrapper(delegate.unordered())
  }

  class UnsafeWrapper(override protected val delegate: JavaDoubleStream) extends UnsafeProxy
}
