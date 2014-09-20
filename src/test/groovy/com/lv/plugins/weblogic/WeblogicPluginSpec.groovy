package com.lv.plugins.weblogic

import com.lv.plugins.weblogic.tasks.WLDeployTask
import nebula.test.PluginProjectSpec
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration


/**
 * Created by Sion Williams on 14/08/2014.
 */
class WeblogicPluginSpec extends PluginProjectSpec {
    static final String PLUGIN_ID = 'com.lv.weblogic'

    @Override
    String getPluginName() {
        return PLUGIN_ID
    }

    def setup() {
        project.apply plugin: pluginName
    }

    def "apply creates weblogic configuration" () {
        expect: project.configurations.findByName('weblogic') instanceof Configuration
    }

    def "apply creates weblogic extension" () {
        expect: project.extensions.findByName('weblogic')
    }

    def "apply creates tasks of type WLDeployTask with default values"() {
        setup:
            Task task = project.tasks.findByName('wlDeploy')

        expect:
            task != null
            task.group == 'Weblogic'
            task.adminurl == 't3://localhost:7001'
            task.user == 'weblogic'
            task.password == 'welcome1'
            task.debug == false
            task.verbose == false
            task.remote == false
            task.upload == false
    }

    def "apply creates wlDeploy task" () {
        setup:
            Task task = project.tasks.findByName('wlDeploy')

        expect:
            task != null
            task instanceof WLDeployTask
            task.action == 'deploy'
            task.description == 'Deploys or redeploys an application or module.'
    }

    def "apply creates wlUndeploy task" () {
        setup:
            Task task = project.tasks.findByName('wlUndeploy')

        expect:
            task != null
            task instanceof WLDeployTask
            task.action == 'undeploy'
            task.description == 'Stops the deployment unit and removes staged files from target servers.'
    }

    def "apply creates wlCancel task" () {
        setup:
            Task task = project.tasks.findByName('wlCancel')

        expect:
            task != null
            task instanceof WLDeployTask
            task.action == 'cancel'
            task.description == 'Attempt to cancel a running deployment task.'
    }

    def "apply creates wlRedeploy task" () {
        setup:
            Task task = project.tasks.findByName('wlRedeploy')

        expect:
            task != null
            task instanceof WLDeployTask
            task.action == 'redeploy'
            task.description == 'Redeploys a running application or part of a running application.'
    }

    def "apply creates wlDistribute task" () {
        setup:
            Task task = project.tasks.findByName('wlDistribute')

        expect:
            task != null
            task instanceof WLDeployTask
            task.action == 'distribute'
            task.description == 'Prepares deployment files for deployment by copying deployment files to target servers and validating them.'
    }

    def "apply creates wlStart task" () {
        setup:
            Task task = project.tasks.findByName('wlStart')

        expect:
            task != null
            task instanceof WLDeployTask
            task.action == 'start'
            task.description == 'Makes a stopped (inactive) application available to clients on target servers.'
    }

    def "apply creates wlStop task" () {
        setup:
            Task task = project.tasks.findByName('wlStop')

        expect:
            task != null
            task instanceof WLDeployTask
            task.action == 'stop'
            task.description == 'Makes an application inactive and unavailable administration and client requests.'
    }
}
