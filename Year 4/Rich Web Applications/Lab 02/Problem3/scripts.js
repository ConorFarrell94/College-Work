function removeElementsByClass(className) {
	const elements = document.getElementsByClassName(className);
	while (elements.length > 0) {
		elements[0].parentNode.removeChild(elements[0]);
	}
}
var form = document.getElementById("myForm");
form.addEventListener("submit", function (e) {
	try {
		var Table = document.getElementById("myTable");
		Table.innerHTML = "";
	} catch (error) {
		console.log("nothing to remove");
	}
	// removeElementsByClass("repo")
	removeElementsByClass("user");
	e.preventDefault();
	var search = document.getElementById("search").value;
	var originalName = search.split(" ").join("");
	fetch("https://api.github.com/users/" + originalName)
		.then((result) => result.json())
		.then((data) => {
			let img = data.avatar_url;
			let name = data.login;
			let email = data.email;
			let location = data.location;
			let numberGists = data.public_gists;
			let userTemplate = `<div class="user">
                                    <img src="${img}">
                                    <h4>Name</h4>
                                    <p>${name}</p>
                                    <h4>Email</h4>
                                    <p>${email}</p>
                                    <h4>Location</h4>
                                    <p>${location}</p>
                                    <h4>Number of Gists</h4>
                                    <p>${numberGists}</p>
                                </div>`;
			document
				.getElementById("userProfile")
				.insertAdjacentHTML("beforeend", userTemplate);

			var repoURL = data.repos_url;
			console.log(repoURL);
			fetch(repoURL)
				.then((result) => result.json())
				.then((data) => {
					let tableInit = `<thead>
                                     <tr>
                                        <th>Repo Name</th>
                                        <th>Description</th>
                                     </tr>
                                     </thead>
                                     <tbody id="tableContent">
                                     </tbody>`;
					document
						.getElementById("myTable")
						.insertAdjacentHTML("beforeend", tableInit);
					for (let i = 0; i < data.length; i++) {
						let title = data[i].name;
						let desc = data[i].description;
						let repoTemplate = `<tr class="repo">
                                                <td>${title}</td>
                                                <td>${desc}</td>
                                            </tr>`;
						document
							.getElementById("tableContent")
							.insertAdjacentHTML("beforeend", repoTemplate);
					}
					const x = document.getElementById("myTable").rows.length;
					console.log(x);
					if (x > 5) {
						document.getElementById("tableContent").style.maxHeight = "200px";
						document.getElementById("tableContent").style.overflow = "auto";
					}
				});
		});
});
