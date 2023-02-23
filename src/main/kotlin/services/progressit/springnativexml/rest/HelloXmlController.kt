package services.progressit.springnativexml.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import services.progressit.springnativexml.domain.HelloXmlService

@RestController
@RequestMapping("/")
class HelloXmlController(
    private val service: HelloXmlService,
) {

    @GetMapping
    fun get() = service.getXml()
}
