
    export function login (userEmail : string, userPassword : string) {
        if (userEmail.length > 2) {
            return fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/auth/login`, {
                method: 'POST',
                body: JSON.stringify({
                    email: userEmail,
                    password: userPassword,
                }),
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(response => {
                    if (response.ok) {
                        return response.text()
                    }
                    throw new Error("Token nicht gültig")
                })
        } else {
            return Promise.reject("Eingabe zu kurz!");
        }
    }


    export function logout(){
        return localStorage.removeItem("jwt");
    }



    export function register(userEmail: string, userPassword :string) {
      return fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/auth`, {
            method: 'POST',
            body: JSON.stringify({
                email: userEmail,
                password: userPassword,
            }),
            headers: {
                'Content-Type': 'application/json'
            }
    })
}




