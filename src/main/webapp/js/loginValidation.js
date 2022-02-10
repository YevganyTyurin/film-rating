const form = document.getElementById('form');
const password = document.getElementById('password');
const login = document.getElementById('login');

form.addEventListener('submit', e => {
    if (validateInputs()) {
        e.preventDefault();
    } else {
        window.location.replace("Controller?command=login");
    }
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const isValidPassword = nickname => {
    let re = /^[a-zA-Z0-9_]{5,49}$/;
    return re.test(String(nickname));
}

const isValidLogin = login => {
    let re = /^[a-zA-Z0-9_]{5,49}$/;
    return re.test(login);
}

const validateInputs = () => {
    const passwordValue = password.value.trim();
    const loginValue = login.value.trim();

    let counter = 0;

    if(loginValue === '') {
        setError(login, 'Login is required');
        counter++;
    } else if (loginValue.length < 5 ) {
        setError(login, 'Login must be at least 5 character.')
        counter++;
    } else if (!isValidLogin(loginValue)) {
        setError(login, 'Provide a valid login');
        counter++;
    } else {
        setSuccess(login);
    }

    if(passwordValue === '') {
        setError(password, 'Password is required');
        counter++;
    } else if (passwordValue.length < 6 ) {
        setError(password, 'Password must be at least 6 character.')
        counter++;
    } else if (!isValidPassword(passwordValue)) {
        setError(password, 'Provide a valid password');
        counter++;
    } else {
        setSuccess(password);
    }

    return counter;
};