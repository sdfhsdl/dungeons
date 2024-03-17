var chooseEnemy;
var isAttackClick = false;

let elementAttack;
let elementDefend;

let elementSkip;

let enemiesElements;
checkUserMove();
function load(botOrUserMove){
    if(botOrUserMove){
        setActiveButtons(false);
        console.log("Ход User")
    elementAttack = document.getElementById("attack_button").addEventListener('click', function(){
        console.log('Успех');
        isAttackClick = true;
        setListenerByAttackButton();
});
elementDefend = document.getElementById("defend_button").addEventListener('click', setListenerByDefendButton);

elementSkip = document.getElementById("skip_button").addEventListener('click', setListenerBySkipButton);

enemiesElements = document.getElementsByClassName("enemies");
}else{
    setActiveButtons(true);
    setTimeout(function(){
    console.log("Ход противника");
    update_fightersQueue();
    update_log();
    update_userFighter();
    nextMove();
    update_activeFighter();
    checkUserMove();
}, 1000)
}
}
function setActiveButtons(active){
    elementAttack = document.getElementById("attack_button").disabled = active;
    elementDefend = document.getElementById("defend_button").disabled = active;
    elementSkip = document.getElementById("skip_button").disabled = active;
}
function setListenerBySkipButton(){
    console.log('Успех skip');
    update_All('skip', 'yourself')
}

function setListenerByAttackButton(){
    console.log(enemiesElements);
for(var i = 0; i < enemiesElements.length; i++){
    let d = enemiesElements[i];
    d.addEventListener('click', function(){
        if(isAttackClick){
            console.log("work")
            let nameFighter = getName(d);
            update_All('attack', nameFighter)
        }
    });
}
}
function setListenerByDefendButton(){
    console.log('Успех defend');
    update_All('defend', 'yourself')
}
function getName(enemy){
    nameFighter = enemy.getElementsByClassName("nameFighter")[0].innerHTML;
    return nameFighter;
}
function update_All(arg_action, id_enemy){
    setAction(arg_action, id_enemy)
    update_fightersQueue();
    update_log();
    update_userFighter();
    nextMove();
    update_activeFighter();
    checkUserMove();
}
function setAction(arg_action, id_enemy){
    console.log("функция attack_click" + arg_action + id_enemy);
    var dataResponse = {arg : arg_action, id : id_enemy};
    console.log(dataResponse);
    $(document).ready(function(){
            $.ajax({
                async: false,
                type: 'post',
                url : "/set_action",
                data : JSON.stringify(dataResponse),
                dateType: 'json',
                contentType: "application/json"
            })
    })
    isAttackClick = false;
}
function update_fightersQueue(){
    $(document).ready(function(){
            $.ajax({
                async: false,
                type: 'post',
                url : "/update_fightersQueue-url",
            }).done(function(header) { 
        $("#enemyBlocks").replaceWith(header); 
    });
    })
    isAttackClick = false;
}
function update_activeFighter(){
    $(document).ready(function(){
        $.ajax({
            async: false,
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
            async: false,
            type: 'post',
            url: '/update_user_url'
        }).done(function(header){
            $("#userFighter").replaceWith(header);
            //load();
        });
    })
}
function update_log(){
    $(document).ready(function(){
        $.ajax({
            async: false,
            type: 'post',
            url: '/update_logFight'
        }).done(function(header){
            $("#logBlock").replaceWith(header);
            //load();
        });
    })
}
function checkUserMove(){
    $(document).ready(function(){
        $.ajax({
            async: false,
            type: 'post',
            url: '/checkUserMove'
        }).done(function(header){
            console.log(header);
            if(header == "true"){
                load(true);
            }else{
                load(false);
            }
        })
    })
}
function nextMove(){
    $(document).ready(function(){
        $.ajax({
            async: false,
            type: 'post',
            url: '/next_move'
        }).done(function(header){
            console.log(header);
        })
    })
}