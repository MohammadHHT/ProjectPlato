function register() {
    this.items = document.querySelectorAll('section.register form ol .item');
    this.inputs = document.querySelectorAll('section.register form ol .item input');
    this.next = document.querySelector('section.register form .next');
    this.message = document.querySelector('section.register form .message');
    this.progress = document.querySelector('section.register form .progress');
    this.navigation = document.querySelectorAll('aside nav');
    this.question = 1;
    this.init();
}

register.prototype.init = function () {
    const self = this;

    this.inputs[0].addEventListener('focus', function () {
        self.next.classList.add('show');
    });

    this.inputs[0].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.firstName();
        }
    });

    this.inputs[1].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.lastName();
        }
    });

    this.inputs[2].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.username();
        }
    });

    this.inputs[3].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.password();
        }
    });

    this.inputs[4].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.phone();
        }
    });

    this.inputs[5].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.email();
        }
    });

    this.next.addEventListener('click', function () {
        switch (self.question) {
            case 1:
                self.firstName();
                break;
            case 2:
                self.lastName();
                break;
            case 3:
                self.username();
                break;
            case 4:
                self.password();
                break;
            case 5:
                self.phone();
                break;
            case 6:
                self.email();
                break;
        }
    });

    this.navigation[1].addEventListener('click', function () {
        self.navigation[1].classList.remove('show');
        self.navigation[2].classList.add('show');
        self.nextPage('register', 'login');

        self.clearFields();
        self.items[self.question - 1].classList.remove('show');
        self.items[0].classList.add('show');
        self.progress.style.transform = 'scaleX(' + 0 + ')';
        self.next.classList.remove('show');
        self.question = 1;
    });
}

register.prototype.firstName = function () {
    if (/^[a-zA-Z\s]+$/.test(this.inputs[0].value)) {
        this.toggle(1);
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.lastName = function () {
    if (/^[a-zA-Z\s]+$/.test(this.inputs[1].value)) {
        this.toggle(2);
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.username = function () {
    if (/\w{3}\w*/.test(this.inputs[2].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user register username ' + self.inputs[2].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                self.toggle(3);
            } else {
                self.message.innerHTML = "Username unavailable!";
                self.message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Username must be at least 3 characters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.password = function () {
    if (/\w{6}\w*/.test(this.inputs[3].value)) {
        this.toggle(4);
    } else {
        this.message.innerHTML = "Password must be at least 6 characters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.phone = function () {
    if (/\d{12}/.test(this.inputs[4].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user register phone ' + self.inputs[4].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                self.toggle(5);
            } else {
                self.message.innerHTML = "Phone number already exists!";
                self.message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Invalid phone!";
        this.message.style.opacity = 1;
    }
}

register.prototype.email = function () {
    if (/^\w+@\w+\.(com|ir)$/.test(this.inputs[5].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user register email ' + self.inputs[5].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                self.progress.style.transform = 'scaleX(' + 6 / 6 + ')';
                self.items[5].style.opacity = 0;
                self.message.style.opacity = 0;
                self.next.classList.remove('show');

                self.navigation[1].classList.remove('show');
                self.navigation[3].classList.add('show');
                self.navigation[4].classList.add('show');
                self.navigation[5].classList.add('show');
                setTimeout(function () {
                    self.nextPage('register', 'primary');
                    self.items[5].classList.remove('show');
                }, 300);
                self.submit();
            } else if (e.data.localeCompare('false') == 0) {
                self.message.innerHTML = "Email already exists!";
                self.message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Invalid email!";
        this.message.style.opacity = 1;
    }
}

register.prototype.toggle = function (num) {
    this.progress.style.transform = 'scaleX(' + num / 6 + ')';
    this.items[num - 1].classList.remove('show');
    this.items[num].classList.add('show');
    this.items[num].querySelector('input').focus();
    this.message.style.opacity = 0;
    this.question++;
}

register.prototype.nextPage = function (previous, next) {
    let pre = document.querySelector('section.' + previous);
    let nxt = document.querySelector('section.' + next);

    if (pre != undefined) {
        pre.classList.remove('page');
    }
    nxt.classList.add('page');
}

register.prototype.clearFields = function () {
    for (let i = 0; i < 6; i++) {
        this.inputs[i].value = '';
    }
}

register.prototype.submit = function () {
    const connection = new WebSocket('ws://127.0.0.1:4444');
    const self = this;

    connection.onopen = function () {
        connection.send('user register ' + self.inputs[0].value + ' ' + self.inputs[1].value + ' ' + self.inputs[2].value + ' ' + self.inputs[3].value + ' ' + self.inputs[4].value + ' ' + self.inputs[5].value);
    };

    connection.onmessage = function (e) {
        let date = new Date();
        new account(true, e.data, self.inputs[0].value, self.inputs[1].value, self.inputs[2].value, self.inputs[4].value, self.inputs[5].value, date.getFullYear(), (date.getMonth() + 1), date.getDate(), 1, 0);
        self.clearFields();
        connection.close();
    }
}