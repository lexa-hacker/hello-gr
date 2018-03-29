package hellogr

class UserManController {

    def list(){
        def usersList = UserMan.findAll()
        [usersList: usersList]
    }

    def index() {
        redirect(action: "list")
    }
}
