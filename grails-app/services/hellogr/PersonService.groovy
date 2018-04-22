package hellogr

import grails.transaction.Transactional

@Transactional
class PersonService {

    def isLegal(request){
        request.getHeader("SecurityKey") == "1qaz!QAZ"
    }

    def createPerson(firstname, lastname, username, password){
        new Person(firstname: firstname, lastname: lastname, username: username, password: password).save(flush: true)
    }

}
