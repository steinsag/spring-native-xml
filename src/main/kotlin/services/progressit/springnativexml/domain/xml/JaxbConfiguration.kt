package services.progressit.springnativexml.domain.xml

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
class JaxbConfiguration {

    @Bean
    fun createJaxbMarshaller(): Jaxb2Marshaller {
        val marshaller = Jaxb2Marshaller()
        marshaller.setClassesToBeBound(XmlRootElement::class.java)

        return marshaller
    }
}
