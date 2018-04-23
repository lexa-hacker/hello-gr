package hellogr

import grails.transaction.Transactional

@Transactional
class PersonService {

    def findPerson(username){
        Person.findByUsername(username)
    }

    def createPerson(firstname, lastname, username, password){
        new Person(firstname: firstname, lastname: lastname, username: username, password: password).save(flush: true)
    }

}
