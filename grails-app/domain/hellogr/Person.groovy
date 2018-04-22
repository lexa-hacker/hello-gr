package hellogr

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Person implements Serializable {

	private static final long serialVersionUID = 1

	Long id
	String firstname
	String lastname
	Integer version

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		(PersonRole.findAllByPerson(this) as List<PersonRole>)*.role as Set<Role>
	}

	static constraints = {
		password  blank: false, password: true
		username  blank: false, unique: true
		firstname blank: false
		lastname  blank: false
	}

	static mapping = {
		id generator: 'identity'
		password column: '`password`'
	}
}
