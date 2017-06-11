package entity

/**
  * Created by sparr on 2017/5/17.
  */
class BreedPool(individuals: Array[Individual]) {

  /**
    * 杂交和变异
    * @param crossRate 杂交的概率
    * @param variationRate 变异的概率
    */
  def mix(crossRate:Double,variationRate:Double):Unit = {
    cross(crossRate)
    variation(variationRate)
  }

  /**
    * 杂交
    * @param rate 概率
    */
  def cross(rate:Double):Unit = {
    val size = individuals.length
    val codeLen = individuals(0).codeLen
    for( i <- 0 to (size*rate/2).toInt){
      val i1 = (Math.random() * size).toInt
      val i2 = (Math.random() * size).toInt
      println(s"i1=$i1 i2=$i2")
      if(i1!=i2){
        val break = (Math.random() * codeLen).toInt
        println(s"break = $break")
        for( i<- break until codeLen)
        {
          val temp = individuals(i1).code(i)
          individuals(i1).code(i) = individuals(i2).code(i)
          individuals(i2).code(i) = temp
        }
      }
    }
  }

  /**
    * 变异
    * @param rate 概率
    */
  def variation(rate:Double):Unit = {
    val size = individuals.length
    val codeLen = individuals(0).codeLen
    val totalLen = size * codeLen
    for( i <- 0 to (totalLen * rate).toInt){
      val i = (Math.random() * totalLen).toInt
      val index = i/codeLen
      val temp = (individuals(index).code(i-index*codeLen) + 1) % 2
      individuals(index).code(i-index*codeLen) = temp
//      println(s"change $index , ${i-index*codeLen} ")
    }
  }

  def getBestGroup(size:Int):Array[Individual] = {
    if(individuals.length>size)
      individuals.slice(0,size)
    else individuals
  }

  def printEntities() : Unit = {
    for(entity <- individuals){
      println()
      for( i <- entity.code)
        print(i)
    }
    println()
  }

}
