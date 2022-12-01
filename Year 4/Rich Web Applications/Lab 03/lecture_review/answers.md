# Q.1
Explain what is meant by the stream abstraction. What is the relationship between
streams and the observer pattern? What are streams useful for modeling and when
might you use them in Rich Web development?

# Q.2
Assume that you are building an interface to an API in your Rich Web App. Describe in
detail how you could use the RxJS library to handle asynchronous network responses to
API requests. In your opinion, what are the benefits to using a streams library for
networking over, say, promises? And what do you think are the downsides?

# Q.3
Consider three asynchronous tasks, A,B & C. What are the consequences of these
functions sharing global state? What is a good practice to alleviate any problems
associated with this?

<br>
<br>
<br>
<br>

# A.1
Streams are a sequence of values over time, Stream abstraction is the use of a stream to get data that has the potential to be present either now or in the future.
The observer pattern is event orientated so a stream, or stream of events, is suited to be used alongside it because the data may not be immediately available but it may become so in the future.
Streams can be extrememely useful in Rich Web development as they provide a way to handle both synchronous and asynchronous data. In scenarios such as when you are dealing with fetch requests, the response can be dealt with by exposing it as a ReadableStream which can in turn be read using ".getReader()".

# A.2
We can use RxJS to handle network responses by using observables. After making your observable from the network response we can map the response to the components we want using "pipe". The pipe operator take one observable as input and returns an observable as output to continue.
Some benefits to using streams to handle network responses can be in such cases where the response contains more than one value. Promises can only handle one value at a time so in these situations its best to use streams and observables.
The main downside to using RxJS is its steep learning curve and lack of documentation for any projects not using Angular although it is considered a "cleaner" and more efficient way of handle network responses. RxJS would be classified as "reactive programming" which can have many pitfalls when applied to JavaScript wherein problems caused by language featues can arise. This can cause a developer to resort to third-party libraries and workarounds that can prove to be tricky or not as efficient.

# A.3
The problem in this scenario may be when you want to run all 3 tasks and each task modifies the global variable it may not withold its state. We also shouldn't do this because it becomes extremely difficult to discover how and when the global variable changes which in turn makes it hard to extend the functionality or use of the variable.
A way to help with this problem is the put your global variable into a single class and share the class instead of the variable. Now when you want to share it you have only 1 state of the variable to share and you will be extending your program by creating another instance of said class.