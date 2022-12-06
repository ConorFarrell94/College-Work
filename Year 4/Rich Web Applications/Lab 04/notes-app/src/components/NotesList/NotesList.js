import React from "react";
import "./NotesList.css";

const notesList = ({ notes, editNote, deleteNote, changeColor }) => {
	return notes.length
		? notes.map((note) => (
				<div className="note" id={note.id} key={note.id}>
					{note.note}
					<span className="note-edit" onClick={() => editNote(note.id)}>
						<button>Edit</button>
					</span>
					<span className="note-delete" onClick={() => deleteNote(note.id)}>
						<button>Delete</button>
					</span>
          			<span className="note-color" onClick={() => changeColor(note.id)}>
						<button>Color</button>
					</span>
				</div>
		  ))
		: null;
};

export default notesList;
