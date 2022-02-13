package de.oklab.l.pumps.tree

import de.oklab.l.pumps.tree.to.TreeTO
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/trees")
class TreeController constructor(internal val treeService: TreeService) {

    @GetMapping("history")
    fun getHistoryPaged(@RequestParam(defaultValue = "0") page: Int, @RequestParam(defaultValue = "10") pageSize: Int): List<TreeTO> =
        treeService.getHistoryPaged(page, pageSize)

    @GetMapping("history/count")
    fun getHistoryCount(): Long = treeService.getCount()

    @GetMapping("history/timestamps")
    fun getHistoryTimestamp(): SortedSet<Long> = treeService.timestamps()

    @GetMapping("history/tree/{treeId}")
    fun getHistory(@PathVariable("treeId") treeId: String, @RequestParam max: Int): List<TreeTO> =
        treeService.getHistory(treeId)

    @GetMapping("history/tree/{treeId}/timestamp/{timestamp}")
    fun getTreeAtTimestamp(@PathVariable("treeId") treeId: String, @PathVariable("timestamp") timestamp: Long,
                           @RequestParam max: Int): TreeTO? =
        treeService.getTreeAtTimestamp(treeId, timestamp)

    @GetMapping("current")
    fun getCurrentPaged(@RequestParam(defaultValue = "0") page: Int, @RequestParam(defaultValue = "10") pageSize: Int): List<TreeTO> =
        treeService.getCurrentPaged(page, pageSize)

    @GetMapping("current/count")
    fun getCount(): Long = treeService.getCurrentCount()

    @GetMapping("/current/tree/{treeId}")
    fun getCurrent(@PathVariable("treeId") treeId: String): TreeTO? = treeService.getCurrent(treeId)
}