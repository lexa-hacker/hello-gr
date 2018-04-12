package hellogr

class Car {

    Long id
    Person person
    String model
    String version

    static mapping = {
        id generator: 'identity'
    }

    static constraints = {
    }
}
