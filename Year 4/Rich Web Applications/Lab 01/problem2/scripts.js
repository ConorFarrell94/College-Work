(function () {
	var app = {
		noteEditor: document.getElementById("noteTaker"),
		noteEditorTitle: document.getElementById("noteTaker-title"),
		title: document.getElementById("title"),
		message: document.getElementById("message"),
		color: document.getElementById("color"),
		addButton: document.getElementById("add-btn"),
		errorDisplay: document.getElementById("error"),
		deleteButton: document.querySelector(".delete"),
		editButton: document.querySelector(".edit"),
		notesSection: document.getElementById("notesSection"),
		notes: document.getElementById("notes"),
		editMode: false,

		init: function () {
			app.title.addEventListener("keypress", app.detectInput);
			app.message.addEventListener("keypress", app.detectInput);
			app.addButton.addEventListener("click", app.createNote);
		},

		createNote: function () {
			var note = new Object();

			note.title = app.title.value;
			note.message = app.message.value;
			note.color = app.color.value;

			app.addNote(note);
		},
		addNote: function (note) {
			var li = document.createElement("li"),
				deleteBtn = document.createElement("span"),
				editBtn = document.createElement("span"),
				title = document.createElement("span"),
				message = document.createElement("span"),
				footer = document.createElement("footer");

			deleteBtn.className = "delete";
			deleteBtn.innerHTML = "Delete";
			deleteBtn.addEventListener("click", app.deleteNote);
			title.className = "noteTaker-title";
			title.innerHTML = note.title;
			message.className = "noteMessage";
			message.innerHTML = note.message;
			editBtn.className = "edit";
			editBtn.innerHTML = "Edit";
			editBtn.addEventListener("click", app.editNote);
			footer.appendChild(editBtn);
			footer.appendChild(deleteBtn);
			li.className = note.color;
			li.appendChild(deleteBtn);
			li.appendChild(title);
			li.appendChild(message);
			li.appendChild(footer);

			app.notes.prepend(li);
		},
		editNote: function () {
			var li,
				title,
				message,
				color,
				note = new Object();

			li = this.parentNode.parentNode;
			for (var i = 0; i < li.childNodes.length; i++) {
				if (li.childNodes[i].className === "note-title") {
					title = li.childNodes[i].innerText;
				}
			}
			for (var i = 0; i < li.childNodes.length; i++) {
				if (li.childNodes[i].className === "noteMessage") {
					message = li.childNodes[i].innerText;
				}
			}
			color = li.getAttribute("class");
			note.title = title;
			note.message = message;
			note.color = color;

			app.openNote(note);
		},
		openNote: function (note) {
			if (!app.editMode) {
				app.noteEditor.classList.add("hide");
				app.notesSection.classList.add("hide");

				setTimeout(function () {
					app.noteEditorTitle.innerText = "Edit Note";
					app.addButton.innerText = "Done";
					app.addButton.removeEventListener("click", app.createNote);
					app.addButton.addEventListener("click", app.saveNote);
					app.title.value = note.title;
					app.message.value = note.message;
					app.color.value = note.color;
					app.noteEditor.classList.remove("hide");
					app.editMode = true;
				}, 200);
			} else {
				return;
			}
		},
		saveNote: function () {
			app.createNote();

			app.noteEditor.classList.add("hide");
			app.notesSection.classList.add("hide");
		},
		deleteNote: function () {
			this.parentNode.remove();
		},
	};
	app.init();
})();
