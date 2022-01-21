package de.oklab.l.pumps.pump

//@DgsComponent
//class GqlPumpFetcher(
//    private val repository: PumpRepository
//) {
//    @DgsQuery
//    fun find(@InputArgument id: String): CompletableFuture<Pump?> {
//        return CompletableFuture.supplyAsync {
//            repository.findById(id)
//        }
//    }
//}