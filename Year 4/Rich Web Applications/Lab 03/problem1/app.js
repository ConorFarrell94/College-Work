const addBtn = document.querySelector("#add")
const main = document.querySelector("#main")
// addBtn.addEventListener("click",function() { addNote() })

rxjs.fromEvent(addBtn, 'click')
    .subscribe(() => addNote());

const changeColor = (x) => {
    x.parentElement.style.backgroundColor=x.value
    console.log(x.value)
    saveNotes()
}
const saveNotes = () => {
    const notes = document.querySelectorAll(".note textarea");
    console.log(notes);
    const data = [];
    notes.forEach((note) => { data.push(note.value) } )
    if (data.length === 0) {
        localStorage.removeItem("notes")
    } else {
        localStorage.setItem("notes", JSON.stringify(data))
    }
}
const addNote = (text = "") => {
    const note = document.createElement("div");
    note.classList.add("note")
    note.innerHTML = `
    <div class="tool">
        <label>Color</label>
        <select id="color" name="color" onchange="changeColor(this)">
        <option value="red">Red</option>
        <option value="green">Green</option>
        <option value="blue">Blue</option>
        </select>
        <button class="save">save</button>
        <button class="bin">bin</button> 
    </div>
    <textarea>${text}</textarea>
    `;

    let bin = note.querySelector(".bin")
    let save = note.querySelector(".save")
    let textarea = note.querySelector("textarea")

    rxjs.fromEvent(bin, 'click')
        .subscribe( () => note.remove())
    rxjs.fromEvent(bin, 'click')
        .subscribe( () => saveNotes())

    rxjs.fromEvent(save, 'click')
        .subscribe( () => saveNotes())

    rxjs.fromEvent(textarea, 'focusout')
        .subscribe( () => saveNotes())

    // note.querySelector(".bin").addEventListener(
    //     "click",
    //     function() {
    //         note.remove()
    //         saveNotes()
    //     }
    // )
    // note.querySelector(".save").addEventListener(
    //     "click",
    //     function() {
    //         saveNotes()
    //     }
    // )
    // note.querySelector("textarea").addEventListener(
    //     "focusout",
    //     function() {
    //         saveNotes()
    //     }
    // )

    main.appendChild(note);
    saveNotes()
}


(
    function() {
        const lsNotes = JSON.parse(localStorage.getItem("notes"));
        if (lsNotes === null) {
            addNote()
        } else {
            lsNotes.forEach(
                (lsNote) => {
                    addNote(lsNote)
                }
            )
        }

    }
)()