
# Stack Exchange App - Diego Pizzo
This is an Android app that shows a list of users retrieved from Stack Exchange API.
The project was created using Android Studio 3.6.1.

**User Interface:**
This app has two views:
 - The first view shows an input edit text and a click button useful to retrieve the list of users by user name. After clicked on the button there are three scenarios:
	 - List of users that match a specific user name;
	 - an empty message;
	 - an error message.
Each user it is displayed with his reputation and user name wrapped inside a card view. In every search it is shown a progress bar that wait for the results from the back end.
 - The second view is shown when the user click on a specific user. It is shown a new activity with the details of the user selected. Each user has different Information such as profile image, user name, reputation, creation date and more. Also in this case it is present a progress bar that wait for the result. In this view is present two scenarios:
	 - the Information available of the user selected;
	 - an error message.

In both views I have chosen to build an activity with a fragment that wrap the main view so it will be possible to reuse it for others scenarios.

**Architecture:**
This project is based on MVVM pattern and written with Kotlin.

**Possible improvements:**
- In the first view it could be useful to add some filters provided by the back end, based on sorting and ordering.
- In both views should be better to implement a cache logic in order to avoid too many calls to the back end. Usually I have used Room to implement the cache or these libraries very useful:
	- [nytimes/store](https://github.com/nytimes/Store) (Deprecated but still working mostly if you use RXJava)
	- [dropbox/store](https://github.com/dropbox/store) (Perfect if you use Kotlin Coroutines)
- Improve the UI of the user details screen to make it prettier.
- Create a custom view in order to have a specific component reusable.

# Third-party libraries
**Koin**  
Koin is a framework for the dependency injection. I have chosen to use this framework because I think is more simple than Dagger2. Avoid writing many classes, reducing boilerplate and it's easier to understand.
Link: https://github.com/InsertKoinIO/koin

**Retrofit**  
HTTP client used the make API Requests and retrieve the data from network.
Link: https://square.github.io/retrofit/

**RxJava**  
It is useful to do async operations and react to events.
Link: https://github.com/ReactiveX/RxJava

**RESTMock**
Library useful to make it easy to test the API calls and mock in the simplest way possible in order to verify the expected results from requests.
Link: https://github.com/andrzejchm/RESTMock

**Glide**
Image loader for Android.
Link: https://github.com/bumptech/glide