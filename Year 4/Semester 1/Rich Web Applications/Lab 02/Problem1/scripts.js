// this part enables the backspace key in the phone number input field, charcode for backspace is 8
// i've also allowed the left and right arrows to be used so as if a mistake is entered you can go back without using the mouse, charcodes 37 & 39
document.getElementById("phone").onkeydown = function (e) {
	var key = e.keyCode ? e.keyCode : e.which;
	if (isNaN(String.fromCharCode(key)) && key != 8 && key != 37 && key != 39)
		return false;
};
function search() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	document.getElementById("noResults").style.display = "none";
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");

	// looping through the table and hiding the ones that don't match the search
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[2];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
				var results = true;
			} else {
				tr[i].style.display = "none";
				var results = false;
			}
		}
	}
	if (results == false) {
		console.log(results);
		document.getElementById("noResults").style.display = "block";
	}
}
// getting all the rows and starting at the second one, messy, but it works
function changeColor() {
	var rows = document.querySelectorAll("#myTable tr");
	var odd = true;
	for (var i = 1; i < rows.length; i++) {
		if (rows[i].style.display !== "none") {
			// toggle between the two colors
			odd = !odd;
			rows[i].style.backgroundColor = odd ? "#000000" : "#f2f2f2";
			rows[i].style.color = odd ? "#ffffff" : "#000000"; // changing foreground color aswell so it's still legible
		}
	}
}
// sorting results once the name header/button is pressed
getCellValue = (tr, idx) =>
	tr.children[idx].innerText || tr.children[idx].textContent;
comparer = (idx, asc) => (a, b) =>
	((var1, var2) =>
		var1 !== "" && var2 !== "" && !isNaN(var1) && !isNaN(var2)
			? var1 - var2
			: var1.toString().localeCompare(var2))(
		getCellValue(asc ? a : b, idx),
		getCellValue(asc ? b : a, idx)
	);
document.querySelector("#nameHead").addEventListener("click", () => {
	const table = nameHead.closest("table");
	Array.from(table.querySelectorAll("tr:nth-child(n+2)"))
		.sort(
			comparer(
				Array.from(nameHead.parentNode.children).indexOf(nameHead),
				(this.asc = !this.asc)
			)
		)
		.forEach((tr) => table.appendChild(tr));
	changeColor();
});
