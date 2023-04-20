const path = require("path");
const express = require("express");
const app = express();
const fs = require("fs");
const dataDir = path.join(__dirname, "country-objects");
const dataFiles = fs.readdirSync(dataDir);

let combinedData = [];

const start = performance.now();

dataFiles.forEach((file) => {
	const data = JSON.parse(fs.readFileSync(path.join(dataDir, file), "utf8"));
	combinedData = combinedData.concat(data);
});

const combinedJson = JSON.stringify(combinedData);

// Read the JSON file into a JavaScript object
const data = JSON.parse(combinedJson);
// Create an empty object to store the combined entries
const combinedData1 = {};

// Loop through the original object, and for each entry:
for (const entry of data) {
	const country = entry.country;
	for (const prop in entry) {
		if (prop !== "country") {
			// Check if an entry with the same "country" property already exists in the combined object
			if (!combinedData1[country]) {
				// If not, create a new entry with the "country" property and the current property as its value
				combinedData1[country] = { [prop]: entry[prop] };
			} else {
				// If it does, add the current property to the existing value
				combinedData1[country][prop] = entry[prop];
			}
		}
	}
}

// Create an array of objects, each with the properties except for "country"
const combinedArray = [];
for (const country in combinedData1) {
	const obj = { country: country };
	for (const prop in combinedData1[country]) {
		obj[prop] = combinedData1[country][prop];
	}
	combinedArray.push(obj);
}

// Convert the combined object back to JSON
const combinedJson1 = JSON.stringify(combinedArray, undefined, 2);

const end = performance.now();
const runtime = `${end - start} ms`;

app.use(express.static("public"));

app.get("/data", (req, res) => {
	const data = { message: combinedJson1, timeTaken: runtime };
	res.setHeader("Access-Control-Allow-Origin", "*");
	res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	res.setHeader("Access-Control-Allow-Headers", "Content-Type");
	res.json(data);
});

app.listen(3000, () => {
	console.log("Server listening on port 3000");
});
