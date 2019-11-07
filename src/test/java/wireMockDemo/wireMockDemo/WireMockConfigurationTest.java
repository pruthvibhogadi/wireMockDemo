package wireMockDemo.wireMockDemo;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
 
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
 
class WireMockConfigurationTest {
 
    private WireMockServer wireMockServer;
 
    @BeforeEach
    void configureSystemUnderTest() {
        this.wireMockServer = new WireMockServer(options()
                .dynamicPort()
        );
    }
}