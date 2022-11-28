
const H = document.getElementById('H');
const M = document.getElementById('M');
const S = document.getElementById('S');


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

hours.addEventListener('change', event => {
    console.log(getHours())
})
mins.addEventListener('change', event => {
    console.log(getMin())
})
seconds.addEventListener('change', event => {
    console.log(getSec())
})


const timerStopBtn = document.createElement('BUTTON');
timerStopBtn.textContent = 'Timer Stop';
document.body.appendChild(timerStopBtn);

submit.addEventListener('click', event => {

    console.log("submit")

    const timeSeconds = getSec()
    const timeMin = getMin()
    const timeHour = getHours()
    Rx.Observable
    .timer(1000, 1000) // timer(firstValueDelay, intervalBetweenValues)
    .take(timeSeconds)
    .map((i)=>(timeSeconds-1)-i)
    .take(timeSeconds + 1)
    .subscribe(i => S.innerHTML = i);
    Rx.Observable
    .timer(1000, 60000) // timer(firstValueDelay, intervalBetweenValues)
    .take(timeMin)
    .map((i)=>(timeMin-1)-i)
    .take(timeMin + 1)
    .subscribe(i => M.innerHTML = i);
    Rx.Observable
    .timer(1000, 3600000) // timer(firstValueDelay, intervalBetweenValues)
    .take(timeHour)
    .map((i)=>(timeHour-1)-i)
    .take(timeHour + 1)
    .subscribe(i => H.innerHTML = i);

    // const timeSeconds = getSec()
    // var timerS = Rx.Observable.interval(1000) // 1000 = 1 second
    // timerS
    //   .take(timeSeconds)
    //   .map((v)=>(timeSeconds-1)-v) // to reach zero
    //   .subscribe((v)=>S.innerHTML = v)

    // const timeMin = getMin()
    // var timerM = Rx.Observable.interval(60000) // 1000 = 1 second
    // timerM
    //   .take(timeMin)
    //   .map((v)=>(timeMin-1)-v) // to reach zero
    //   .subscribe((v)=>M.innerHTML = v)

    // const timeHour = getHours()*3600
    // var timerH = Rx.Observable.interval(1000) // 1000 = 1 second
    // timerH
    //   .take(timeHour)
    //   .map((v)=>(timeHour-1)-v) // to reach zero
    //   .subscribe((v)=>H.innerHTML = v)

})


