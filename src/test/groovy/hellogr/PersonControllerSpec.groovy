package hellogr

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PersonController)
class PersonControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Access granted with valid security key."() {
        given:
            request.addHeader('SecurityKey', '1qaz!QAZ')
        expect:
            controller.isLegal(request) == true
    }
    void "Access denied with invalid security key."() {
        given:
            request.addHeader('SecurityKey', 'invalidKey')
        expect:
            controller.isLegal(request) == false
    }
}
