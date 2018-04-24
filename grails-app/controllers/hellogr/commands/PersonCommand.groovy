package hellogr.commands
import grails.validation.Validateable
class PersonCommand implements Validateable {
    String firstname
    String lastname
    String username
    String password

    static constraints = {
        password  blank: false, password: true
        username  blank: false, unique: true
        firstname blank: false
        lastname  blank: false
    }
}