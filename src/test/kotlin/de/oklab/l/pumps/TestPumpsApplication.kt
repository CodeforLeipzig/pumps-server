package de.oklab.l.pumps

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@SpringBootApplication
@ComponentScan(value = ["de.oklab.l.pumps"],
               excludeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                                                      value = arrayOf(PumpsApplication::class))])
@EnableJpaAuditing
class TestPumpsApplication : PumpsApplication() {
    @MockBean(name = "mvcHandlerMappingIntrospector")
    private val mvcHandlerMappingIntrospector: HandlerMappingIntrospector? = null

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(javaClass, *args)
        }
    }
}