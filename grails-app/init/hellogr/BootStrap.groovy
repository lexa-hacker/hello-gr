package hellogr

class BootStrap {

    def init = { servletContext ->

        def makeUser = {role ->
            def newUser = new UserMan(firstname: 'Vasya', lastname: 'Pupkin', username: 'testuser', password: 'qwerty').save(flush: true)
            UserManRole.create(newUser, role, true)
            println "****************New user with username: ${newUser.username} was created."
        }

        def roleAdmin = Role.findByAuthority('ROLE_ADMIN')
        if(roleAdmin){
            println '****************Role Admin already exists.'
            def userRole = UserManRole.findByRole(roleAdmin)
            if(userRole)
                println '****************At least one user with role Admin already exists.'
            else
                makeUser(roleAdmin)
        } else {
            roleAdmin = new Role(authority: 'ROLE_ADMIN').save(flush: true)
            println '****************Role Admin was created.'
            makeUser(roleAdmin)
        }
    }
    def destroy = {
    }
}
