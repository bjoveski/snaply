# snaply


## overview

Snaply is a tool that groups similar images together. It uses the [Clarifai](clarifai.com) API to generate the tags, and then the user can browse through their photos based on the tags.

## requisites

You'll need couple of things before you can use Snaply locally.

You'll need jdk, which you can download [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

You'll need maven, which you can download [here](https://maven.apache.org/download.cgi)

You'll need Clarifai app id & secret, which you'll when you create an [account](https://developer.clarifai.com/signup/). Add your id & secret as environmental variables, as Snaply needs them to access the API.

```
export CLARIFAI_APP_ID=my-id
export CLARIFAI_APP_SECRET=my-secret
```

And you should be all set!

