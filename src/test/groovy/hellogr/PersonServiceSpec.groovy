package hellogr

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PersonService)
@Mock(Person)
class PersonServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Creating User"() {
        given:
        def tstUser = service.createPerson('testfirstname','testlastname','testusername','testpassword')

        expect:
        tstUser.username == 'testusername'
    }
    void "Find User"() {
        given:
            new Person(firstname: 'testfirstname', lastname: 'testlastname', username: 'testusername', password: 'testpassword').save()
        when:
            def tstUser = service.findPerson('testusername')
        then:
            tstUser.firstname == 'testfirstname'
            tstUser.lastname == 'testlastname'
            tstUser.username == 'testusername'
            tstUser.password == 'testpassword'
    }
}
