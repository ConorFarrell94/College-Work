import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { v4 as uuid } from "uuid";
import './index.css';
import NoteInput from './components/NoteInput/NoteInput';
import NotesList from './components/NotesList/NotesList';

var i = 0;

class App extends Component {


  state = {
    value: '',
    notes: [
      {
        id: uuid()
      }
    ]
  }

  componentDidMount() {
    const notes = JSON.parse(localStorage.getItem('notes')) || [...this.state.notes]
    this.setState({
      notes: notes
    })
  }

  onChange = (e) => {
    this.setState({
      value: e.target.value,
      color: e.target.color
    })
  }

  onSubmit = (value) => {
    this.setState({
      notes: [
        ...this.state.notes,
        {
          id:uuid(),
          note: value
        }
      ],
      value: ''
    }, () => localStorage.setItem('notes', JSON.stringify(this.state.notes)));
    document.querySelector('textarea').focus();
    console.log(value);
  }

  editNote = id => {
    const currentNote = this.state.notes.filter(note => note.id === id);
    this.setState({
      value: currentNote[0].note
    }, () => this.deleteNote(currentNote[0].id));
    document.querySelector('textarea').focus();
  }

  deleteNote = id => {
    const notes = this.state.notes.filter(note => note.id !== id);
    this.setState({
      notes
    }, () => localStorage.setItem('notes', JSON.stringify(this.state.notes)))
  }

  changeColor = id => {
    var colorsArray = new Array("red", "green", "cornflowerblue");
    if (i >= colorsArray.length - 1) {
      i = 0
    }
    else {
      i ++;
    }
    const currentNote = this.state.notes.filter(note => note.id === id);
    console.log(currentNote[0].id + " | color changed to : " + colorsArray[i])
    document.getElementById(currentNote[0].id).style.backgroundColor=colorsArray[i];
  }

  render() {
    return (
      <div className="App">
        <h1>Notes App</h1>
        <NoteInput
          onSubmit={this.onSubmit}
          onChange={this.onChange}
          value={this.state.value}
        />
        <NotesList
          notes={this.state.notes}
          deleteNote={this.deleteNote}
          editNote={this.editNote}
          changeColor={this.changeColor}
        />
      </div>
    )
  }
}

ReactDOM.render(<App />, document.getElementById('root'));