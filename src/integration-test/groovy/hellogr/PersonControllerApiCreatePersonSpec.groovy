package hellogr

import grails.converters.JSON
import grails.test.mixin.integration.Integration
import grails.transaction.*
import grails.util.GrailsWebMockUtil
import hellogr.commands.PersonCommand
import org.apache.http.entity.ContentType
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.WebApplicationContext
import spock.lang.*

import java.lang.reflect.Method

@Integration
@Rollback
class PersonControllerApiCreatePersonSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def setupData(){


    }

    void "test something"() {
        given:

            def ctrl = new PersonController()
            ctrl.request.method = 'POST'
            ctrl.request.contentType = 'application/json'
            //ctrl.request.setAttribute(SecurityKey: '1qaz!QAZ')
            def msg = [firstname: 'testfirstname', lastname: 'testlastname', username: 'testusername', password: 'testpassword'] as JSON
            ctrl.request.content = msg.toString().getBytes()
            ctrl.createPerson()
        expect:
            ctrl.response.status == 200

    }
}
