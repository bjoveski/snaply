# snaply
[![Build Status](https://travis-ci.org/bjoveski/snaply.svg?branch=master)](https://travis-ci.org/bjoveski/snaply)


## Overview

Snaply is a tool that let's you explore your photo gallery. It groups similar photos together, based on the tags provided by [Clarifai](clarifai.com).
This tool is just a for-fun project, mostly for exploring the Clarifai possibilities and experimenting with the state-of-the-art swing UI framework.

## Prerequisites

You'll need couple of things before you can use Snaply locally.

* jdk, which you can download [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

* scala, which you can download [here](http://www.scala-lang.org/download/)

* maven, which you can download [here](https://maven.apache.org/download.cgi)

* Clarifai app id & secret, which can get when you create an [account](https://developer.clarifai.com/signup/). Add your id & secret as environmental variables, as Snaply needs them to access the API.

```
export CLARIFAI_APP_ID=my-id
export CLARIFAI_APP_SECRET=my-secret
```

And you should be all set!

## Future work

* add a cache so we don't call the API every time we start the app
* add a way to choose tags
* add a way to go back to previous image
