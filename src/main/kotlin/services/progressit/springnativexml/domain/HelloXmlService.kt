package services.progressit.springnativexml.domain

import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.stereotype.Service
import services.progressit.springnativexml.domain.xml.XmlRootElement
import java.io.ByteArrayOutputStream
import javax.xml.transform.stream.StreamResult

@Service
class HelloXmlService(
    private val xmlRootElementMarshaller: Jaxb2Marshaller,
) {

    fun getXml(): String {
        val xmlRootElement = XmlRootElement()

        val stream = ByteArrayOutputStream()
        xmlRootElementMarshaller.marshal(xmlRootElement, StreamResult(stream))

        return String(stream.toByteArray())
    }
}
