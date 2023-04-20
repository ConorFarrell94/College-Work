function selectedHighlight() {
	// Add click event listeners to all table cells
	$("td").click(function () {
		// Remove the "selected" class from any previously selected cell
		$("td.selected").removeClass("selected");
		// Add the "selected" class to the clicked cell
		$(this).addClass("selected");
	});
}

function createFullTable(myArray) {
	let tableBody = document.querySelector("#myTable tbody");
	tableBody.innerHTML = "";
	// Loop through each object in the array
	myArray.forEach((obj) => {
		// Create a new row element
		let row = document.createElement("tr");

		let countryCell = document.createElement("td");
		countryCell.textContent = obj.country;
		row.appendChild(countryCell);

		let cityCell = document.createElement("td");
		cityCell.textContent = obj.city;
		row.appendChild(cityCell);

		let continentCell = document.createElement("td");
		continentCell.textContent = obj.continent;
		row.appendChild(continentCell);

		let coastlineCell = document.createElement("td");
		coastlineCell.textContent = obj.costline;
		row.appendChild(coastlineCell);

		let currencyCell = document.createElement("td");
		currencyCell.textContent = obj.currency_name;
		row.appendChild(currencyCell);

		let tldCell = document.createElement("td");
		tldCell.textContent = obj.tld;
		row.appendChild(tldCell);

		let imgCell = document.createElement("td");
		let img = document.createElement("img");
		img.src = obj.flag_base64;
		imgCell.appendChild(img);
		row.appendChild(imgCell);

		// Add the row to the table body
		tableBody.appendChild(row);
	});
	selectedHighlight();
}

function createTwentyTable(myArray) {
	let tableBody = document.querySelector("#myTable tbody");
	tableBody.innerHTML = "";
	let counter = 0;

	// Loop through each object in the array
	for (let i = 0; i < myArray.length && counter < 20; i++) {
		// Create a new row element
		let row = document.createElement("tr");

		let countryCell = document.createElement("td");
		countryCell.textContent = myArray[i].country;
		row.appendChild(countryCell);

		let cityCell = document.createElement("td");
		cityCell.textContent = myArray[i].city;
		row.appendChild(cityCell);

		let continentCell = document.createElement("td");
		continentCell.textContent = myArray[i].continent;
		row.appendChild(continentCell);

		let coastlineCell = document.createElement("td");
		coastlineCell.textContent = myArray[i].costline;
		row.appendChild(coastlineCell);

		let currencyCell = document.createElement("td");
		currencyCell.textContent = myArray[i].currency_name;
		row.appendChild(currencyCell);

		let tldCell = document.createElement("td");
		tldCell.textContent = myArray[i].tld;
		row.appendChild(tldCell);

		let imgCell = document.createElement("td");
		let img = document.createElement("img");
		img.src = myArray[i].flag_base64;
		imgCell.appendChild(img);
		row.appendChild(imgCell);

		// Add the row to the table body
		tableBody.appendChild(row);

		counter++;
	}
	selectedHighlight();
}

// Wait for the page to load
$(document).ready(function () {
	// Attach a click event to the button with id "toggle-mode-button"
	$("#toggle-mode-button").click(function () {
		var currentColor = $("body").css("background-color");

		// Toggle between light and dark mode based on the current background color
		if (currentColor === "rgb(255, 255, 255)") {
			$("body").css("background-color", "#222");
			$("body").css("color", "#fff");
		} else {
			$("body").css("background-color", "#fff");
			$("body").css("color", "#222");
		}
	});
	$("#animate-button").click(function () {
		var element = $("#myTable");
		element.hide();
		// Fade in the element over 1 second
		element.fadeIn(2500);
	});
	$("#increase-font-button").click(function () {
		var element = $("#body");

		var currentFontSize = parseInt(element.css("font-size"));
		// Increase the font size by 2 pixels
		currentFontSize += 2;

		// Set the new font size of the element
		element.css("font-size", currentFontSize + "px");
	});
	$("#decrease-font-button").click(function () {
		var element = $("#body");

		var currentFontSize = parseInt(element.css("font-size"));
		// Increase the font size by 2 pixels
		currentFontSize += -2;

		// Set the new font size of the element
		element.css("font-size", currentFontSize + "px");
	});
	$("#toggle-div-button").click(function () {
		var divElement = $("#headerInfo");
		// Toggle the visibility of the div element
		divElement.toggle();
	});
	$("#change-font-color-button").click(function () {
		var element = $("#body");

		// Generate a random color value
		var color = getRandomColor();

		// Set the new font color of the element
		element.css("color", color);
	});
	// Helper function to generate a random color value
	function getRandomColor() {
		var letters = "0123456789ABCDEF";
		var color = "#";
		for (var i = 0; i < 6; i++) {
			color += letters[Math.floor(Math.random() * 16)];
		}
		return color;
	}
});
