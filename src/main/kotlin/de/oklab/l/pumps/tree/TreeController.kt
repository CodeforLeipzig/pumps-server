package de.oklab.l.pumps.tree

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trees")
class TreeController constructor(internal val treeService: TreeService){

    @GetMapping("count")
    fun getCount(): Long = treeService.getCount()
}