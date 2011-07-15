package grailscheck

import grails.converters.JSON

class MyDomController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[myDomInstanceList: MyDom.list(params), myDomInstanceTotal: MyDom.count()]
	}

    def create = {
        def myDomInstance = new MyDom()
        myDomInstance.properties = params
        return [myDomInstance: myDomInstance]
    }

    def save = {
        def myDomInstance = new MyDom(params)
        if (myDomInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'myDom.label', default: 'MyDom'), myDomInstance.id])
            redirect(action: "show", id: myDomInstance.id)
        }
        else {
            render(view: "create", model: [myDomInstance: myDomInstance])
        }
    }

    def show = {
        def myDomInstance = MyDom.get(params.id)
        if (!myDomInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myDom.label', default: 'MyDom'), params.id])
            redirect(action: "list")
        }
        else {
            [myDomInstance: myDomInstance]
        }
    }

    def edit = {
        def myDomInstance = MyDom.get(params.id)
        if (!myDomInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myDom.label', default: 'MyDom'), params.id])
            redirect(action: "list")
        }
        else {
            return [myDomInstance: myDomInstance]
        }
    }

    def update = {
        def myDomInstance = MyDom.get(params.id)
        if (myDomInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (myDomInstance.version > version) {
                    
                    myDomInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'myDom.label', default: 'MyDom')] as Object[], "Another user has updated this MyDom while you were editing")
                    render(view: "edit", model: [myDomInstance: myDomInstance])
                    return
                }
            }
            myDomInstance.properties = params
            if (!myDomInstance.hasErrors() && myDomInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'myDom.label', default: 'MyDom'), myDomInstance.id])
                redirect(action: "show", id: myDomInstance.id)
            }
            else {
                render(view: "edit", model: [myDomInstance: myDomInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myDom.label', default: 'MyDom'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def myDomInstance = MyDom.get(params.id)
        if (myDomInstance) {
            try {
                myDomInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'myDom.label', default: 'MyDom'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'myDom.label', default: 'MyDom'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myDom.label', default: 'MyDom'), params.id])
            redirect(action: "list")
        }
    }
}
