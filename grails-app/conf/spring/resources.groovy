import hellogr.PersonPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    personPasswordEncoderListener(PersonPasswordEncoderListener, ref('hibernateDatastore'))
    personPasswordEncoderListener(PersonPasswordEncoderListener, ref('hibernateDatastore'))
    personPasswordEncoderListener(PersonPasswordEncoderListener, ref('hibernateDatastore'))
}
