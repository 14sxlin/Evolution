package entity

/**
  * Created by sparr on 2017/5/17.
  */
class Individual {

  var codeLen:Int = _
  var code : Array[Int] = _
  var from : Double = _
  var to : Double = _

  /**
    *
    * @param code 个体的编码
    */
  def this(code:Array[Int],from:Double,to:Double) = {
    this()
    this.codeLen = code.length
    this.code = code
    this.from = from
    this.to = to
  }

  /**
    * 建立个体,随机初始化个体的值
    * @param codeLen 编码的长度
    */
  def this(codeLen:Int,from:Double,to:Double) = {
    this()
    this.codeLen = codeLen
    this.from = from
    this.to = to
    code = new Array[Int](codeLen)
    for( i <- code.indices){
      code(i) = if(Math.random()>0.5) 1 else 0
    }
  }

  /**
    * 复制一个相同的个体
    * @param individual 个体
    */
  def this(individual: Individual){
    this()
    this.from = individual.from
    this.to = individual.to
    this.codeLen = individual.codeLen
    this.code = new Array[Int](codeLen)
    for( i <- code.indices){
      code(i) = individual.code(i)
    }
  }

  /**
    * 获得个体适应值
    * @param fun 1个输入参数的函数
    */
  def getAdaptRate(fun:(Double) => Double) : Double= {
    fun(code2Value(code))
  }

  /**
    * 获得个体适应值
    * @param fun 两个输入参数的函数
    */
  def getAdaptRate(fun:(Double, Double) => Double): Double = {
    val (v1,v2) = code2Value(codeLen/2,code)
    fun(v1,v2)
  }

  /**
    * 将本个体的编码变换成实数
    * @param code 编码
    * @return
    */
  def code2Value(code:Array[Int]):Double= {
    var sum = 0L
    var weight = 1
    for( i <- code.reverse){
      sum += i*weight
      weight *= 2
    }
//    println("sum = "+sum)
    sum*1.0/((1 << code.length) - 1) *(to-from) + from //<< 运算符的优先级比 - 低
  }


  /**
    *
    * 将编码分隔成两个数
    * @param break 分割点
    * @param code 编码
    * @return
    */
  def code2Value(break:Int,code:Array[Int]):(Double,Double)= {
    val code1 = code.slice(0,break)
    val code2 = code.slice(break,code.length)
    (code2Value(code1),code2Value(code2))
  }
}
