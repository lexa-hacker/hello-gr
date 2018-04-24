package hellogr

import grails.converters.JSON
import grails.test.mixin.integration.Integration
import grails.transaction.*
import grails.util.GrailsWebMockUtil
import hellogr.commands.PersonCommand
import org.apache.http.entity.ContentType
import org.grails.plugins.testing.GrailsMockHttpServletRequest
import org.grails.plugins.testing.GrailsMockHttpServletResponse
import org.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import spock.lang.*

import java.lang.reflect.Method

@Integration
@Rollback
class PersonControllerApiCreatePersonSpec extends Specification {

    @Autowired
    WebApplicationContext ctx

    @Autowired
    PersonController personController

    @Ignore
    def mockWebRequest(Map<String, String> reqParams = null, String key) {

        MockHttpServletRequest request = new GrailsMockHttpServletRequest(ctx.servletContext)
        MockHttpServletResponse response = new GrailsMockHttpServletResponse()
        GrailsWebMockUtil.bindMockWebRequest(ctx, request, response)

        if (reqParams)
            request.setJSON(reqParams as JSON)
        request.contentType = 'application/json'
        request.method = 'POST'
        request.addHeader('SecurityKey', key)

        PersonCommand cmd = new PersonCommand()

        def params = request.getJSON()

        if(params['firstname'])
            cmd.setFirstname(params['firstname'])
        if(params['lastname'])
            cmd.setLastname(params['lastname'])
        if(params['username'])
            cmd.setUsername(params['username'])
        if(params['password'])
            cmd.setPassword(params['password'])

        cmd.validate()

        personController.createPerson(cmd)

        return [status: response.getStatus(), message: response.getJson()['message']]
    }

    void "One field is not filled."() {
        given:
            def msg = [firstname: 'testfirstname', lastname: 'testlastname', username: 'testusername']
            def result = mockWebRequest(msg, '1qaz!QAZ')
        expect:
            result.status == 500
            result.message == 'Following fields are incorrect or missing: password'
    }
    void "Two fields are not filled."() {
        given:
            def msg = [firstname: 'testfirstname', username: 'testusername']
            def result = mockWebRequest(msg, '1qaz!QAZ')
        expect:
            result.status == 500
            result.message == 'Following fields are incorrect or missing: password, lastname'
    }
    void "Three fields are not filled."() {
        given:
            def msg = [username: 'testusername']
            def result = mockWebRequest(msg, '1qaz!QAZ')
        expect:
            result.status == 500
            result.message == 'Following fields are incorrect or missing: password, firstname, lastname'
    }
    void "All fields are not filled."() {
        given:
            def msg = [:]
            def result = mockWebRequest(msg, '1qaz!QAZ')
        expect:
            result.status == 500
            result.message == 'Following fields are incorrect or missing: password, username, firstname, lastname'
    }
    void "User already exists."() {
        given:
            def msg = [firstname: 'testfirstname', lastname: 'testlastname', username: 'testuser', password: 'testpassword']
            def result = mockWebRequest(msg, '1qaz!QAZ')
        expect:
            result.status == 500
            result.message == 'User with username "testuser" already exists.'
    }
    void "New user succesfully created."() {
        given:
            def msg = [firstname: 'testfirstname', lastname: 'testlastname', username: 'testusername', password: 'testpassword']
            def result = mockWebRequest(msg, '1qaz!QAZ')
        expect:
            result.status == 200
            result.message == 'User "testusername" was created successfully.'
    }
    void "Access denied becauseof invalid security key."() {
        given:
            def msg = [firstname: 'testfirstname', lastname: 'testlastname', username: 'testusername', password: 'testpassword']
            def result = mockWebRequest(msg, 'invalidKey')
        expect:
            result.status == 401
            result.message == 'Access denied!'
    }
}
