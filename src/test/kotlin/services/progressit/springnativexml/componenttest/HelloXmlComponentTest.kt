package services.progressit.springnativexml.componenttest

import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class HelloXmlComponentTest @Autowired constructor(
    private val mockMvc: MockMvc,
) {

    @Test
    fun getPage() {
        mockMvc.get("/")
            .andExpect { status().isAccepted }
            .andExpect {
                content {
                    string(
                        containsString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><XmlRootElement/>"),
                    )
                }
            }
    }
}
