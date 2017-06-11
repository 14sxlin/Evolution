import entity.{BreedPool, Individual}
import org.scalatest.FunSuite

/**
  * Created by sparr on 2017/5/17.
  */
class TestBreedPool extends FunSuite{

  val entities = Array(
    new Individual(Array(0,0,0,0,1),-1,1),
    new Individual(Array(0,0,0,1,1),-1,1),
    new Individual(Array(0,0,1,1,1),-1,1),
    new Individual(Array(0,1,1,1,1),-1,1),
    new Individual(Array(1,1,1,1,1),-1,1)
  )
  val breedPool = new BreedPool(entities)
  test("cross"){
    breedPool.printEntities()
    println("-------after cross------")
    breedPool.cross(0.75)
    breedPool.printEntities()

  }

  test("variation"){
    breedPool.printEntities()
    for(i<- 0 to 100)
      breedPool.variation(0.01)
    println("-------after variation------")
    breedPool.printEntities()

  }
}
