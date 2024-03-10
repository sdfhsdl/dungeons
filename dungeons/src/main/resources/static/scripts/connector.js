var chooseEnemy;
var isAttackClick = false;

let elementAttack;
let elementDefend;

let elementSkip;

let enemiesElements;
load();
function load(){
    elementAttack = document.getElementById("attack_button").addEventListener('click', function(){
        console.log('Успех');
        isAttackClick = true;
        setListenerByAttackButton();
});
elementDefend = document.getElementById("defend_button").addEventListener('click', setListenerByDefendButton);

elementSkip = document.getElementById("skip_button").addEventListener('click', setListenerBySkipButton);

enemiesElements = document.getElementsByClassName("enemies");
}

function setListenerBySkipButton(){
    console.log('Успех skip');
    update_fightersQueue('skip', 'yourself');
    update_activeFighter();
    update_userFighter();
}

function setListenerByAttackButton(){
    console.log(enemiesElements);
for(var i = 0; i < enemiesElements.length; i++){
    let d = enemiesElements[i];
    d.addEventListener('click', function(){
        if(isAttackClick){
            console.log("work")
            let nameFighter = getName(d);
            update_fightersQueue('attack', nameFighter);
            update_activeFighter();
            update_userFighter();
        }
    });
}
}
function setListenerByDefendButton(){
    console.log('Успех defend');
    update_fightersQueue('defend', 'yourself');
    update_activeFighter();
    update_userFighter();
}
function getName(enemy){
    nameFighter = enemy.getElementsByClassName("nameFighter")[0].innerHTML;
    return nameFighter;
}
function update_fightersQueue(arg_action, id_enemy){
    console.log("функция attack_click" + arg_action + id_enemy);
    var dataResponse = {arg : arg_action, id : id_enemy};
    console.log(dataResponse);
    $(document).ready(function(){
            $.ajax({
                type: 'post',
                url : "/update_fightersQueue-url",
                data : JSON.stringify(dataResponse),
                dateType: 'json',
                contentType: "application/json"
            }).done(function(header) { 
        $("#enemyBlocks").replaceWith(header); 
    });
    })
    isAttackClick = false;
}
function update_activeFighter(){
    $(document).ready(function(){
        $.ajax({
            type: 'post',
            url: '/update_activeFighter-url'
        }).done(function(header){
            $("#activeFighter").replaceWith(header);
        });
    })
}
function update_userFighter(){
    $(document).ready(function(){
        $.ajax({
            type: 'post',
            url: '/update_user_url'
        }).done(function(header){
            $("#userFighter").replaceWith(header);
            load();
        });
    })
}