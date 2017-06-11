import entity.Individual
import org.scalatest.{FunSuite, TestSuite}

/**
  * Created by sparr on 2017/5/17.
  */
class TestIndividual extends FunSuite{

  test("code2Value"){
    val code = Array(1,0,1,0)
    val individual = new Individual(code,0,1)
    individual.code = code
    val value = individual.code2Value(individual.code)
    println("value = "+value)
    assert(value == 1.0)
  }

  test("code2 value 2"){
    val code = Array(1,0,1,0)
    val individual = new Individual(code,0,1)
    individual.code = code
    val (value1,value2) = individual.code2Value(2,individual.code)
    println("value1 = "+value1+" value = "+value2)
  }
}
