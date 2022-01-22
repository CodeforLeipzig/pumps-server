package de.oklab.l.pumps.pump

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import de.oklab.l.pumps.pump.to.PumpTO
import java.util.concurrent.*

@DgsComponent
class GqlPumpFetcher(
        private val repository: PumpRepository
) {
    @DgsQuery
    fun find(@InputArgument numberAnke: String): CompletableFuture<PumpTO?> {
        return CompletableFuture.supplyAsync {
            repository.findByNumberAnke(numberAnke)?.let { PumpTO.from(it) }
        }
    }
}