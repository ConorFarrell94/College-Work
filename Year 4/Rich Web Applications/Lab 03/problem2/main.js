const { interval, take, map } = rxjs

const countDiv = document.getElementById("countdown")

let subscription = null

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

submit.addEventListener('click', event => {

  let timeSeconds = getSec()
  let timeMin = getMin()
  let timeHour = getHours()

  timeHour = timeHour * 3600
  timeMin = timeMin * 60

  let durationInSeconds = Number(timeSeconds) + Number(timeMin) + Number(timeHour)


  subscription = interval(1000).pipe(take(durationInSeconds), map(count => durationInSeconds - count)).subscribe(countdown => {
    const hrs  = (~~(countdown / 3600)).toString()
    const mins = (~~((countdown % 3600) / 60)).toString()
    const secs = (~~countdown % 60).toString()
    countDiv.innerHTML = `${hrs.padStart(2, '0')}:${mins.padStart(2, '0')}:${secs.padStart(2, '0')}`;
    })
  }
)
cancel.addEventListener('click', event => {
  subscription.unsubscribe();
  countDiv.innerHTML = ""
}
)