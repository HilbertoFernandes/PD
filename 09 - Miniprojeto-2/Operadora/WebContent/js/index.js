new function() {
	var ws = null;
	var connected = false;

	var serverUrl;
	var connectionStatus;
	var sendMessage;

	var connectButton;
	var disconnectButton;
	var sendButton;

	var open = function() {
		var url = serverUrl.val();
		ws = new WebSocket(url);
		ws.onopen = onOpen;
		ws.onclose = onClose;
		ws.onmessage = onMessage;
		ws.onerror = onError;

		connectionStatus.text('CONECTANDO ...');
		cliente.attr('disabled', 'disabled');
		connectButton.hide();
		disconnectButton.show();
	}

	var close = function() {
		if (ws) {
			console.log('DESCONECTANDO ...');
			ws.close();
		}
		connected = false;
		connectionStatus.text('DESCONECTADO');

		serverUrl.removeAttr('disabled');
		connectButton.show();
		disconnectButton.hide();
		sendMessage.attr('disabled', 'disabled');
		sendButton.attr('disabled', 'disabled');

		document.getElementById('connectButton').style.display = "inline"
		document.getElementById('cliente').style.display = "inline"
		document.getElementById('seuNumero').style.display = "inline"
		document.getElementById('requestArea').style.display = "none"
		document.getElementById('requestArea2').style.display = "none"
	}

	var clearLog = function() {
		$('#messages').html('');
	}

	var onOpen = function() {
		console.log('CONECTADO: ' + serverUrl.val());
		connected = true;
		connectionStatus.text('CONECTADO');
		sendMessage.removeAttr('disabled');
		sendButton.removeAttr('disabled');
		document.getElementById('requestArea').style.display = "inline";
		document.getElementById('requestArea2').style.display = "inline";
		document.getElementById('disconnectButton').style.display = "inline";
		document.getElementById('connectButton').style.display = "none";
		document.getElementById('cliente').style.display = "none";
		document.getElementById('seuNumero').style.display = "none";

		var data = event.data;
	};

	var onClose = function() {
		console.log('DESCONECTADO: ' + serverUrl.val());
		addUsers(data);
		ws = null;
	};

	var onMessage = function(event) {
		var data = event.data;
		addMessage(data);
		console.log(data);
	};

	var onError = function(event) {
		alert(event.data);
	}

	var addMessage = function(data, type) {
		if (!data.includes(" ")) {
			addUsers(data);
		} else {
			var msg = $('<pre>').text(data);
			var messages = $('#messages');

			messages.append(msg);

			var msgBox = messages.get(0);

			while (msgBox.childNodes.length > 1000) {
				msgBox.removeChild(msgBox.firstChild);
			}
			msgBox.scrollTop = msgBox.scrollHeight;
		}
	}

	var addUsers = function(data) {
		$('#users').html('');
		var msg = $('<pre>').text(data);

		var usuarios = $('#users');
		usuarios.append(msg);

		var msgBox = usuarios.get(0);
		while (msgBox.childNodes.length > 1000) {
			msgBox.removeChild(msgBox.firstChild);
		}
		msgBox.scrollTop = msgBox.scrollHeight;
	}

	WebSocketClient = {
		init : function() {
			serverUrl = $('#serverUrl');
			connectionStatus = $('#connectionStatus');
			sendMessage = $('#sendMessage');

			connectButton = $('#connectButton');
			disconnectButton = $('#disconnectButton');
			sendButton = $('#sendButton');

			connectButton.click(function(e) {
				close();
				open();
			});

			disconnectButton.click(function(e) {
				close();
			});

			sendButton.click(function(e) {
				var msg = $('#sendMessage').val();
				//addMessage(msg, 'SENT');
				ws.send(msg);
			});

			$('#clearMessage').click(function(e) {
				clearLog();
			});

			var isCtrl;
			sendMessage.keyup(function(e) {
				if (e.which == 17)
					isCtrl = false;
			}).keydown(function(e) {
				if (e.which == 17)
					isCtrl = true;
				if (e.which == 13 && isCtrl == true) {
					sendButton.click();
					return false;
				}
			});
		}
	};
}

$(function() {
	WebSocketClient.init();
});