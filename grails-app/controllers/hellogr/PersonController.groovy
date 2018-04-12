package hellogr

class PersonController {

    def list(){
        def personList = Person.findAll()
        [personList: personList]
    }

    def index() {
        redirect(action: "list")
    }

    def createPerson(PersonCommand cmd) {

        if(request.getHeader("SecurityKey") == "1qaz!QAZ") {
            if(cmd.hasErrors()){
                def errMsg = "Following fields are incorrect or missing: "
                cmd.errors.getFieldErrors().arguments.each{
                    if(cmd.errors.getFieldErrors().arguments.indexOf(it))
                        errMsg += ", "
                    errMsg += it[0]
                }
                render(status: 500, text: errMsg)
            }
            else{
                def newPerson
                try{
                    if(!Person.findByUsername(cmd.username))
                        newPerson = new Person(firstname: cmd.firstname, lastname: cmd.lastname, username: cmd.username, password: cmd.password).save(flush: true)
                    else
                        render(status: 500, text: "User with username \"${cmd.username}\" already exists.")
                }
                catch (Exception e){
                    render(status: 500, text: e.message)
                }
                if(newPerson)
                    render(status: 200, text: "User \"${newPerson.username}\" was created successfully.")
            }
        }
        else
            render(status: 401, text: "Access denied!")

    }

}

class PersonCommand {
    String firstname
    String lastname
    String username
    String password
}