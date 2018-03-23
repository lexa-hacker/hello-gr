package hellogr

class User_Controller {

    def list(){
        def users_list = User_.findAll()
        [users_list: users_list]
    }

    def index() { }
}
