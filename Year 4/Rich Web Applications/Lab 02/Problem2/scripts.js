async function titles_morethan() {
	try {
		let titles = await fetch("https://jsonplaceholder.typicode.com/posts")
			.then((res) => res.json())
			.then((res) => res.map((res) => res.title))
			.then((res) => res.filter((res) => res.split(" ").length > 6));
		// .then(json => console.log(json));
		console.log(JSON.stringify(titles, null, 2));
	} catch (error) {
		console.log(error);
	}
}
titles_morethan();

async function word_frequency() {
	try {
		let body = await fetch("https://jsonplaceholder.typicode.com/posts")
			.then((res) => res.json())
			.then((res) => res.map((res) => res.body));
		// console.log(body)

		// converting the response to a string to be able to clean and count
		// the frequency easier
		let one_string = body.toString();
		// let one_string = JSON.stringify(body);
		one_string = one_string.replace(/\\/g, " ");
		one_string = one_string.replace(/"/g, "");
		one_string = one_string.replace(/,/g, " ");
		// console.log(one_string)

		// splitting the string at every whitespace and then counting the words
		var words = one_string.split(/\s/);
		var frequency_map = {};
		words.forEach(function (word) {
			if (!frequency_map[word]) {
				frequency_map[word] = 0;
			}
			frequency_map[word] += 1;
		});
		console.log(frequency_map);
	} catch (error) {
		console.log(error);
	}
}
word_frequency();
