# Observable
This is a thread scheduling library, time-consuming operation in the child thread, the main thread to update the UI

## Sample
	```java
	Observable.create(new OnSubscribe<String>() {
            @Override
            public void call(SubscriberDelegate<? super String> subscriber) throws Exception {
                Log.i(TAG,"The current thread name[" + Thread.currentThread().getName() + "].");
                try{
                    subscriber.onStart();
                    subscriber.onCompleted("Success!");
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onError(Throwable error) {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onCompleted(String t) {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }
        });

        // simple
        Observable.create(new OnSubscribeImpl<String>() {
            @Override
            public String execute() throws Exception {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
                return "Success";
            }
        }).subscribe(new SubscriberHandler<String>() {
            @Override
            public void onSuccess(String response) throws Exception {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            protected void onFinally(String s) {
                super.onFinally(s);
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }
        });```

## Jar

  <a href="https://github.com/devsoulwolf/Observable/blob/master/observable-1.0.0.jar" target="_blank">Download last version Jar</a>

## Maven
	<dependency>
  	    <groupId>net.soulwolf.widget</groupId>
		<url>https://dl.bintray.com/soulwolf/maven</url>
  	    <artifactId>Observable</artifactId>
  	    <version>1.0.0</version>
	</dependency>
## Gradle
	allprojects {
       repositories {
          jcenter()
          maven{
            url "https://dl.bintray.com/soulwolf/maven"
          }
       }
	}
	
	compile 'net.soulwolf.widget:Observable:1.0.0'

## Developed by
 Ching Soulwolf - <a href='javascript:'>Ching.Soulwolf@gmail.com</a>


## License
	Copyright 2015 Soulwolf Ching
	Copyright 2015 The Android Open Source Project for Observable
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	




