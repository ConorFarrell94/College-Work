import React from 'react';
import './NoteInput.css';

const noteInput = ({ onChange, onSubmit, value }) => {

  const handleChange = (e) => {
    onChange(e);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    if(value.length) {
      onSubmit(value)
    }
  }

  return (
    <form className="note-input" onSubmit={handleSubmit}>
      <textarea placeholder="Add a note . . ." onChange={handleChange} value={value}></textarea>
      <button>Save</button>
    </form>
  )
}

export default noteInput;