package nl.nmc

import org.codehaus.groovy.grails.commons.ApplicationHolder

class Project {
    String name
    String description
    Date dateCreated
    Date lastUpdated

    static constraints = {
        description(nullable: true, blank: true)
    }

    static transients = ['datas','settings']

    String toString() {
        return name
    }

    String nameOfDirectory() {
        def location = "${ApplicationHolder.getApplication().getParentContext().getResource('/')}/${this.id.encodeAsBase64()}/"
        new File(location).mkdirs()

        return location
    }

    List getDatas() {
        return Data.findAllByProject(this)
    }
    List getSettings(){
        return Settings.findAllByProject(this)
    }
}