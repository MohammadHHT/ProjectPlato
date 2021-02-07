function suggestion(username, token) {
    this.username = username;
    this.token = token;
    this.init();
}

suggestion.prototype.init = function () {
    const self = this;

    document.querySelector().addEventListener('click', () => {
        const connection = new WebSocket('ws://127.0.0.1:4444');

        connection.onopen = function () {
            connection.send('user allUsers' + self.username + ' ' + self.token);
        };

        connection.onmessage = function (e) {

            connection.close();
        };
    })
}