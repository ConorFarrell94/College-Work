const path = require("path");
const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const fs = require("fs");

const port = 3000;

app.get("/data/:id", (req, res) => {
	const id = req.params.id;
	fs.readFile("data.json", (err, data) => {
		if (err) {
			console.error(err);
			res.status(500).send("Error reading data");
		} else {
			const jsonData = JSON.parse(data);
			const foundData = jsonData.find((d) => d.id === id);
			if (foundData) {
				res.send(foundData);
			} else {
				res.status(404).send("Data not found");
			}
		}
	});
});

app.put("/data/:id", (req, res) => {
	const id = req.params.id;
	const newData = req.body;
	fs.readFile("data.json", (err, data) => {
		if (err) {
			console.error(err);
			res.status(500).send("Error reading data");
		} else {
			const jsonData = JSON.parse(data);
			const index = jsonData.findIndex((d) => d.id === id);
			if (index >= 0) {
				jsonData[index] = { ...jsonData[index], ...newData };
				fs.writeFile("data.json", JSON.stringify(jsonData), (err) => {
					if (err) {
						console.error(err);
						res.status(500).send("Error saving data");
					} else {
						res.send("Data updated successfully");
					}
				});
			} else {
				res.status(404).send("Data not found");
			}
		}
	});
});

app.delete("/data/:id", (req, res) => {
	const id = req.params.id;
	fs.readFile("data.json", (err, data) => {
		if (err) {
			console.error(err);
			res.status(500).send("Error reading data");
		} else {
			const jsonData = JSON.parse(data);
			const index = jsonData.findIndex((d) => d.id === id);
			if (index >= 0) {
				jsonData.splice(index, 1);
				fs.writeFile("data.json", JSON.stringify(jsonData), (err) => {
					if (err) {
						console.error(err);
						res.status(500).send("Error saving data");
					} else {
						res.send("Data deleted successfully");
					}
				});
			} else {
				res.status(404).send("Data not found");
			}
		}
	});
});

app.get("/data", (req, res) => {
	fs.readFile("data.json", (err, data) => {
		if (err) {
			console.error(err);
			res.status(500).send("Error reading data");
		} else {
			const jsonData = JSON.parse(data);
			res.send(jsonData);
		}
	});
});

app.use(bodyParser.json());

app.post("/save", (req, res) => {
	const data = JSON.stringify(req.body);
	fs.writeFile("data.json", data, (err) => {
		if (err) {
			console.error(err);
			res.status(500).send("Error saving data");
		} else {
			res.send("Data saved successfully");
		}
	});
});

// set index.html as the default page
app.get("/", (req, res) => {
	res.sendFile(path.join(__dirname, "index.html"));
});

app.listen(port, () => {
	console.log(`Server listening at http://localhost:${port}`);
});
