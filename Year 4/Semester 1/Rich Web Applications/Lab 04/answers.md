Q.1
In React, props and state are used for storing and managing data within components.
Props, short for properties, are values that are passed to a React component from its parent component. They are used for configuring a component when it is being used, and they are often used for passing data from one component to another.
State, on the other hand, is used for storing and managing the data that is specific to a component, and it is only available to that component. It is used for storing and managing the component's internal, mutable data.

Q.2
In functional programming, a functor is a type of data structure that can be "mapped" over. In other words, it is a data structure that has a map() method that can be used to apply a function to each element in the data structure and produce a new data structure containing the results.
Functors are often used in functional programming as a way of abstracting over common data structures and operations on those data structures. This allows for more flexible and reusable code.

Q.3
Callbacks:
    Advantage: Callbacks are a simple and widely-used mechanism for handling asynchronous operations in JavaScript. They are easy to understand and implement, and they are supported by many existing libraries and APIs.
    Disadvantage: Callbacks can lead to what is known as "callback hell", where code becomes deeply nested and hard to read and maintain. This can happen when many asynchronous operations are chained together, each with its own callback function.
Promises:
    Advantage: Promises provide a more structured and elegant way of dealing with asynchronous operations in JavaScript. They allow for easier chaining and composition of asynchronous operations, and they make it easier to handle errors and exceptions.
    Disadvantage: Promises can be difficult to understand for developers who are new to asynchronous programming, and they require a different mental model than other mechanisms such as callbacks and synchronous code.
Streams:
    Advantage: Streams are a powerful mechanism for handling large amounts of data, such as data from a network connection or a file on disk. They allow data to be processed and consumed as it is being received, rather than having to wait for all the data to arrive before processing it. This can be more efficient and scalable.
    Disadvantage: Streams can be more difficult to work with than other mechanisms such as callbacks and promises, as they involve working with low-level APIs and dealing with complex data flows. They can also require more memory and CPU resources than other mechanisms, as data is buffered in memory as it is processed.

Q.4
The CSS Box Model is a fundamental concept in web design and development, and it describes the rectangular boxes that are generated for elements in the document object model (DOM). Each box has four regions: the content, the padding, the border, and the margin.
The content region is where the element's content, such as text or images, is displayed. The padding is the space around the content, and it is used to add space between the content and the border. The border is the line or area around the padding, and it is used to add a visual separation between the element and other elements on the page. The margin is the space around the border, and it is used to add space between the element and other elements on the page.

Q.5
When a user enters a URL into their web browser and hits enter, the following sequence of events occurs:
1 -	The browser sends a request to the web server for the specified URL.
2 -	The web server receives the request and looks up the specified URL in its file system or database.
3 -	If the URL is found, the web server sends a response back to the browser containing the contents of the requested page. This response may include HTML, CSS, JavaScript, and other resources such as images and videos.
4 -	The browser receives the response and begins rendering the HTML content of the page. As it encounters CSS and JavaScript, it will download and execute those resources as well.
5 -	As the CSS and JavaScript files are executed, they may modify the appearance and behavior of the page, adding interactivity and dynamic content.
6 -	The web application may make additional requests to the web server for data or other resources as needed.
7 -	The web application continues to run and respond to user input, updating the page and interacting with the web server as necessary.
This sequence of events is known as bootstrapping the web application, and it involves the browser downloading and executing the various resources that make up the application, and then using those resources to render and run the application. The initial URL serves as the entry point for the application, and all other resources and data are fetched and loaded as needed.
