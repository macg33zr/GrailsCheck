package grailscheck

class MyDom {
    
    String name
    String description

    static constraints = {
        
        name()
        description()
    }
}
