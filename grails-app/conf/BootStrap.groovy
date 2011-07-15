package com.bill

class BootStrap {
    def springSecurityService

    def init = {

        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: springSecurityService.encodePassword('admin'),
                enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            SecUserSecRole.create adminUser, adminRole
        }
        
    }
}
