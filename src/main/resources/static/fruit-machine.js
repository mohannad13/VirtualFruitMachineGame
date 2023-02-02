const playButton = document.getElementById("play-button");
const playerInput = document.getElementById("player-name");
const betInput = document.getElementById("bet-amount");
const resultDiv = document.getElementById("result");

playButton.addEventListener("click", function() {
const playerName = playerInput.value;
const betAmount = betInput.value;
if (!playerName || !betAmount) {
    alert("Player name and bet amount are required");
    return;
}
fetch("/fruit-machine/play?player=" + playerName + "&bet=" + betAmount)
    .then(response => {
        if (!response.ok) {
            return response.json().then(err => {
                throw new Error(err.error);
            });
        }
        return response.json();
    })
    .then(data => {
        let winMessage = '';
        if(data.selectedColors.length === 4 && new Set(data.selectedColors).size === 1){
            winMessage = "WOW! congratulations!! You won!";
        }else{
            winMessage = "You lost.";
        }
        resultDiv.innerHTML = winMessage + "<br>Colors: " + data.selectedColors.join(", ") + "<br>Balance: " + data.balance;
    })
    .catch(error => {
        alert(error.message);
    });
});
