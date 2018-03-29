import hellogr.UserManPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userManPasswordEncoderListener(UserManPasswordEncoderListener, ref('hibernateDatastore'))
    userManPasswordEncoderListener(UserManPasswordEncoderListener, ref('hibernateDatastore'))
    userManPasswordEncoderListener(UserManPasswordEncoderListener, ref('hibernateDatastore'))
}
