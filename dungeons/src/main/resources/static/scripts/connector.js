var chooseEnemy;
var isAttackClick = false;

const elementAttack = document.getElementById("attack_button").addEventListener('click', function(){
        console.log('Успех');
        isAttackClick = true;
        setListener();
})
//window.onclick = e => {
  //  console.log(e.target.innerText);
//} 
//window.addEventListener('click', function(e){   
  //  if (document.getElementById('').contains(e.target)){
   //   e.target.getElementsByClassName('name')
   // } else{
      // Clicked outside the box
   // }
 // });
const enemiesElements = document.getElementsByClassName("enemies");
function setListener(){
    console.log(enemiesElements);
for(var i = 0; i < enemiesElements.length; i++){
    let d = enemiesElements[i];
    d.addEventListener('click', function(){
        if(isAttackClick){
            console.log("work")
            let nameFighter = getName(d);
            update_All('attack', nameFighter);
        }
    });
}
}
function getName(enemy){
    nameFighter = enemy.getElementsByClassName("nameFighter")[0].innerHTML;
    return nameFighter;
}
function update_All(arg_action, id_enemy){
    console.log("функция attack_click" + arg_action + id_enemy);
    var dataResponse = {arg : arg_action, id : id_enemy};
    console.log(dataResponse);
    $(document).ready(function(){
            $.ajax({
                type: 'post',
                url : "/reload-data-url",
                data : JSON.stringify(dataResponse),
                dateType: 'json',
                contentType: "application/json"
            }).done(function(header) { // get from controller
        $("#enemyBlocks").replaceWith(header); // update snippet of page
    });
    })
    isAttackClick = false;
}