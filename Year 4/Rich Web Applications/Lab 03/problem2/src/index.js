import _, { debounce } from 'lodash';
const {Observable, fromEvent, debounceTime} = require('rxjs')

// const wrapArrayIntoObservable = arr => {
//     return new Observable(subscriber => {
//         for(let item of arr) {
//             subscriber.next(item);
//         }
//     });
// }

// const data = [1,2,3,4,5];

// const observable = wrapArrayIntoObservable(data);

// observable.subscribe(val => console.log("Subsciber: " + val))


// function component() {
//     const element = document.createElement('div');

//     // Lodash, currently included via a script, is required for this line to work
//     element.innerHTML = _.join(['Hello', 'webpack'], ' ');

//     return element;
//   }

//   document.body.appendChild(component());


const form = document.createElement("div");
form.classList.add("form")
form.innerHTML = `
<form id="userInput">
<input type="number" id="hours" class="inputBox" placeholder="H" min="0">
<span class="inputSep" style="font-size: xx-large;">:</span>
<input type="number" id="mins" class="inputBox" placeholder="M" min="0">
<span class="inputSep" style="font-size: xx-large;">:</span>
<input type="number" id="seconds" class="inputBox" placeholder="S" min="0">
</form>
<input type="submit" value="Start Countdown" id="submit">
`;
document.body.appendChild(form);

function getHours() {
    let hours = document.getElementById('hours').value;
    return hours;
}
function getMin() {
    let mins = document.getElementById('mins').value;
    return mins;
}
function getSec() {
    let seconds = document.getElementById('seconds').value;
    return seconds;
}


// const hoursValue = {
//     subscribe: observer => {
//       hours.addEventListener("click", event => {
//         observer.next(event.clientX)
//       })
//     },
//     pipe: operator => {

//     },
//   }


hours.addEventListener('change', event => {
    console.log(getHours())
})
mins.addEventListener('change', event => {
    console.log(getMin())
})
seconds.addEventListener('change', event => {
    console.log(getSec())
})
submit.addEventListener('click', event => {
    console.log("Hours: " + getHours());
    console.log("Minutes: " + getMin());
    console.log("Seconds: " + getSec());
})

timer$ = timer(0, 1000).pipe(
    scan(acc => --acc, 120),
    takeWhile(x => x >= 0)
  )