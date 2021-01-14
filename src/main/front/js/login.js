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

    this.inputs[0].addEventListener('focus', function () {
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

    this.next.addEventListener('click', function () {
        switch (self.question) {
            case 1:
                self.username();
                break;
            case 2:
                self.password();
                break;
        }
    });

    this.navigation[2].addEventListener('click', function () {
        self.navigation[2].classList.remove('show');
        self.navigation[1].classList.add('show');
        self.nextPage('login', 'register');

        self.clearFields();
        self.items[self.question - 1].classList.remove('show');
        self.items[0].classList.add('show');
        self.progress.style.transform = 'scaleX(' + 0 + ')';
        self.next.classList.remove('show');
        self.question = 1;
    });
}

login.prototype.username = function () {
    if (this.inputs[0].value.length > 0) {
        this.toggle(1, 2);
    } else {
        this.message.innerHTML = "Enter username!";
        this.message.style.opacity = 1;
    }
}

login.prototype.password = function () {
    if (this.inputs[1].value.length > 0) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user login ' + self.inputs[0].value + ' ' + self.inputs[1].value);
        };

        connection.onmessage = function (e) {
            let data = e.data.split(' ');
            if (data.length != 2) {
                self.progress.style.transform = 'scaleX(' + 2 / 2 + ')';
                document.querySelector('section.login form ol .item.show label').classList.add('tmp');
                self.items[1].style.opacity = 0;
                self.message.style.opacity = 0;
                self.next.classList.remove('show');

                self.navigation[2].classList.remove('show');
                self.navigation[3].classList.add('show');
                self.navigation[4].classList.add('show');
                self.navigation[5].classList.add('show');
                setTimeout(function () {
                    self.nextPage('login', 'primary');
                    self.items[1].classList.remove('show');
                }, 300);
                self.clearFields();

                new account(data[0].localeCompare('admin') != 0, data[1], data[2], data[3], data[4], data[5], parseInt(data[6]), parseInt(data[7]), parseInt(data[8]), data[9], data[10])
            } else {
                if (data[1].localeCompare('username') == 0) {
                    self.message.innerHTML = "Username doesn't exist!";
                    self.message.style.opacity = 1;
                } else if (data[1].localeCompare('password') == 0) {
                    self.message.innerHTML = "Wrong password!";
                    self.message.style.opacity = 1;
                } else {
                    self.message.innerHTML = "You've logged in already!";
                    self.message.style.opacity = 1;
                }
                setTimeout(function () {
                    self.clearFields();
                    this.toggle(2, 1);
                }, 1000);
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Enter password!";
        this.message.style.opacity = 1;
    }
}

login.prototype.toggle = function (num1, num2) {
    this.progress.style.transform = 'scaleX(' + num1 / 2 + ')';
    this.items[num1 - 1].classList.remove('show');
    this.items[num2 - 1].classList.add('show');
    this.items[num2 - 1].querySelector('input').focus();
    this.message.style.opacity = 0;
    this.question += num2 - num1;
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