package hellogr

class Car {

    Long id
    Integer user_id
    String model
    String version

    static mapping = {
        id generator: 'identity'
    }

    static constraints = {
    }
}
