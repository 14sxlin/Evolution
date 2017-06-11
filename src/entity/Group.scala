package entity

import scala.collection.mutable.ArrayBuffer

/**
  * Created by sparr on 2017/5/17.
  */
class Group(val codeLen:Int,
            val from:Double,
            val to:Double,
            val size:Int) {

  var individuals = new Array[Individual](size)
  var breedPool : BreedPool = _

  private val rate = new Array[Double](size)

  def createIndividuals(): Unit = {
    for(i <- individuals.indices){
      individuals(i) = new Individual(codeLen,from,to)
    }
  }

  /**
    * 计算适应值把并且生成一个繁殖池
    * @param fun 适应函数
    * @return
    */
  def adapt(fun:(Double, Double) => Double,reverse:Boolean): Unit = {
    for( i <- individuals.indices){
      if(reverse)
        rate(i) = 1.0/individuals(i).getAdaptRate(fun)
      else
        rate(i) = individuals(i).getAdaptRate(fun)
    }
    val sumRate = rate.sum
    for( i <- rate.indices) {
      rate(i) /= sumRate
      println("rate = "+rate(i))
    }

    var entices = new ArrayBuffer[Individual]()
    for( i <- rate.indices)
      for( j <- 0 until getCount(rate(i) * size)){
        entices += new Individual(individuals(i))
      }
    println("next gen begin size = "+entices.length)
    breedPool = new BreedPool(entices.toArray)
//    breedPool.printEntities()
  }

  /**
    * 或者最接近的整数值
    * @param value 输入的数
    * @return
    */
  def getCount(value:Double) :Int = {
    val i1 = (value+1).toInt
    val i2 = value.toInt
    if(Math.abs(i1-value)<Math.abs(i2-value))
      i1
    else i2
  }

  /**
    * 杂交和变异
    * @param crossRate 杂交的概率
    * @param variationRate 变异的概率
    */
  def mix(crossRate:Double,variationRate:Double):Unit = {
    breedPool.mix(crossRate,variationRate)
    breedPool.getBestGroup(size)
    individuals = breedPool.getBestGroup(size)
  }

}
