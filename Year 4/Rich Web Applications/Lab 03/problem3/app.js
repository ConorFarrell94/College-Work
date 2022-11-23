const { Observable, fromEvent } = rxjs;

const main = document.querySelector("#main");
const addBtn = document.querySelector("#add");

rxjs.fromEvent(addBtn, "click").subscribe(() => addNote());

let noteTitle = document.getElementById("noteTitle");
let noteContent = document.getElementById("noteContent");
rxjs
	.fromEvent(noteTitle, "keyup")
	.subscribe(() => console.log(noteTitle.value));
rxjs
	.fromEvent(noteContent, "keyup")
	.subscribe(() => console.log(noteContent.value));

const addNote = () => {
	console.log(noteTitle.value);
	console.log(noteContent.value);

	// let noteTitle = prompt("enter note title")
	// let noteContent = prompt("note content :")
	const note = document.createElement("note-template");
	note.innerHTML = `
    <note-card name="${noteTitle.value}">
    <div slot="email">${noteContent.value}</div>
    </note-card>
    `;
	main.appendChild(note);
};

const template = document.createElement("template");
template.innerHTML = `
  <div class="note-card">
    <div>
      <h3></h3>
      <div class="info">
        <p><slot name="email" /></p>
      </div>
      <button id="addSubNote">Add subNote</button>
      <button id="toggle-info">Hide Info</button>
      <button id="removeNote">Remove Note</button>
    </div>
  </div>
`;

class noteTemplate extends HTMLElement {
	constructor() {
		super();

		this.showInfo = true;

		this.attachShadow({ mode: "open" });
		this.shadowRoot.appendChild(template.content.cloneNode(true));
		this.shadowRoot.querySelector("h3").innerText = this.getAttribute("name");
	}

	toggleInfo() {
		this.showInfo = !this.showInfo;

		const info = this.shadowRoot.querySelector(".info");
		const toggleBtn = this.shadowRoot.querySelector("#toggle-info");

		if (this.showInfo) {
			info.style.display = "block";
			toggleBtn.innerText = "Hide Info";
		} else {
			info.style.display = "none";
			toggleBtn.innerText = "Show Info";
		}
	}
	removeNote() {
		const removeButton = this.shadowRoot.querySelector("#removeNote");
		removeButton.parentElement.remove();
	}
	addSubnote() {
		const info = this.shadowRoot.querySelector(".info");
		// const subNote = this.shadowRoot.querySelector('#addSubNote')
		// let noteTitle = prompt("sub note title")
		let noteContent = prompt("content");
		console.log(noteTitle);
		console.log(noteContent);
		let html_to_insert = `
    <p><slot name="email" />${noteContent}</p>
    `;
		info.insertAdjacentHTML("beforeend", html_to_insert);

		// const note = document.createElement('note-template');
		// note.innerHTML = `
		// <note-card name="${noteTitle.value}">
		// <div slot="email">${noteContent.value}</div>
		// </note-card>
		// `
		// subNote.parentElement.appendChild(note);
	}

	connectedCallback() {
		this.shadowRoot
			.querySelector("#toggle-info")
			.addEventListener("click", () => this.toggleInfo());
		this.shadowRoot
			.querySelector("#removeNote")
			.addEventListener("click", () => this.removeNote());
		this.shadowRoot
			.querySelector("#addSubNote")
			.addEventListener("click", () => this.addSubnote());
	}

	disconnectedCallback() {
		this.shadowRoot.querySelector("#toggle-info").removeEventListener();
		this.shadowRoot.querySelector("#removeNote").removeEventListener();
	}
}

window.customElements.define("note-card", noteTemplate);
