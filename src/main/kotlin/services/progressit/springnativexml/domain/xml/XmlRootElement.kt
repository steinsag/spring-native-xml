package services.progressit.springnativexml.domain.xml

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement
import jakarta.xml.bind.annotation.XmlType

@XmlType(name = "XmlRootElement", namespace = "https://example.com/XmlStructure")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "XmlRootElement")
class XmlRootElement
