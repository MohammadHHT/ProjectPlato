function login() {
    this.items = document.querySelectorAll('section.login form ol .item');
    this.inputs = document.querySelectorAll('section.login form ol .item input');
    this.next = document.querySelector('section.login form .next');
    this.message = document.querySelector('section.login form .message');
    this.progress = document.querySelector('section.login form .progress');
    this.navigation = document.querySelectorAll('aside nav');
    this.question = 1;
    this.init();
}

login.prototype.init = function () {
    const self = this;

    this.inputs[0].addEventListener('focus', function (event) {
        self.next.classList.add('show');
    });

    this.inputs[0].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.username();
        }
    });

    this.inputs[1].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.password();
        }
    });

    this.next.addEventListener('click', function (event) {
        switch (self.question) {
            case 1:
                self.username();
                break;
            case 2:
                self.password();
                break;
        }
    });

    this.navigation[2].addEventListener('click', function (event) {
        self.navigation[2].classList.remove('show');
        self.navigation[1].classList.add('show');
    });
}

login.prototype.username = function () {
    if (this.inputs[0].value.length > 0) {
        self.progress.style.transform = 'scaleX(' + 1 / 2 + ')';
        self.items[0].classList.remove('show');
        self.items[1].classList.add('show');
        self.items[1].querySelector('input').focus();
        self.message.style.opacity = 0;
        self.question++;
    } else {
        this.message.innerHTML = "Enter username!";
        this.message.style.opacity = 1;
    }
}

login.prototype.password = function () {
    if (this.inputs[3].value.length > 0) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user login ' + self.inputs[0].value + ' ' + self.inputs[1].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('done') == 0) {
                self.progress.style.transform = 'scaleX(' + 2 / 2 + ')';
                self.items[1].style.opacity = 0;
                self.clearFields();
                self.message.style.opacity = 0;
                self.next.classList.add('hide');
                self.question++;
                setTimeout(function () {
                    nextPage('login', 'primary');
                    self.navigation[2].classList.remove('show');
                    self.navigation[3].classList.add('show');
                    self.navigation[4].classList.add('show');
                    self.navigation[5].classList.add('show');
                }, 1000);
            } else {
                if (e.data.localeCompare('failed username') == 0) {
                    this.message.innerHTML = "Username doesn't exist!";
                    this.message.style.opacity = 1;
                } else if (e.data.localeCompare('failed password') == 0) {
                    this.message.innerHTML = "Wrong password!";
                    this.message.style.opacity = 1;
                } else {
                    this.message.innerHTML = "You've logged in already!";
                    this.message.style.opacity = 1;
                }
                self.question--;
                self.clearFields();
                self.progress.style.transform = 'scaleX(' + 0 + ')';
                self.items[1].classList.remove('show');
                self.items[0].classList.add('show');
                self.items[0].querySelector('input').focus();
                self.message.style.opacity = 0;
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Enter password!";
        this.message.style.opacity = 1;
    }
}

login.prototype.nextPage = function (previous, next) {
    let pre = document.querySelector('section.' + previous);
    let nxt = document.querySelector('section.' + next);

    if (pre != undefined) {
        pre.classList.remove('page');
    }
    nxt.classList.add('page');
}

login.prototype.clearFields = function () {
    for (let i = 0; i < 2; i++) {
        this.inputs[i].value = '';
    }
}