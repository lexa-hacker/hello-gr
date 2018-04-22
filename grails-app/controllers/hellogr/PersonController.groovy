package hellogr

import grails.converters.JSON
import hellogr.commands.PersonCommand

class PersonController {
    def personService

    def list(){
        def personList = Person.findAll()
        [personList: personList]
    }

    def index() {
        redirect(action: "list")
    }

    def createPerson(PersonCommand cmd) {

        if(personService.isLegal(request)) {
            if(cmd.hasErrors()){
                String errMsg = "Following fields are incorrect or missing: "
                cmd.errors.getFieldErrors().arguments.eachWithIndex{it, i ->
                    if(i) errMsg += ", "
                    errMsg += it[0]
                }
                render(status: 500, contentType: "application/json", [message: errMsg] as JSON)
            }
            else{
                def newPerson
                try{
                    if(!Person.findByUsername(cmd.username))
                        newPerson = personService.createPerson(cmd.firstname,cmd.lastname,cmd.username,cmd.password)
                    else
                        render(status: 500, contentType: "application/json", [message: "User with username \"${cmd.username}\" already exists." as String] as JSON)
                }
                catch (Exception e){
                    render(status: 500, contentType: "application/json", [message: e.message] as JSON)
                }
                if(newPerson)
                    render(status: 200,  contentType: "application/json", [message: "User \"${newPerson.username}\" was created successfully." as String] as JSON)
            }
        }
        else
            render(status: 401, contentType: "application/json", [message: "Access denied!"] as JSON)
    }

}

/*class PersonCommand {
    String firstname
    String lastname
    String username
    String password
}*/