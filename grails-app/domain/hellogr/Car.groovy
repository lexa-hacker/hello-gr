package hellogr

class Car {

    Long id
    UserMan userMan
    String model
    String version

    static mapping = {
        id generator: 'identity'
    }

    static constraints = {
    }
}
