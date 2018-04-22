package hellogr

class Car {

    Long id
    Person person
    String model
    Integer version

    static mapping = {
        id generator: 'identity'
    }

    static constraints = {
    }
}
