package hellogr

class UserManController {

    def list(){
        def usersList = UserMan.findAll()
        [usersList: usersList]
    }

    def index() {
        redirect(action: "list")
    }

    def createMan(UserManCommand cmd) {

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
                def newUser
                try{
                    if(!UserMan.findByUsername(cmd.username))
                        newUser = new UserMan(firstname: cmd.firstname, lastname: cmd.lastname, username: cmd.username, password: cmd.password).save(flush: true)
                    else
                        render(status: 500, text: "User with username \"${cmd.username}\" already exists.")
                }
                catch (Exception e){
                    render(status: 500, text: e.message)
                }
                if(newUser)
                    render(status: 200, text: "User \"${newUser.username}\" was created successfully.")
            }
        }
        else
            render(status: 401, text: "Access denied!")

    }

}

class UserManCommand {
    String firstname
    String lastname
    String username
    String password
}