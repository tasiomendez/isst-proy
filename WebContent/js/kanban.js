
// Cache common DOM
var UI = {
		elBoard: document.getElementById('board'),
		elTotalCardCount: document.getElementById('totalCards'),
		elCardPlaceholder: null,
},
lists = [],
todos = [],
isDragging = false,
_listCounter = 0, // To hold last ID/index to avoid .length based index
_cardCounter = 0; // To hold last ID/index to avoid .length based index

// Live binding event listener (like jQuery's .on)
function live(eventType, selector, callback) {
	document.addEventListener(eventType, function (e) {
		if (e.target.webkitMatchesSelector(selector)) {
			callback.call(e.target, e);
		}
	}, false);
}

// Draggable Cards
live('dragstart', '.list .card', function (e) {
	isDragging = true;
	e.dataTransfer.setData('text/plain', e.target.dataset.id);
	e.dataTransfer.dropEffect = "copy";
	e.target.classList.add('dragging');
});
live('dragend', '.list .card', function (e) {
	this.classList.remove('dragging');
	UI.elCardPlaceholder && UI.elCardPlaceholder.remove();
	UI.elCardPlaceholder = null;
	isDragging = false;
});

// Dropzone
live('dragover', '.list, .list .card, .list .card-placeholder', function (e) {
	e.preventDefault();
	e.dataTransfer.dropEffect = "move";
	if(this.className === "list") { // List
		this.appendChild(getCardPlaceholder());
	} else if(this.className.indexOf('card') !== -1) { // Card
		this.parentNode.insertBefore(getCardPlaceholder(), this);
	}
});
live('drop', '.list, .list .card-placeholder', function (e) {
	e.preventDefault();
	if(!isDragging) return false;
	var todo_id = +e.dataTransfer.getData('text');
	var todo = todos[getIndexCard(todo_id)];
	var newListID = null; 
	if(this.className === 'list') { // Dropped on List
		newListID = this.dataset.id;
		this.appendChild(todo.dom);
	} else { // Dropped on Card Placeholder
		newListID = this.parentNode.dataset.id;
		this.parentNode.replaceChild(todo.dom, this);
	}
	moveCard(todo_id, +newListID);
});

function createCard(id,title,description,planned_hours,list, cardState, index) {
	if(!title || title === '') return false;
	var newCardId=id;

	var card= document.getElementById(id)

	try{
		var buttonCard=document.getElementById("buttonCard"+id);
		buttonCard.onclick = function removeCard() {
			// Removes an element from the document
			var element = document.getElementById(card.id);
			element.parentNode.removeChild(element);
			updateDB("delete",todos[getIndexCard(card.id)]);
			return card;
		};
	}finally{
		var todo = {
				_id: newCardId,
				cardState: cardState,
				title: title,
				description: description,
				planned_hours:planned_hours,
				hours_done:0,
				dom: card,
				index: index || list.cards+1 // Relative to list
		};
		todos.push(todo);

		// Update card count in list
		++list.cards;

		return card;
	}


}

function importCard(id,title,description,planned_hours,state){
	if(state=="todo"){
		addTodo(id,title,description,planned_hours);
	}else if(state=="doing"){
		addInProgress(id,title,description,planned_hours);
	}else{
		addDone(id,title,description,planned_hours);
	}
}

function addTodo(id,title,description,planned_hours,index, updateCounters) {
	if(!title) return false;
	var list =document.getElementById("list_1")
	var card = createCard(id,title,description,planned_hours,list,"todo",index);

	if(index) {
		list.insertBefore(card, list.children[index]);
	} else {
		list.appendChild(card);
	}
	// Don't update DOM if said so
	if(updateCounters !== false) {updateCardCounts();}
}

function addInProgress(id,title,description,planned_hours,index, updateCounters) {
	if(!title) return false;
	var list =document.getElementById("list_2")
	var card = createCard(id,title,description,planned_hours,list,"doing",index);

	if(index) {
		list.insertBefore(card, list.children[index]);
	} else {
		list.appendChild(card);
	}
	// Don't update DOM if said so
	if(updateCounters !== false) {updateCardCounts();}
}

function addDone(id,title,description,planned_hours,index, updateCounters) {
	if(!title) return false;
	var list =document.getElementById("list_3")
	var card = createCard(id,title,description,planned_hours,list,"done",index);

	if(index) {
		list.insertBefore(card, list.children[index]);
	} else {
		list.appendChild(card);
	}
	// Don't update DOM if said so
	if(updateCounters !== false) {updateCardCounts();}
}

function getIndexCard(id){
	index = todos.findIndex(x => x._id==id);
	return index
}

// Update Card Counts
// Updating DOM objects that are cached for performance
function updateCardCounts (listArray) {
	//UI.elTotalCardCount.innerHTML = 'Total Projects: '+todos.length;
	lists.map(function (list) {
		list.elCounter.innerHTML = list.cards;
	})
}

function moveCard(cardId, newListId, index) {
	if(!cardId) return false;
	try {

		var card = todos[getIndexCard(cardId)];
		if(card.listID !== newListId) { // If different list
			//Modificamos el estado de la tarjeta
			if(newListId==1){
				card["cardState"]="todo"
			}else if (newListId==2){
				card["cardState"]="doing"
			}else{
				card["cardState"]="done"
			}
			updateDB("state",card);
			updateCardCounts();
		}

		if(index)
			card.index = index;

	} catch (e) {
		console.log(e.message)
	}
}

function saveHours(){
	var id_card=document.getElementsByName("id_card");
	var horas_hechas=document.getElementsByName("horas_hechas");
	var card = getTodo({_id: id_card});
	card["hours_done"]=horas_hechas;
}

function getCardPlaceholder () {
	if(!UI.elCardPlaceholder) { // Create if not exists
		UI.elCardPlaceholder = document.createElement('div');
		UI.elCardPlaceholder.className = "card-placeholder";
	}
	return UI.elCardPlaceholder;
}

$(".saveHours").on("submit", function() {
	var id_card=document.getElementsByName("id_card");
	var horas_hechas=document.getElementsByName("horas_hechas");
	var card = getTodo({_id: id_card});
	card["hours_done"]=horas_hechas;
});

function updateDB(action,card){
	$.ajax({
		url: 'UpdateKanbanServlet',
		data: {
			action:action, 
			id_card:card["_id"],
			state_card:card["cardState"]
		},
		method:'POST',
		error: function(error) {
			console.log(error);
			$('#error-task').css('z-index', '2000').modal('show');
		},
		success: function(response) {
			console.log(response)
		},
	});
} 

function init () {
	updateCardCounts();
	moveCard(2, 1, 3);
}

document.addEventListener("DOMContentLoaded", function() {
	init();
});




