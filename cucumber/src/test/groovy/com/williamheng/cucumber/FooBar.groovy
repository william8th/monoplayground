package com.williamheng.cucumber

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

class TestWorld {
    //
}

World {
    new TestWorld()
}


Given(/^a cucumber test$/) { ->
    println("Do nothing")
}

When(/^this line runs$/) { ->
    println("Line is running")
}

Then(/^computing cycles are wasted$/) { ->
    println("Electricity is wasted too!")
}
