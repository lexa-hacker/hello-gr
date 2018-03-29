package hellogr

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UserManRole implements Serializable {

	private static final long serialVersionUID = 1

	UserMan userMan
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof UserManRole) {
			other.userManId == userMan?.id && other.roleId == role?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (userMan) {
            hashCode = HashCodeHelper.updateHash(hashCode, userMan.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static UserManRole get(long userManId, long roleId) {
		criteriaFor(userManId, roleId).get()
	}

	static boolean exists(long userManId, long roleId) {
		criteriaFor(userManId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long userManId, long roleId) {
		UserManRole.where {
			userMan == UserMan.load(userManId) &&
			role == Role.load(roleId)
		}
	}

	static UserManRole create(UserMan userMan, Role role, boolean flush = false) {
		def instance = new UserManRole(userMan: userMan, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(UserMan u, Role r) {
		if (u != null && r != null) {
			UserManRole.where { userMan == u && role == r }.deleteAll()
		}
	}

	static int removeAll(UserMan u) {
		u == null ? 0 : UserManRole.where { userMan == u }.deleteAll() as int
	}

	static int removeAll(Role r) {
		r == null ? 0 : UserManRole.where { role == r }.deleteAll() as int
	}

	static constraints = {
		role validator: { Role r, UserManRole ur ->
			if (ur.userMan?.id) {
				UserManRole.withNewSession {
					if (UserManRole.exists(ur.userMan.id, r.id)) {
						return ['userRole.exists']
					}
				}
			}
		}
	}

	static mapping = {
		id composite: ['userMan', 'role']
		version false
	}
}
